import java.util.Arrays;

public class AddTwoNumbers {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultList = new ListNode();
        ListNode currentNode = resultList;
        ListNode previousNode = null;
        boolean carry = false;

        while (true) {
            // If we reached the end - bail out
            if (l1 == null && l2 == null) {
                // If we still have carry, make a new node
                if (carry) {
                    ListNode carryNode = new ListNode(1);
                    carry = false;
                    previousNode.next = carryNode;
                }
                break;
            }

            int val1 = (l1 != null) ? (l1.val) : 0;
            int val2 = (l2 != null) ? (l2.val) : 0;
            int result = (carry) ? (val1 + val2 + 1) : (val1 + val2);
            carry = false;

            if (result >= 10) {
                result = remainderOfTen(result);
                carry = true;
            }

            // Update current and previous node
            currentNode.val = result;
            previousNode = currentNode;

            // Update the currentNode to a new node
            currentNode = new ListNode();

            // Move on to the next member of both lists if we haven't reached the end already
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }

            // Finally, nake previous node point to the current (still blank) node if we're not at the end of both lists
            if (l1 != null || l2 != null) {
                previousNode.next = currentNode;
            }
        }

        return resultList;
    }

    private static int remainderOfTen(int number) {
        int retVal;
        if (number >= 10) {
            retVal = (number - 10);
        } else {
            retVal = number;
        }

        return retVal;
    }

    public static void main(String[] args) {
        ListNode list1;
        ListNode list2;
        int[] expected;
        ListNode output;

        // First case
        list1 = ListNode.generateList(new int[]{2, 4, 3});
        list2 = ListNode.generateList(new int[]{5, 6, 4});
        expected = new int[]{7, 0, 8};

        output = addTwoNumbers(list1, list2);

        stringifyCase(list1, list2, expected, output);

        // Second case
        list1 = ListNode.generateList(new int[]{0});
        list2 = ListNode.generateList(new int[]{0});
        expected = new int[]{0};

        output = addTwoNumbers(list1, list2);

        stringifyCase(list1, list2, expected, output);

        // third case
        list1 = ListNode.generateList(new int[]{9, 9, 9, 9, 9, 9, 9});
        list2 = ListNode.generateList(new int[]{9, 9, 9, 9});
        expected = new int[]{8, 9, 9, 9, 0, 0, 0, 1};

        output = addTwoNumbers(list1, list2);

        stringifyCase(list1, list2, expected, output);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public static ListNode generateList(int[] contents) {
            ListNode retVal = new ListNode();
            ListNode currentNode = retVal;
            ListNode previousNode = null;

            for (int i = 0; i < contents.length; i++) {
                currentNode.val = contents[i];
                previousNode = currentNode;
                currentNode = new ListNode();
                // make previous node point to new empty node for every item except the last
                if (i != contents.length - 1) {
                    previousNode.next = currentNode;
                }
            }

            return retVal;
        }
    }


    public static String stringifyCase(ListNode list1, ListNode list2, int[] expected, ListNode output) {
        StringBuilder sb = new StringBuilder();
        sb
                .append("L1 = ")
                .append(stringifyList(list1))
                .append("\n")
                .append("L2 = ")
                .append(stringifyList(list2))
                .append("\n")
                .append("Expected = ")
                .append(Arrays.toString(expected))
                .append("\n")
                .append("Output = ")
                .append(stringifyList(output))
                .append("\n")
                .append("\n")
                .append("Case passed ? ")
                .append(checkEquals(expected, output))
                .append("\n--------------------------\n");

        System.out.println(sb.toString());

        return sb.toString();
    }

    public static String stringifyList(ListNode list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean singleElementList = true;
        while (list != null) {
            sb.append(list.val).append(", ");
            list = list.next;
            singleElementList = false;
        }
        // we need to rewind builder by two and add a ']' for multi-element lists
        if (!singleElementList) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");


        return sb.toString();
    }

    public static boolean checkEquals(int[] expected, ListNode list) {
        // Presume true
        boolean retVal = true;
        int index = 0;
        while (list.next != null) {
            retVal = retVal && (expected[index] == list.val);
            // move on
            index++;
            list = list.next;
        }

        return retVal;
    }
}
