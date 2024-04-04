import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> indicesMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // calculate the complement
            int complement = target - nums[i];
            if (indicesMap.containsKey(complement)) {
                return new int[]{indicesMap.get(complement), i};
            }
            // If the indicesMap already contained this number
            // such as in the case of
            // { 0, 1, 2, 3, 2, 3, 4 }
            // requiring the target of 6
            // we should not expect the first occurance to be in the solution
            indicesMap.put(nums[i], i);
        }

        return new int[0];
    }

    public static void main(String[] args) {
        // First case
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] expected = {0, 1};

        int[] actualOutput = twoSum(nums, target);

        stringifyCase(nums, target, expected, actualOutput);

        // Second case
        nums = new int[]{3, 2, 4};
        target = 6;
        expected = new int[]{1, 2};

        actualOutput = twoSum(nums, target);

        stringifyCase(nums, target, expected, actualOutput);

        // Third case
        nums = new int[]{3, 3};
        target = 6;
        expected = new int[]{0, 1};

        actualOutput = twoSum(nums, target);

        stringifyCase(nums, target, expected, actualOutput);

        // My case 1
        nums = new int[]{1, 2, 3, 3, 4, 5, 3, 3, 4, 5, 6, 8};
        target = 6;
        expected = new int[]{2, 3};

        actualOutput = twoSum(nums, target);

        stringifyCase(nums, target, expected, actualOutput);

        // My case 2
        nums = new int[]{1, 2, 3, 3, 4, 5, 3, 3, 4, 5, 6, 8, 11, 24, 21};
        target = 12;
//        expected = new int[]{4, 11};
        // As noted before, the first occurance will be overwritten
        // so indices [4,11] won't be the solution but rather [8,11] will
        expected = new int[]{8, 11};

        actualOutput = twoSum(nums, target);

        stringifyCase(nums, target, expected, actualOutput);
    }

    public static String stringifyCase(int[] nums, int target, int[] expected, int[] output) {
        StringBuilder sb = new StringBuilder();
        sb
                .append("Nums = ")
                .append(Arrays.toString(nums))
                .append("\n")
                .append("Target = ")
                .append(target)
                .append("\n")
                .append("Expected = ")
                .append(Arrays.toString(expected))
                .append("\n")
                .append("Output = ")
                .append(Arrays.toString(output))
                .append("\n")
                .append("\n")
                .append("Case passed ? ")
                .append(Arrays.equals(expected, output))
                .append("\n--------------------------\n");

        System.out.println(sb.toString());

        return sb.toString();
    }
}
