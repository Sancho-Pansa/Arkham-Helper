import getInvestigatorsJson from "./InvestigatorFetcher";
import { inspect } from "node:util";
import fs from "node:fs/promises";
import Investigator from "./Investigator";

function main() {
  getInvestigatorsJson().then((json) => {
    const vanilla: Investigator[] = [];
    const kingsport: Investigator[] = [];
    const dunwich: Investigator[] = [];
    const innsmouth: Investigator[] = [];
    for(let investigator of json) {
      switch(investigator.expansion.toLocaleLowerCase()) {
        case "ужас кингспорта":
          kingsport.push(investigator);
          break;
        case "ужас данвича":
          dunwich.push(investigator);
          break;
        case "ужас иннсмута":
          innsmouth.push(investigator);
          break;
        default:
          vanilla.push(investigator);
      }
    }
    return [
      vanilla,
      kingsport,
      dunwich,
      innsmouth
    ];
  }).then(([vanilla, kingsport, dunwich, innsmouth]) => {
    writeFile(vanilla, "investigators_classic");
    writeFile(kingsport, "investigators_kingsport");
    writeFile(dunwich, "investigators_dunwich");
    writeFile(innsmouth, "investigators_innsmouth");
  });
}

function writeFile(json: Object, filename: string) {
  fs.mkdir("./out", { recursive: true });
  fs.writeFile(`./out/${filename}.json`, JSON.stringify(json, null, 2), { encoding: "utf-8" });
}


main();