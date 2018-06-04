package mitroshin.business;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code NumberGenerator} class contains a static method {@code generate}
 * which generates a random number consisting of unique digits
 */
public final class NumberGenerator {
    private static final int SIZE = 10;

    private NumberGenerator() {}

    /**
     * Returns generated random number consisting of unique digits
     * @param size
     *        Digits amount in generated number.
     *        Has to be less than 10.
     * @return A generated random number as an {@code int[]} array where each element is a unique digit.
     *
     * @throws IllegalArgumentException
     *         If the {@code size} is greater than 10.
     */
    public static int[] generate(int size) {
        if (size > SIZE) {
            throw new IllegalArgumentException("parameter 'size' is bigger than " + SIZE);
        }

        int[] result = new int[size];
        List<Integer> digits = getDigitsList();
        for (int i = 0; i < size; i++) {
            int choosenIndex = (int) (Math.random() * digits.size());
            result[i] = digits.get(choosenIndex);
            digits.remove(choosenIndex);
        }

        return result;
    }

    private static List<Integer> getDigitsList() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            result.add(i);
        }
        return result;
    }
}
