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
                    mergedArray[i + j] = val1;

                    // do sum and median parts
                    totalSum += val1;
                    i++;
                    median = (double) totalSum / (i + j);
                } else {
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

    public static double findMedianSortedArrays_O_logn(int[] nums1, int[] nums2) {
        /**
         * To achieve O(log(m+n)) time complexity for finding the median of two sorted arrays, you can use the binary search approach.
         * Here's a general outline of how you can do it:
         *
         * 1. Ensure that the combined length of both arrays (m + n) is even or odd. If it's odd, finding the median is straightforward.
         * If it's even, you'll need to find the median of two middle elements.
         *
         * 2. Perform binary search on the smaller array (let's say nums1) to partition it into two halves such that:
         * - Elements on the left side are smaller than elements on the right side.
         * - The total number of elements on the left side is equal to or one less than the total number of elements on the right side.
         *
         * 3. Determine the corresponding partition in the other array (nums2) based on the partition in nums1.
         *
         * 4. Check if the partitioning is correct:
         * - If the partitioning is correct, calculate the median based on the values at the partition boundaries.
         * - If the partitioning is incorrect, adjust the partitioning and continue the binary search.
         *
         * 5. Repeat steps 2-4 until the correct partitioning is found.
         */

        int m = nums1.length;
        int n = nums2.length;

        // Ensure we're using the smaller one as nums1 (i'm not really getting *why* exactly we want the first array to be smaller though)
        if (m > n) {
            return findMedianSortedArrays_O_logn(nums2, nums1);
        }

        int totalLength = m + n;
        // Initializing binary search boundaries
        int low = 0;
        int high = m;

        while (low <= high) {
            int partitionNums1 = (low + high) / 2;
            int partitionNums2 = (totalLength + 1) / 2 - partitionNums1;

            int maxLeftNums1 = (partitionNums1 != 0) ? nums1[partitionNums1 - 1] : Integer.MIN_VALUE;
            int minRightNums1 = (partitionNums1 != m) ? nums1[partitionNums1] : Integer.MAX_VALUE;

            int maxLeftNums2 = (partitionNums2 != 0) ? nums2[partitionNums2 - 1] : Integer.MIN_VALUE;
            int minRightNums2 = (partitionNums2 != n) ? nums2[partitionNums2] : Integer.MAX_VALUE;

            if (maxLeftNums1 <= minRightNums2 && maxLeftNums2 <= minRightNums1) {
                if (totalLength % 2 == 0) {
                    return (Math.max(maxLeftNums1, maxLeftNums2) + Math.min(minRightNums1, minRightNums2)) / 2.0;
                } else {
                    return Math.max(maxLeftNums1, maxLeftNums2);
                }
            } else if (maxLeftNums1 > minRightNums2) {
                high = partitionNums1 - 1; // Partition is too far to the right, adjust high
            } else {
                low = partitionNums1 + 1; // Partition is too far to the left, adjust low
            }
        }

        // Should never reach here
        return -1;
    }

    public static void main(String[] args) {
        /**
         * Original O(n) version
         */
        System.out.println(">>>>>>>>>> O(n) version <<<<<<<<<<");
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        double expected = 2.0f;

        double actualOutput = findMedianSortedArrays_O_n(nums1, nums2);

        stringifyCase(nums1, nums2, expected, actualOutput);

        nums1 = new int[]{1, 2};
        nums2 = new int[]{3, 4};
        expected = 2.5f;

        actualOutput = findMedianSortedArrays_O_n(nums1, nums2);

        stringifyCase(nums1, nums2, expected, actualOutput);

        /**
         * The desired O(log(n)) version
         */
        System.out.println(">>>>>>>>>> O(log(n)) version <<<<<<<<<<");
        nums1 = new int[]{1, 3};
        nums2 = new int[]{2};
        expected = 2.0f;

        actualOutput = findMedianSortedArrays_O_logn(nums1, nums2);

        stringifyCase(nums1, nums2, expected, actualOutput);

        nums1 = new int[]{1, 2};
        nums2 = new int[]{3, 4};
        expected = 2.5f;

        actualOutput = findMedianSortedArrays_O_logn(nums1, nums2);

        stringifyCase(nums1, nums2, expected, actualOutput);

        // My case - test if it works for nonconsecutive arrays
        nums1 = new int[]{1, 3, 5, 7};
        nums2 = new int[]{2, 6, 8, 12};
        expected = 5.5f;

        actualOutput = findMedianSortedArrays_O_logn(nums1, nums2);

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
