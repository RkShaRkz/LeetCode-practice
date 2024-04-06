public class ZigZagConversion {

    public static String convert(String s, int numRows) {
        // Initialize the stringbuilders
        StringBuilder[] builders = new StringBuilder[numRows];
        boolean increment = true;

        // Initialize each builder
        for (int i = 0; i < numRows; i++) {
            builders[i] = new StringBuilder();
        }

        int builderIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            builders[builderIndex].append(s.charAt(i));
            if (increment) {
                builderIndex++;
            } else {
                builderIndex--;
            }

            // manage limits
            if (builderIndex >= builders.length) {
                increment = false;
//                builderIndex = builderIndex % builders.length;
                // Since we need to change direction, and decrementing brings us back to where we started from
                // we need to decrement by two to end up where we want to be (one step in the 'changed' direction)
                builderIndex = builderIndex - 2;
            }
            if (builderIndex < 0) {
                increment = true;
//                builderIndex = 0;
                // Similarly, we need to change direction and go one step towards where we want to be
                builderIndex = builderIndex + 2;
            }

            // Edgecase - to avoid bothering with clamping etc, this will do the trick fine
            if (numRows == 1) {
                builderIndex = 0;
            }
        }

        StringBuilder retVal = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            retVal.append(builders[i]);
        }

        return retVal.toString();
    }

    public static void main(String[] args) {
        // Case 1
        String input = "PAYPALISHIRING";
        int numRows = 3;
        String expected = "PAHNAPLSIIGYIR";

        String actual = convert(input, numRows);

        stringifyCase(input, numRows, expected, actual);

        // Case 2
        input = "PAYPALISHIRING";
        numRows = 4;
        expected = "PINALSIGYAHRPI";

        actual = convert(input, numRows);

        stringifyCase(input, numRows, expected, actual);

        // Case 3
        input = "A";
        numRows = 1;
        expected = "A";

        actual = convert(input, numRows);

        stringifyCase(input, numRows, expected, actual);

        // Case 4
        input = "AB";
        numRows = 1;
        expected = "AB";

        actual = convert(input, numRows);

        stringifyCase(input, numRows, expected, actual);
    }

    public static String stringifyCase(String testString, int numRows, String expected, String actualOutput) {
        StringBuilder sb = new StringBuilder();
        sb
                .append("String = ")
                .append(testString)
                .append("\n")
                .append("Num Rows = ")
                .append(numRows)
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
