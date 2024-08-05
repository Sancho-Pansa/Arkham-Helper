package io.sanchopansa.arkham.mechanics.investigators;

/**
 * Обозначает объект характеристики Сыщика и операции с ними
 */
public class Statistic {
    private int minimum;
    private int maximum;
    private int current;

    // Вариант характеристики для здоровья или рассудка (минимум всегда 0)
    public Statistic(int initialValue) {
        this.minimum = 0;
        this.maximum = initialValue;
        this.current = maximum;
    }

    // Вариант характеристики для навыков
    public Statistic(int minimum, int maximum) {
        this.minimum = minimum;
        this.current = minimum;
        this.maximum = maximum;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    /**
     * Данная функция добавляет к характеристике определенное значение,
     * а затем возвращает код результата.
     * Позволяет собрать в одном месте обработку величин, которые ограничены сверху и снизу.
     * Если величина прибавки превышает максимум, результатом станет максимум.
     * Если величина вычета опустит текущее значение ниже минимума, результатом станет минимум.
     * @param value величина изменения характеристики
     * @return -1 (отрицательное число) - в ходе операции был достигнут минимум<br />
     * 0 - в ходе операции границы характеристики не достигнуты<br />
     * 1 (положительное число - в ходе операции был достигнут максимум
     */
    public int add(int value) {
        current += value;
        if(current >= maximum) {
            current = maximum;
            return 1;
        }
        if(current < minimum) {
            current = minimum;
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Stat: [ %d / %d / %d ]", minimum, current, maximum);
    }
}