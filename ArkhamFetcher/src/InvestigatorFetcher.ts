import fs from "node:fs/promises";
import Possessions from "./Possessions";
import Item from "./Item";
import Investigator from "./Investigator";

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

/**
 * Обрабатывает викитекст из arkham.fandom.com/ru, при помощи набора регулярных выражений.
 * @param investigator Имя сыщика
 * @param wikitext Код викитекста
 * @returns Литеральный объект сыщика
 */
function parseWikiPage(investigator: string, wikitext: string) {
  // Simple regexes
  let expansionRegex = /^\|Edition=(.*)$/gm;
  let titleRegex = /^\|Occupation=(.*)$/gm;
  let locationRegex = /^\|Home=(.*)$/gm;
  let staminaRegex = /^\|Stamina=(.*)$/gm;
  let sanityRegex = /^\|Sanity=(.*)$/gm;
  let focusRegex = /^\|Focus=(.*)$/gm;
  let minSpeedRegex = /^\|Speed1=(.*)$/gm;
  let maxSneakRegex = /^\|Sneak1=(.*)$/gm;
  let minFightRegex = /^\|Fight1=(.*)$/gm;
  let maxWillRegex = /^\|Will1=(.*)$/gm;
  let minLoreRegex = /^\|Lore1=(.*)$/gm;
  let maxLuckRegex = /^\|Luck1=(.*)$/gm;

  // Многострочные регексы
  let abilityRegex = /\|Ability1=(.*?)\n\|/gs;
  let fixedPosessionsRegex = /\|FixedPossessions=(.*?)\n\|/gs;
  let randomPosessionsRegex = /\|RandomPossessions=(.*?)\n\|/gs;
  let storyRegex = /(?:''')Предыстория:?(?:''')?:? ?(.*)/gs;

  // Убрать вики-ссылки из многострочных текстов
  let abilityText = removeWikilinks(abilityRegex.exec(wikitext)?.[1]);
  let fixedPosessionsText = removeWikilinks(fixedPosessionsRegex.exec(wikitext)?.[1]);
  let randomPosessionsText = removeWikilinks(randomPosessionsRegex.exec(wikitext)?.[1]);
  let storyText = removeWikilinks(storyRegex.exec(wikitext)?.[1]);

  let possessions = parsePossessions(fixedPosessionsText, randomPosessionsText);

  return new Investigator(
    investigator,
    expansionRegex.exec(wikitext)?.[1] ?? "",
    titleRegex.exec(wikitext)?.[1] ?? "",
    locationRegex.exec(wikitext)?.[1] ?? "",
    Number(staminaRegex.exec(wikitext)?.[1] ?? 0),
    Number(sanityRegex.exec(wikitext)?.[1] ?? 0),
    abilityText,
    possessions,
    Number(focusRegex.exec(wikitext)?.[1] ?? 0),
    Number(minSpeedRegex.exec(wikitext)?.[1] ?? 0),
    Number(maxSneakRegex.exec(wikitext)?.[1] ?? 0),
    Number(minFightRegex.exec(wikitext)?.[1] ?? 0),
    Number(maxWillRegex.exec(wikitext)?.[1] ?? 0),
    Number(minLoreRegex.exec(wikitext)?.[1] ?? 0),
    Number(maxLuckRegex.exec(wikitext)?.[1] ?? 0),
    storyText
  )
}

function removeWikilinks(wikitext: string | undefined) {
  if(wikitext === undefined) {
    return "";
  }
  const REGEX = /(?:\[\[([^|]*?)\]\]|\[\[.*?\|(.*?)\]\])/g;
  return wikitext.replace(REGEX, "$1$2");
}

function parsePossessions(fixed: string, random: string) {
  // Вычленить из текстов Имущества фиксированные и случайно выбираемые предметы
  const MONEY_REGEX = /\$(\d+)/g;
  const CLUE_REGEX = /(\d+) [Уу]лик/g;
  const RANDOM_COMMONS_REGEX = /(\d+) [Пп]рост.*? вещ.*?/g;
  const RANDOM_UNIQUES_REGEX = /(\d+) [Уу]никальн.*? вещ.*?/g;
  const RANDOM_SPELLS_REGEX = /(\d+) [Зз]аклинани.*?/g;
  const RANDOM_SKILLS_REGEX = /(\d+) [Нн]авык.*?/g;
  const RANDOM_ALLIES_REGEX = /(\d+) [Сс]оюзник.*?/g;

  const FIXED_COMMONS_REGEX = /[Пп]рост.*? вещ.*? \((.*)\)/g;
  const FIXED_UNIQUES_REGEX = /[Уу]никальн.*? вещ.*? \((.*)\)/g;
  const FIXED_SPELLS_REGEX = /[Зз]аклинани.*? \((.*)\)/g;
  const FIXED_SKILLS_REGEX = /[Нн]авык.*? \((.*)\)/g;
  const FIXED_ALLIES_REGEX = /[Сс]оюзник.*? \((.*)\)/g;

  let money = Number(MONEY_REGEX.exec(fixed)?.[1] ?? 0);
  let clues = Number(CLUE_REGEX.exec(fixed)?.[1] ?? 0);

  let fixedCommons = (FIXED_COMMONS_REGEX.exec(fixed)?.[1])?.split(/, ?/) ?? [];
  let randomCommons = Number(RANDOM_COMMONS_REGEX.exec(random)?.[1] ?? 0);
  let fixedUnuiques = (FIXED_UNIQUES_REGEX.exec(fixed)?.[1])?.split(/, ?/) ?? [];
  let randomUniques = Number(RANDOM_UNIQUES_REGEX.exec(random)?.[1] ?? 0);
  let fixedSpells = (FIXED_SPELLS_REGEX.exec(fixed)?.[1])?.split(/, ?/) ?? [];
  let randomSpells = Number(RANDOM_SPELLS_REGEX.exec(random)?.[1] ?? 0);
  let fixedSkills = (FIXED_SKILLS_REGEX.exec(fixed)?.[1])?.split(/, ?/) ?? [];
  let randomSkills = Number(RANDOM_SKILLS_REGEX.exec(random)?.[1] ?? 0);
  let fixedAllies = (FIXED_ALLIES_REGEX.exec(fixed)?.[1])?.split(/, ?/) ?? [];
  let randomAllies = Number(RANDOM_ALLIES_REGEX.exec(random)?.[1] ?? 0);

  return new Possessions(
    money,
    clues,
    new Item(randomCommons, fixedCommons),
    new Item(randomUniques, fixedUnuiques),
    new Item(randomSpells, fixedSpells),
    new Item(randomSkills, fixedSkills),
    new Item(randomAllies, fixedAllies)
  );
}