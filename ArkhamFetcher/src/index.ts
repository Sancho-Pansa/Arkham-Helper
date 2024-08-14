import getInvestigatorsJson from "./InvestigatorFetcher";
import { inspect } from "node:util";
import fs from "node:fs/promises";

function main() {
  getInvestigatorsJson().then(writeFile);
}

function writeFile(json: Object) {
  fs.writeFile("./out/investigators.json", JSON.stringify(json, null, 2), { encoding: "utf-8" });
}


main();