package io.sanchopansa.arkham.mechanics.investigators;

/**
 * Данный класс используется для описания одного ползунка навыка сыщика,
 * состоящего из двух разнонаправленных значений.
 */
public class Skill {
    private static final int SKILL_VALUES = 4; // Один навык принимает четыре значения

    private final int[] blueSkill = new int[SKILL_VALUES];
    private final int[] redSkill = new int[SKILL_VALUES];
    private final Stat focus;

    private int sliderIndex = 0;

    /**
     * Создает одну спаренную полосу навыков. "Синяя" полоса находится наверху и возрастает. "Красная" располагается
     * внизу и убывает.
     * Одна шкала всегда состоит из 4 значений "Синих" и "Красных" навыков.
     * @param leftBlue Самое левое (наименьшее) значение "синей" полосы
     * @param leftRed Самое левое (наибольшее) значение "красной" полосы
     * @param focus Значение Концентрации Сыщика
     */
    Skill(int leftBlue, int leftRed, Stat focus) {
        for (int i = 0; i < SKILL_VALUES; i++) {
            blueSkill[i] = leftBlue + i;
            redSkill[i] = leftRed - i;
        }
        this.focus = focus;
    }

    Skill(int leftBlue, int leftRed) {
        this(leftBlue, leftRed, new Stat(2));
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
        return blueSkill[sliderIndex];
    }

    public int getRedValue() {
        return redSkill[sliderIndex];
    }

    /**
     * Сдвигает ползунок навыка на одну позицию влево. Не может выйти за пределы массива.
     * @return Статус операции.<br />
     * <code>-1</code> - недостаточно Концентрации<br />
     * <code>0</code> - ползунок попытался выйти за пределы массива.<br />
     * <code>+1</code> - всё в порядке.
     */
    public int moveLeft() {
        if(focus.getValue() <= 0) {
            return -1;
        }
        sliderIndex--;
        focus.add(-1);
        if(sliderIndex <= 0) {
            sliderIndex = 0;
            return 0;
        }
        else
            return 1;
    }

    /**
     * Сдвигает ползунок навыка на одну позицию вправо. Не может выйти за пределы массива.
     * @return Статус операции.<br />
     * <code>-1</code> - недостаточно Концентрации<br />
     * <code>0</code> - ползунок попытался выйти за пределы массива.<br />
     * <code>+1</code> - всё в порядке.
     */
    public int moveRight() {
        if(focus.getValue() <= 0) {
            return -1;
        }
        sliderIndex++;
        focus.add(-1);
        if(sliderIndex >= SKILL_VALUES) {
            sliderIndex = SKILL_VALUES - 1;
            return 0;
        }
        else
            return 1;
    }

    public int setSkillPosition(int value) {
        if(value < 0 || value >= SKILL_VALUES) {
            throw new IllegalArgumentException("Skill Slider overflow");
        }
        this.sliderIndex = value;
        return sliderIndex;
    }

    @Override
    public String toString() {
        String blueRow = "";
        String redRow = "";
        for(int i = 0; i < SKILL_VALUES; i++) {
            if(i == sliderIndex) {
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