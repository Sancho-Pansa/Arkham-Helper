import fs from "node:fs/promises";

const LIST = "./lists/investigators.txt";
const DEFAULT_URL = "https://arkhamhorror.fandom.com/ru/api.php";

export default async function getInvestigatorsJson() {
  let investigatorList = await fs.readFile(LIST, { encoding: "utf-8" });
  let promises = investigatorList.split(/\r?\n/g).map(async (s) => {
    let wikipage = await fetchInvestigatorPage(s);
    let parsedObj = parseWikiPage(s, wikipage);
    return parsedObj;
  });
  return Promise.all(promises);
}

async function fetchInvestigatorPage(investigator: string): Promise<string> {
  const requestOptions = {
    method: "GET",
    redirect: "follow"
  } as Request;
  let searchParams = new URLSearchParams({
    action: "parse",
    format: "json",
    formatversion: "2",
    prop: "wikitext",
    page: investigator
  });
  let url = new URL(`${DEFAULT_URL}?${searchParams.toString()}`);

  let response = await fetch(url, requestOptions);
  if(!response.ok) {
    throw new Error("Network error");
  }
  let wikitext = (await response.json()).parse.wikitext;
  return wikitext;
}

function parseWikiPage(investigator: string, wikitext: string) {
  // Simple regexes
  let expansionRegex = /^\|Edition=(.*)$/gm;
  let titleRegex = /^\|Occupation=(.*)$/gm;
  let staminaRegex = /^\|Stamina=(.*)$/gm;
  let sanityRegex = /^\|Sanity=(.*)$/gm;
  let focusRegex = /^\|Focus=(.*)$/gm;
  let minSpeedRegex = /^\|Speed1=(.*)$/gm;
  let maxSneakRegex = /^\|Sneak1=(.*)$/gm;
  let minFightRegex = /^\|Fight1=(.*)$/gm;
  let maxWillRegex = /^\|Will1=(.*)$/gm;
  let minLoreRegex = /^\|Lore1=(.*)$/gm;
  let maxLuckRegex = /^\|Luck1=(.*)$/gm;

  // Complex regexes
  let abilityRegex = /\|Ability1=(.*?)\n\|/gs;
  let fixedPosessionsRegex = /\|FixedPossessions=(.*?)\n\|/gs;
  let randomPosessionsRegex = /\|RandomPossessions=(.*?)\n\|/gs;
  let storyRegex = /(?:''')Предыстория:?(?:''')?:? ?(.*)/gs;

  let abilityText = removeWikilinks(abilityRegex.exec(wikitext)?.[1]);
  let fixedPosessionsText = removeWikilinks(fixedPosessionsRegex.exec(wikitext)?.[1]);
  let randomPosessionsText = removeWikilinks(randomPosessionsRegex.exec(wikitext)?.[1]);
  let storyText = removeWikilinks(storyRegex.exec(wikitext)?.[1]);


  return {
    investigator,
    expansion: expansionRegex.exec(wikitext)?.[1],
    title: titleRegex.exec(wikitext)?.[1],
    stamina: staminaRegex.exec(wikitext)?.[1],
    sanity: sanityRegex.exec(wikitext)?.[1],
    ability: abilityText,
    fixedPosessions: fixedPosessionsText,
    randomPosessions: randomPosessionsText,
    focus: focusRegex.exec(wikitext)?.[1],
    minSpeed: minSpeedRegex.exec(wikitext)?.[1],
    maxSneak: maxSneakRegex.exec(wikitext)?.[1],
    minFight: minFightRegex.exec(wikitext)?.[1],
    maxWill: maxWillRegex.exec(wikitext)?.[1],
    minLore: minLoreRegex.exec(wikitext)?.[1],
    maxLuck: maxLuckRegex.exec(wikitext)?.[1],
    story: storyText,
  }
}

function removeWikilinks(wikitext: string | undefined) {
  if(wikitext === undefined) {
    return "";
  }
  const REGEX = /(?:\[\[([^|]*?)\]\]|\[\[.*?\|(.*?)\]\])/g;
  return wikitext.replace(REGEX, "$1$2");
}