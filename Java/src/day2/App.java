package day2;

public class App {

    public static void main(String[] args) {
        // System.out.println("hello world");

        displayArray(powNumber(4));
        displayArray(oddNumber(4));
        displayArray(fibonacci(5));
        displayArray(rotateArr(new int[] { 1, 5, 3, 4, 9 }));
    }

    public static void displayArray(int[] arr) {
        for (int i : arr) {
            System.out.print((i + " "));
        }
        System.out.println();
    }

    // soal 1
    public static int[] powNumber(int n) {

        int[] newArr = new int[n];
        for (int i = 0; i < n; i++) {
            newArr[i] = (int) Math.pow(2, i);
        }
        return newArr;

    }

    // soal 2
    public static int[] oddNumber(int n) {

        int[] newArr = new int[n];
        for (int i = 0; i < n; i++) {
            newArr[i] = (2 * i) + 1;
        }
        return newArr;
    }

    // soal 3
    public static int[] fibonacci(int n) {
        int[] newArr = new int[n];
        newArr[0] = 1;
        newArr[1] = 1;
        for (int i = 2; i < n; i++) {
            newArr[i] = newArr[i - 2] + newArr[i - 1];
        }
        return newArr;
    }

    // soal 4
    public static int[] rotateArr(int[] n) {
        int temp = n[0];
        // int[] newArr = new int[n];
        for (int i = 1; i < n.length; i++) {
            n[i - 1] = n[i];
        }
        n[n.length - 1] = temp;
        return n;
    }

    // soal 5

}
