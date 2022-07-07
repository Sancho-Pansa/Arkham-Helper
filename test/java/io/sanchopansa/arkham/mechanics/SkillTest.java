package io.sanchopansa.arkham.mechanics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SkillTest {
    private Skill skill;
    private int leftBlue = 0;
    private int leftRed = 5;

    @Test
    public void checkRightBlueValue() {
        skill = new Skill(leftBlue, leftRed);
        assertEquals(leftBlue + 3, skill.getRightBlue());
    }

    @Test
    public void checkRightRedValue() {
        skill = new Skill(leftBlue, leftRed);
        assertEquals(leftRed - 3, skill.getRightRed());
    }

    @Test
    public void moveLeft() {
        skill = new Skill(leftBlue, leftRed);
        skill.setSkillPosition(2);
        skill.moveLeft();
        assertEquals(leftBlue + 1, skill.getBlueValue());
    }

    @Test
    public void moveRight() {
        skill = new Skill(leftBlue, leftRed);
        skill.setSkillPosition(1);
        skill.moveRight();
        assertEquals(leftRed - 2, skill.getRedValue());
    }
}