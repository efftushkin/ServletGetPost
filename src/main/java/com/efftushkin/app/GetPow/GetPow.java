package com.efftushkin.app.GetPow;

import java.util.HashMap;
import java.util.Set;

public final class GetPow {
    private GetPow() {
    }

    public static double getPow(double number, int exponent) {
        if (exponent == 0) {
            return 1;
        }

        if (number == 0) {
            return 0;
        }

        int sign = 1;

        if (exponent < 0) {
            sign = -1;
            exponent = -exponent;
        }

        HashMap<Integer, Double> cache = new HashMap<>();

        int currExp = 1;

        cache.put(currExp, number);

        int maxExpCached = currExp;

        Set<Integer> keys = null;

        while (currExp < exponent) {
            int diffExponent = exponent - currExp;

            if (diffExponent >= maxExpCached) {
                number = multiply(number, cache.get(maxExpCached));

                currExp += maxExpCached;
                cache.put(currExp, number);
                maxExpCached = currExp;
            } else {
                if (cache.containsKey(diffExponent)) {
                    return negativeExp(multiply(number, cache.get(diffExponent)), sign);
                }

                if (keys == null) {
                    keys = cache.keySet();
                }

                int maxExpFromCache = 1;

                for (int key : keys) {
                    if (key > maxExpFromCache && key <= diffExponent) {
                        maxExpFromCache = key;
                    }
                }

                number = multiply(number, cache.get(maxExpFromCache));

                currExp += maxExpFromCache;
            }
        }

        return negativeExp(number, sign);
    }

    private static double multiply(double number, double multiplier) {
        System.out.println("Mutltiplying: " + number + " & " + multiplier);

        return number * multiplier;
    }

    private static double negativeExp(double number, int sign) {
        if (sign > 0) {
            return number;
        }

        return 1 / number;
    }
}
