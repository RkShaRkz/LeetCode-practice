import java.util.Arrays;

public class MedianOfTwoSortedArrays {

    public static double findMedianSortedArrays_O_n(int[] nums1, int[] nums2) {
        int combinedLength = nums1.length + nums2.length;
        int[] mergedArray = new int[combinedLength];
        // We will use two indices, 'i' and 'j' to track where we are in each array
        // the member we're currently processing will always be equal to i+j
        int i = 0, j = 0;

        int totalSum = 0;
        double median = 0f;

        while ((i + j) < combinedLength) {
            int val1 = safeGet(nums1, i);
            int val2 = safeGet(nums2, j);

            int len1 = nums1.length;
            int len2 = nums2.length;
            // In order to correctly determine which array's element goes in next,
            // we need to perform some logic; namely if we've reached the end of
            // one array, we will not be using the smaller of the two.
            //
            // we use the smaller of the two only while we haven't reached the end of either array
            boolean reachedEnd1 = (i == len1);
            boolean reachedEnd2 = (j == len2);

            if (!reachedEnd1 && !reachedEnd2) {
                if (val1 < val2) {
//                mergedArray[i + j] = nums1[i];
                    mergedArray[i + j] = val1;

                    // do sum and median parts
                    totalSum += val1;
                    i++;
                    median = (double) totalSum / (i + j);
                } else {
//                mergedArray[i + j] = nums2[j];
                    mergedArray[i + j] = val2;

                    // do sum and median parts
                    totalSum += val2;
                    j++;
                    median = (double) totalSum / (i + j);
                }
            } else if (reachedEnd1) {
                // we will only use array2
                mergedArray[i + j] = val2;

                // do sum and median parts
                totalSum += val2;
                j++;
                median = (double) totalSum / (i + j);
            } else if (reachedEnd2) {
                // we will only use array1
                mergedArray[i + j] = val1;

                // do sum and median parts
                totalSum += val1;
                i++;
                median = (double) totalSum / (i + j);
            } else {
                // this should never happen, throw
                throw new IllegalStateException("this shouldn't be happening");
            }
        }

        return median;
    }

    private static int safeGet(int[] array, int index) {
        if (index >= array.length) {
            return array[array.length - 1];
        } else {
            return array[index];
        }
    }

    public double findMedianSortedArrays_O_logn(int[] nums1, int[] nums2) {
        return 0.0f;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        double expected = 2.0f;

        double actualOutput = findMedianSortedArrays_O_n(nums1, nums2);

        stringifyCase(nums1, nums2, expected, actualOutput);

        nums1 = new int[] {1,2};
        nums2 = new int[] {3,4};
        expected = 2.5f;

        actualOutput = findMedianSortedArrays_O_n(nums1, nums2);

        stringifyCase(nums1, nums2, expected, actualOutput);
    }

    public static String stringifyCase(int[] nums1, int[] nums2, double expected, double actualOutput) {
        StringBuilder sb = new StringBuilder();
        sb
                .append("Nums1 = ")
                .append(Arrays.toString(nums1))
                .append("\n")
                .append("Nums2 = ")
                .append(Arrays.toString(nums2))
                .append("\n")
                .append("Expected = ")
                .append(expected)
                .append("\n")
                .append("Output = ")
                .append(actualOutput)
                .append("\n")
                .append("\n")
                .append("Case passed ? ")
                .append(expected == actualOutput)
                .append("\n--------------------------\n");

        System.out.println(sb.toString());

        return sb.toString();
    }
}
