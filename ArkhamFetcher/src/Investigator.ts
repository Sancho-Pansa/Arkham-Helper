import Possessions from "./Possessions";

export default class Investigator {
  name: string;
  expansion: string;
  location: string;
  title: string;
  stamina: number;
  sanity: number;
  ability: string;
  possessions: Possessions;
  focus: number;
  minSpeed: number;
  maxSneak: number;
  minFight: number;
  maxWill: number;
  minLore: number;
  maxLuck: number;
  blessing: -1|0|1;
  retainer: boolean;
  cult: boolean;
  sheriff: boolean;
  loan: boolean;
  monsters: number;
  gates: number;
  story: string;
  constructor(
    name: string,
    expansion: string,
    title: string,
    location: string,
    stamina: number,
    sanity: number,
    ability: string,
    possessions: Possessions,
    focus: number,
    minSpeed: number,
    maxSneak: number,
    minFight: number,
    maxWill: number,
    minLore: number,
    maxLuck: number,
    story: string
  ) {
    this.name = name;
    this.expansion = expansion;
    this.location = location;
    this.title = title;
    this.stamina = stamina;
    this.sanity = sanity;
    this.ability = ability;
    this.possessions = possessions;
    this.focus = focus;
    this.minSpeed = minSpeed;
    this.maxSneak = maxSneak;
    this.minFight = minFight;
    this.maxWill = maxWill;
    this.minLore = minLore;
    this.maxLuck = maxLuck;
    this.story = story;
    this.blessing = 0;
    this.retainer = false;
    this.sheriff = false;
    this.cult = false;
    this.loan = false;
    this.monsters = 0;
    this.gates = 0;
  }
}