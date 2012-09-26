/**
Written by Mike O'Beirne (michael.obeirne@gmail.com)

Passes test data but does not pass the ACM ICPC LiveJudge due to a Runtime
Error. Can't quite figure out what the problem is exactly. :(
**/

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

public class Stringer {

    static HashMap<Integer, BigInteger> factorial = new HashMap<Integer, BigInteger>();

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int numLetters = in.nextInt();

        while (numLetters != 0) {

            long index = in.nextLong() + 1;

            int totalLetters = 0;
            int[] letters = new int[numLetters + 1];

            for (int i = 0; i < numLetters; i++) {
                letters[i] = in.nextInt();
                totalLetters += letters[i];
            }

            letters[numLetters] = totalLetters;

            System.out.println(solve(index, letters, ""));

            numLetters = in.nextInt();
        }

    }

    static String solve(long index, int[] letters, String ans) {

        if (letters[letters.length - 1] == 0) {
            return ans;
        }

        // We're guaranteed to use a letter
        letters[letters.length - 1]--;

        for (int i = 0; i < letters.length - 1; i++) {

            if (letters[i] == 0) {
                continue;
            }

            letters[i]--;
            char current = (char) ('a' + i);

            long possibleWords = numWords(letters[letters.length - 1],
                    letters);

            if (index - possibleWords == 0) {
                return ans + current + restInPlace(letters);
            }

            if (index - possibleWords <= 0) {

                return solve(index, letters, ans + current);
            }
            // Return to previous state
            else {
                index -= possibleWords;
                letters[i]++;
            }
        }

        return null;

    }

    static String restInPlace(int[] letters) {

        String ans = "";

        for (int i = 0; i < letters.length - 1; i++) {
            for (int j = 0; j < letters[i]; j++) {
                ans += (char) ('a' + i);
            }
        }

        return ans;
    }

    static long numWords(int N, int[] letters) {

        BigInteger ans = factorial(N);
        for (int i = 0; i < letters.length - 1; i++) {
            ans = ans.divide(factorial(letters[i]));
        }

        return ans.longValue();

    }

    static BigInteger factorial(int N) {

        if (N == 0) {
            return BigInteger.ONE;
        }

        if (factorial.containsKey(N)) {
            return factorial.get(N);
        }

        else {

            BigInteger current = BigInteger.ONE;

            for (int i = 1; i <= N; i++) {

                current = current.multiply(BigInteger.valueOf(i));

                factorial.put(i, current);
            }

            return current;
        }

    }
}
