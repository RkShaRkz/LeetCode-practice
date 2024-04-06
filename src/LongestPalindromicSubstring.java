import java.util.Set;
import java.util.TreeSet;

public class LongestPalindromicSubstring {

    public static String longestPalindrome(String s) {
        Set<Character> seenCharacters = new TreeSet<>();
        int start = 0, end = 0;
        int maxLength = 0; //retVal

        StringBuilder sb = new StringBuilder();

        while (end < s.length() - 1) {
            Character endCharacter = s.charAt(end);
            if (!seenCharacters.contains(endCharacter)) {
                seenCharacters.add(endCharacter);
                int newLength = (end - start) + 1;

                if (newLength > maxLength) {
                    maxLength = newLength;
                }

                sb.append(endCharacter);

                end++;
            } else {
                // We have already seen this character
                // this means two things:
                // 1. we have found a potential palindrome
                // 2. we need to increment the start and remove it (the startCharacter) from the set and our substring
                // in the case we have not found a palindrome
                char startCharacter = s.charAt(start);
                seenCharacters.remove(startCharacter);

                // Append the already-seen 'end' character
                sb.append(endCharacter);

                // Check for palindrome
                String string = sb.toString();
                if (sb.length() > 1 && string.equalsIgnoreCase(sb.reverse().toString())) {
                    return sb.toString();
                } else {
                    // No palindrome, revert the stringbuilder to original form
                    sb.reverse();
                    // Since we have not found a palindrome, delete the 'start' character and keep on looking
                    sb.deleteCharAt(start);
                    // recheck
                    string = sb.toString();
                    if (sb.length() > 1 && string.equalsIgnoreCase(sb.reverse().toString())) {
                        return sb.toString();
                    } else {
                        // revert since it wasn't a palindrome
                        sb.reverse();
                    }
                }

                start++;
            }
        }

        throw new IllegalStateException("Should not reach this");
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
