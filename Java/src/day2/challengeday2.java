package day2;

public class challengeday2 {

    public static void main(String[] args) {

        displayArr(orderEvenBeforeOdd(new int[] { 1, 2, 3, 4, 5 }));
        displayArr(addOneplus(new int[] { 1, 2, 9 }));
        System.out.println("isPalindrome : " + isPalindrome(new String[] { "true", "true", "aa" }));
        // soal4(7);
    }

    public static void displayArr(int[] n) {
        for (int i : n) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /*
     * soal 1
     * input : [3,4,5,2,10]
     * output : [2,4,10,3,5]
     */
    public static int[] orderEvenBeforeOdd(int[] n) {

        for (int i = 1; i < n.length; i++) {
            if (n[i] % 2 == 0) {
                int temp = n[i];
                int j = i;
                while (j > 0 && n[j - 1] % 2 != 0) {
                    n[j] = n[j - 1];
                    j--;
                }
                n[j] = temp;
            }
        }

        return n;
    }

    /*
     * soal 2
     * input :["donout","king","donout"]
     * output : true
     * 
     * input : ["true","false","false"]
     * output : false
     */

    public static boolean isPalindrome(String[] arr) {

        for (int i = 0; i < arr.length / 2; i++) {
            if (!arr[i].equals(arr[arr.length - i - 1])) {
                return false;
            }
        }
        return true;
    }

    /*
     * soal 3
     * input : [1,3,2,4]
     * output : [1,3,2,5]
     */

    public static int[] addOneplus(int[] n) {

        for (int i = n.length - 1; i >= 0; i--) {
            if (n[i] < 9) {
                n[i]++;
                return n;
            }
            n[i] = 0;
        }
        int[] result = new int[n.length + 1];
        result[0] = 1;

        return result;
    }

    // soal 3 dan 4 ada di matrix

    // public static void soal4(int n) {
    // for (int i = 0; i < n; i++) {
    // for (int j = 0; j < n; j++) {
    // if (i == 0) {
    // System.out.print(j + "\t");
    // } else if (j == 0) {
    // System.out.print(i + "\t");
    // } else if (i == n - 1) {
    // System.out.print((n - 1) + j + "\t");
    // } else if (j == n - 1) {
    // System.out.print((n - 1) + i + "\t");
    // } else {
    // System.out.print("\t");
    // }
    // }
    // System.out.println();
    // }
    // }
}
