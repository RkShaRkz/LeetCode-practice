public class PalindromeNumber {

    public static boolean isPalindrome(int x) {
        // Handle negative numbers since they're not palindromes
        if (x < 0) return false;

        // The idea is to build the number "in reverse" and then compare
        // the new number with the original number. If they match - it's a palindrome
        int result = 0;
        int copyOfX = x;
        while (copyOfX > 0) {
            int digit = copyOfX % 10;
            result = result * 10 + digit;
            copyOfX = copyOfX / 10;
        }

        // Finally, check
        return result == x;
    }

    public static void main(String[] args) {
        // Case 1
        int input = 121;
        boolean expected = true;

        boolean actualOutput = isPalindrome(input);

        stringifyCase(input, expected, actualOutput);

        // Case 2
        input = -121;
        expected = false;

        actualOutput = isPalindrome(input);

        stringifyCase(input, expected, actualOutput);

        // Case 3
        input = 10;
        expected = false;

        actualOutput = isPalindrome(input);

        stringifyCase(input, expected, actualOutput);
    }

    public static String stringifyCase(int testInput, boolean expected, boolean actualOutput) {
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
