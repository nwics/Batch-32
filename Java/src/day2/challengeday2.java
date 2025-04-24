package day2;

public class challengeday2 {

    public static void main(String[] args) {

        System.out.println();
        System.out.println("soal 1 " + "-".repeat(50));
        displayArr(orderEvenBeforeOdd(new int[] { 1, 2, 3, 4, 5 }));

        System.out.println();
        System.out.println("soal 2 " + "-".repeat(50));
        System.out.println("isPalindrome : " + isPalindrome(new String[] { "true", "false", "false" }));

        System.out.println();
        System.out.println("soal 3 " + "-".repeat(50));
        displayArr(addOneplus(new int[] { 1, 2, 2 }));
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

        int indexGenap = 0;

        for (int i = 0; i < n.length; i++) {
            if (n[i] % 2 == 0) {
                int temp = n[i];
                n[i] = n[indexGenap];
                n[indexGenap] = temp;
                indexGenap++;
            }
        }
        for (int i = 0; i < indexGenap - 1; i++) {
            for (int j = 0; j < indexGenap - i - 1; j++) {
                if (n[j] > n[j + 1]) {
                    int temp = n[j];
                    n[j] = n[j + 1];
                    n[j + 1] = temp;
                }
            }
        }

        for (int i = indexGenap; i < n.length - 1; i++) {
            for (int j = indexGenap; j < n.length - 1 - (i - indexGenap); j++) {
                if (n[j] > n[j + 1]) {
                    int temp = n[j];
                    n[j] = n[j + 1];
                    n[j + 1] = temp;
                }
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

    // soal 4 dan 5 ada di matrix

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
