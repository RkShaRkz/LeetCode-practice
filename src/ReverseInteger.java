public class ReverseInteger {
    public static int reverse(int x) {
        long temp = 0;

        int digit = 0;
        while (x != 0) {
            temp = temp * 10;
            digit = x % 10;
            temp += digit;
            x = x / 10;
        }

        if (temp > Integer.MAX_VALUE || temp < Integer.MIN_VALUE) return 0;
        return (int) temp;
    }

    public static void main(String[] args) {
        //case 1
        int input = 123;
        int expected = 321;

        int actualOutput = reverse(input);

        stringifyCase(input, expected, actualOutput);

        //case 2
        input = -123;
        expected = -321;

        actualOutput = reverse(input);

        stringifyCase(input, expected, actualOutput);

        // case 3
        input = 120;
        expected = 21;

        actualOutput = reverse(input);

        stringifyCase(input, expected, actualOutput);

        // my case
        input = Integer.MIN_VALUE + 1; //0x80000000 + 1 = 0x80000001
        expected = 0; //MIN_VALUE is -2147483647 and when that is reversed we get -7463847412 which is out-of-bounds

        actualOutput = reverse(input);

        stringifyCase(input, expected, actualOutput);

        // my case 2
        input = Integer.MAX_VALUE - 2; //0x7fffffff - 2 = 0x7ffffffd
        expected = 0; //MAX_VALUE is 2147483645 and when that is reversed we get 5463847412 which is out-of-bounds

        actualOutput = reverse(input);

        stringifyCase(input, expected, actualOutput);
    }

    public static String stringifyCase(int testInput, int expected, int actualOutput) {
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
