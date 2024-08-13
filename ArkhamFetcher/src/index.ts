import getInvestigatorsJson from "./InvestigatorFetcher";

function main() {
  getInvestigatorsJson().then(console.log);
}


main();