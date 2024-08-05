package io.sanchopansa.arkham.mechanics.investigators;

/**
 * Данный класс используется для описания одного ползунка навыка сыщика,
 * состоящего из двух разнонаправленных значений.
 */
public class Skill {
    private static final int SKILL_VALUES = 4; // Один навык принимает четыре значения

    private int[] blueSkill = new int[SKILL_VALUES];
    private int[] redSkill = new int[SKILL_VALUES];

    private int skillSliderPosition = 0;

    /**
     * Создает одну спаренную полосу навыков. "Синяя" полоса находится наверху и возрастает. "Красная" располагается
     * внизу и убывает.
     * Одна шкала всегда состоит из 4 значений "Синих" и "Красных" навыков.
     * @param leftBlue Самое левое (наименьшее) значение "синей" полосы
     * @param leftRed Самое левое (наибольшее) значение "красной" полосы
     */
    Skill(int leftBlue, int leftRed) {
        for (int i = 0; i < SKILL_VALUES; i++) {
            blueSkill[i] = leftBlue + i;
            redSkill[i] = leftRed - i;
        }
    }

    public int getLeftBlue() {
        return blueSkill[0];
    }

    public int getRightBlue() {
        return blueSkill[SKILL_VALUES - 1];
    }

    public int getLeftRed() {
        return redSkill[0];
    }

    public int getRightRed() {
        return redSkill[SKILL_VALUES - 1];
    }

    public int getBlueValue() {
        return blueSkill[skillSliderPosition];
    }

    public int getRedValue() {
        return redSkill[skillSliderPosition];
    }

    /**
     * Сдвигает ползунок навыка на одну позицию влево. Не может выйти за пределы массива.
     * @return Статус операции. Если 0 - ползунок попытался выйти за пределы массива. Если 1 - всё в порядке.
     */
    public int moveLeft() {
        skillSliderPosition--;
        if(skillSliderPosition <= 0) {
            skillSliderPosition = 0;
            return 0;
        }
        else
            return 1;
    }

    /**
     * Сдвигает ползунок навыка на одну позицию вправо. Не может выйти за пределы массива.
     * @return Статус операции. Если 0 - ползунок попытался выйти за пределы массива. Если 1 - всё в порядке.
     */
    public int moveRight() {
        skillSliderPosition++;
        if(skillSliderPosition >= SKILL_VALUES) {
            skillSliderPosition = SKILL_VALUES - 1;
            return 0;
        }
        else
            return 1;
    }

    public int setSkillPosition(int value) {
        if(value < 0)
            throw new IllegalArgumentException("Skill Slider cannot have negative position");
        this.skillSliderPosition = value % SKILL_VALUES;
        return skillSliderPosition;
    }

    @Override
    public String toString() {
        String blueRow = "";
        String redRow = "";
        for(int i = 0; i < SKILL_VALUES; i++) {
            if(i == skillSliderPosition) {
                blueRow += String.format(" [%d] ", blueSkill[i]);
                redRow += String.format(" [%d] ", redSkill[i]);
            }
            else {
                blueRow += " " + blueSkill[i] + " ";
                redRow += " " + redSkill[i] + " ";
            }

        }
        return blueRow + "\n" + redRow;
    }
}