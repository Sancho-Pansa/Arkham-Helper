import fs from "node:fs/promises";

const LIST = "./lists/investigators.txt";
const DEFAULT_URL = "https://arkhamhorror.fandom.com/ru/api.php";

export default async function getInvestigatorsJson() {
  let investigatorList = await fs.readFile(LIST, { encoding: "utf-8" });
  investigatorList.split(/\r?\n/g).forEach((s) => {
    fetchInvestigatorPage(s).then(console.log);
  });
}

async function fetchInvestigatorPage(investigator) {
  const requestOptions = {
    method: "GET",
    redirect: "follow"
  };
  let searchParams = new URLSearchParams({
    action: "parse",
    format: "json",
    formatversion: 2,
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