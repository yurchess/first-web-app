package mitroshin.business;

import java.util.ArrayList;
import java.util.List;

public final class NumberGenerator {
    private static final int SIZE = 10;

    private NumberGenerator() {}

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
