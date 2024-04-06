import java.util.Set;
import java.util.TreeSet;

public class LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        // So the idea is to use two pointers, 'start' and 'end' to represent the start and end of substring
        // and a Set to hold already-seen characters
        //
        // we will iterate through the string by moving the 'end' pointer, and for each character:
        // if the character is not in the substring, do end++ and add to the set
        // if character is in the substring, remove from set and do start++

        Set<Character> seenCharacters = new TreeSet<>();
        int start = 0, end = 0;
        int maxLength = 0; //retVal

        while (end < s.length() - 1) {
            Character endCharacter = s.charAt(end);
            if (!seenCharacters.contains(endCharacter)) {
                seenCharacters.add(endCharacter);
                int newLength = (end - start) + 1;

                if (newLength > maxLength) {
                    maxLength = newLength;
                }

                end++;
            } else {
                char startCharacter = s.charAt(start);
                seenCharacters.remove(startCharacter);
                start++;
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        // Case 1
        String testString = "abcabcbb";
        int expected = 3;

        int actualOutput = lengthOfLongestSubstring(testString);

        stringifyCase(testString, expected, actualOutput);

        // case 2
        testString = "bbbbb";
        expected = 1;

        actualOutput = lengthOfLongestSubstring(testString);

        stringifyCase(testString, expected, actualOutput);

        // case 3
        testString = "pwwkew";
        expected = 3;

        actualOutput = lengthOfLongestSubstring(testString);

        stringifyCase(testString, expected, actualOutput);
    }

    public static String stringifyCase(String testString, int expected, int actualOutput) {
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
                .append(expected == actualOutput)
                .append("\n--------------------------\n");

        System.out.println(sb);

        return sb.toString();
    }

}
