public class LongestPalindromicSubstring {

    public static String longestPalindrome(String s) {
        int maxLength = 0;
        String longestPalindrome = "";

        for (int i = 0; i < s.length(); i++) {
            // Check for odd-length palindromes with s.charAt(i) as the center
            String oddPalindrome = expandAroundCenter(s, i, i);
            if (oddPalindrome.length() > maxLength) {
                maxLength = oddPalindrome.length();
                longestPalindrome = oddPalindrome;
            }

            // Check for even-length palindromes with s.charAt(i) and s.charAt(i+1) as the center
            String evenPalindrome = expandAroundCenter(s, i, i + 1);
            if (evenPalindrome.length() > maxLength) {
                maxLength = evenPalindrome.length();
                longestPalindrome = evenPalindrome;
            }
        }

        return longestPalindrome;
    }

    private static String expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Return the substring from left+1 to right-1 because the while loop ended at non-matching indices
        return s.substring(left + 1, right);
    }

    public static void main(String[] args) {
        // Case 1
        String input = "babad";
        String expected = "bab";

        String actualOutput = longestPalindrome(input);

        stringifyCase(input, expected, actualOutput);

        // Case 2
        input = "cbbd";
        expected = "bb";

        actualOutput = longestPalindrome(input);

        stringifyCase(input, expected, actualOutput);

        // My case
        input = "blablaanavolimilovanableh";
        expected = "anavolimilovana";

        actualOutput = longestPalindrome(input);

        stringifyCase(input, expected, actualOutput);
    }

    public static String stringifyCase(String testString, String expected, String actualOutput) {
        StringBuilder sb = new StringBuilder();
        sb
                .append("String = ")
                .append(testString)
                .append("\n")
                .append("Expected = ")
                .append(expected)
                .append("\n")
                .append("Actual Output = ")
                .append(actualOutput)
                .append("\n")
                .append("\n")
                .append("Case passed ? ")
                .append(expected.equals(actualOutput))
                .append("\n--------------------------\n");

        System.out.println(sb);

        return sb.toString();
    }

}
