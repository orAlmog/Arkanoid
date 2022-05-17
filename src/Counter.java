// or almog 315828178
/**
 * @author or almog
 * This class defines a basic counter as a number with
 * decreasing/ increasing method and a method to get the value.
 */
public class Counter {
    private int number;

    /**
     * A constructor that creates a counter.
     * sets the number to 0.
     */
    public Counter() {
        this.number = 0;
    }

    /**
     * A method that adds a given number to the current count.
     *
     * @param number2 the given number
     */
    void increase(int number2) {
        this.number += number2;
    }

    /**
     * A method that subtract a given number from the current count.
     *
     * @param number2 the given number
     */
    void decrease(int number2) {
        this.number -= number2;
    }

    /**
     * A method that returns the current count.
     *
     * @return the current count
     */
    int getValue() {
        return this.number;
    };
}
