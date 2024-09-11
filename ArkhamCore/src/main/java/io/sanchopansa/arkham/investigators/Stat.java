package io.sanchopansa.arkham.investigators;

/**
 * Обозначает объект характеристики Сыщика и операции с ними.<br />
 * Характеристика определяется как диапазон неотрицательных величин, нестрого ограниченный сверху и снизу.
 * Текущее значение не может стать меньше минимума и больше максимума в результате операции, и при пересечении границ
 * будет принудительно приравнено к этой границе.<br />
 * По достижении характеристикой границы игра может налагать на сыщика особое действие или ограничение.
 */
public class Stat {
    private final int minimum;
    private int currentMinimum;
    private final int maximum;
    private int currentMaximum;
    private int value;

    /**
     * Задает характеристику, у которой минимум - это 0, а максимум - переданное число.
     * @param initialMaximum максимум характеристики
     */
    public Stat(int initialMaximum) {
        this.minimum = 0;
        this.currentMinimum = 0;
        this.maximum = initialMaximum;
        this.currentMaximum = initialMaximum;
        this.value = maximum;
    }

    /**
     * Задает характеристику, ограниченную сверху и снизу числами, переданными в аргументах функции
     * @param minimum Минимум характеристики
     * @param maximum Максимум характеристики
     */
    public Stat(int minimum, int maximum) {
        this.minimum = minimum;
        this.value = minimum;
        this.maximum = maximum;
    }

    public int getValue() {
        return this.value;
    }

    public int getCurrentMinimum() {
        return currentMinimum;
    }

    // Если текущее значение меньше нового минимума, оно будет доведено до него.
    public void setCurrentMinimum(int currentMinimum) {
        this.currentMinimum = currentMinimum;
        this.value = Math.max(this.value, this.currentMinimum);
    }

    public void resetCurrentMinimum() {
        this.currentMinimum = this.minimum;
        this.value = Math.max(this.value, this.currentMinimum);
    }

    public int getCurrentMaximum() {
        return currentMaximum;
    }

    // Если текущее значение превышает новый максимум, оно будет низведено до него.
    public void setCurrentMaximum(int currentMaximum) {
        this.currentMaximum = currentMaximum;
        this.value = Math.min(this.currentMaximum, this.value);
    }

    public void resetCurrentMaximum() {
        this.currentMaximum = this.maximum;
        this.value = Math.min(this.currentMaximum, this.value);
    }

    public int getInitialMinimum() {
        return minimum;
    }

    public int getInitialMaximum() {
        return maximum;
    }

    /**
     * Данная функция добавляет к характеристике определенное значение, а затем возвращает код результата.
     * Позволяет собрать в одном месте обработку величин, которые ограничены сверху и снизу.
     * Если величина прибавки превышает максимум, результатом станет максимум.
     * Если величина вычета опустит текущее значение ниже минимума, результатом станет минимум.
     * @param value величина изменения характеристики
     * @return -1 (отрицательное число) - в ходе операции был достигнут минимум<br />
     * 0 - в ходе операции границы характеристики не достигнуты<br />
     * 1 (положительное число) - в ходе операции был достигнут максимум
     */
    public int add(int value) {
        this.value += value;
        if(this.value >= maximum) {
            this.value = maximum;
            return 1;
        }
        if(this.value < minimum) {
            this.value = minimum;
            return -1;
        }
        return 0;
    }

    /**
     * Восстанавливает текущее значение характеристики до максимума.
     */
    public void refill() {
        this.value = this.currentMaximum;
    }

    @Override
    public String toString() {
        return String.format("Stat {\n[ %d / %d / %d ]\n}", currentMinimum, value, currentMaximum);
    }
}