public class StringToInteger {
    public static int myAtoi(String s) {
        boolean isPositive = true;
        long result = 0;
        int currentIndex = 0;
        boolean signRead = false;

        // First swallow all leading whitespace
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);

            // Ignore trailing whitespace
            if (isWhitespace(letter)) {
                continue;
            } else {
                currentIndex = i;
                break;
            }
        }

        for (int i = currentIndex; i < s.length(); i++) {
            char letter = s.charAt(i);
            // Now look for sign
            if (isSign(letter)) {
                // If sign was already encountered, make sure to return 0 since the input is invalid
                if (signRead) return 0;
                // parse it
                isPositive = (letter == '+');
                signRead = true;
                continue;
            }

            // If we already reached digits, then roll to previous character and let the digit-parsing loop take over
            // the parsing from the start
            if (isDigit(letter)) {
                currentIndex = i;
                break;
            } else {
                // Since letters came before the sign or the number, we should ignore everything...
                return 0;
            }
        }

//        int lastOverflowDigit = (int) (Long.MAX_VALUE % 10);
//        long overflowWarning = Long.MAX_VALUE / 10;

        // Digit reading loop
        for (int i = currentIndex; i < s.length(); i++) {
            // parse digit
            char letter = s.charAt(i);

            // If it's not a digit, break since that's what the problem statement says.
            // If it is a digit, parse them until a non-digit shows up
            if (!isDigit(letter)) break;

            // Check whether we'll get overflowed, and clamp if we would
            int letterValue = letter - '0';
//            if (result >= overflowWarning && letterValue >= lastOverflowDigit) {
//                result = Long.MAX_VALUE;
//            } else {
            if (isOverflow(s)) {
                result = Long.MAX_VALUE;
            } else if (isUnderflow(s)) {
                result = Long.MIN_VALUE;
            } else {
                result = result * 10 + letterValue;
            }
        }

        // Since all parsing has been done, apply the sign
        if (isPositive) {
            result = Math.abs(result);
        } else {
            result = Math.negateExact(result);
        }

        // Stay within bounds
        if (result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (result < Integer.MIN_VALUE) return Integer.MIN_VALUE;

        // Return the parsed integer.
        return (int) result;
    }

    public static boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\n';
    }

    public static boolean isSign(char c) {
        return c == '-' || c == '+';
    }

    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isOverflow(String s) {
        String numericString = extractNumericPart(s);

        boolean retVal = false;
        try {
            long value = Long.parseLong(numericString);
            retVal = (value > Long.MAX_VALUE);
        } catch (NumberFormatException nfe) {
            retVal = true;
        }

        return retVal;
    }

    public static boolean isUnderflow(String s) {
        String numericString = extractNumericPart(s);

        boolean retVal = false;
        try {
            long value = Long.parseLong(numericString);
            retVal = (value < Long.MIN_VALUE);
        } catch (NumberFormatException nfe) {
            retVal = true;
        }

        return retVal;
    }

    private static String extractNumericPart(String input) {
        StringBuilder numericPart = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                numericPart.append(c);
            } else if (!numericPart.isEmpty()) {
                // Stop extracting if non-numeric characters are encountered after numeric part
                break;
            }
        }
        return numericPart.toString();
    }

    public static void main(String[] args) {
        // Case 1
        String input = "42";
        int expected = 42;

        int actualOutput = myAtoi(input);

        stringifyCase(input, expected, actualOutput);

        // Case 2
        input = "   -42";
        expected = -42;

        actualOutput = myAtoi(input);

        stringifyCase(input, expected, actualOutput);

        // Case 3
        input = "4193 with words";
        expected = 4193;

        actualOutput = myAtoi(input);

        stringifyCase(input, expected, actualOutput);

        // Failed case
        input = "words and 987";
        expected = 0;

        actualOutput = myAtoi(input);

        stringifyCase(input, expected, actualOutput);

        // Failed case 2
        input = ".1";
        expected = 0;

        actualOutput = myAtoi(input);

        stringifyCase(input, expected, actualOutput);

        // Failed case 3
        input = "+-12";
        expected = 0;

        actualOutput = myAtoi(input);

        stringifyCase(input, expected, actualOutput);

        // Failed case 3
        input = "9223372036854775808";
        expected = Integer.MAX_VALUE;

        actualOutput = myAtoi(input);

        stringifyCase(input, expected, actualOutput);

        // Failed case 4
        input = "  +  413";
        expected = 0;

        actualOutput = myAtoi(input);

        stringifyCase(input, expected, actualOutput);

        // Failed case 5
        input = "10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000522545459";
        expected = Integer.MAX_VALUE;

        actualOutput = myAtoi(input);

        stringifyCase(input, expected, actualOutput);
    }

    public static String stringifyCase(String testInput, int expected, int actualOutput) {
        StringBuilder sb = new StringBuilder();
        sb
                .append("Input = ")
                .append(testInput)
                .append("\n")
                .append("Expected = ")
                .append(expected)
                .append("\n")
                .append("Actual Output = ")
                .append(actualOutput)
                .append("\n")
                .append("\n")
                .append("Case passed ? ")
                .append(expected == actualOutput)
                .append("\n--------------------------\n");

        System.out.println(sb);

        return sb.toString();
    }
}
