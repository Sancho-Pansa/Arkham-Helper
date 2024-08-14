import Item from "./Item";

export default class Possessions {
  money: number;
  clues: number;
  commons: Item;
  uniques: Item;
  spells: Item;
  skills: Item;
  allies: Item;

  constructor(
    money: number = 0,
    clues: number = 0,
    commons: Item = new Item(),
    uniques: Item = new Item(),
    spells: Item = new Item(),
    skills: Item = new Item(),
    allies: Item = new Item()
  ) {
    this.money = money;
    this.clues = clues;
    this.commons = commons;
    this.uniques = uniques;
    this.spells = spells;
    this.skills = skills;
    this.allies = allies;
  }
}