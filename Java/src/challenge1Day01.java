public class challenge1Day01 {

    public static void main(String[] args) {
        // System.out.println("hello world");
        int num = 5;

        System.out.println();
        System.out.print("soal no 1 " + "-".repeat(100));
        System.out.println();
        // soal no 1
        int angka = 1;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                System.out.print(angka++ + "\t");
            }
            System.out.println();
        }
        System.out.println();
        System.out.print("soal no 2 " + "-".repeat(100));
        System.out.println();

        // soal no 2
        for (int i = 0; i < num; i++) {
            int angkaa = i + 1;
            for (int j = 0; j <= i; j++) {
                System.out.print(angkaa++ + "\t");
            }
            System.out.println();
        }

        System.out.println();
        System.out.print("soal no 3 " + "-".repeat(100));
        System.out.println();

        // soal no 3
        for (int i = 0; i < num; i++) {
            // int angkaa = i + 1;
            for (int j = 0; j < num - i; j++) {
                // int num = j + 1;
                System.out.print(j + i + 1 + "\t");
            }
            System.out.println();
        }

        System.out.println();
        System.out.print("soal no 4 " + "-".repeat(100));
        System.out.println();

        // soal no 4
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (i <= j) {
                    System.out.print(j + 1 + "\t");
                } else {
                    System.out.print(" \t");
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.print("soal no 5 " + "-".repeat(100));
        System.out.println();
        // soal no 5
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (i == j) {
                    System.out.print((i + 1) + "\t");
                } else if (i < j) {

                    System.out.print("10" + "\t");
                } else {
                    System.out.print("20" + "\t");
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.print("soal no 6 " + "-".repeat(100));
        System.out.println();
        // soal no 6
        for (int i = num; i > 0; i--) {
            for (int j = num; j > 0; j--) {
                if (i == j) {
                    System.out.print((i) + "\t");
                } else if (i > j) {
                    System.out.print("20\t");
                } else {
                    System.out.print("10\t");
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.print("soal no 7 " + "-".repeat(100));
        System.out.println();

        // soal no 7
        int n = 8;
        for (int i = 0; i < n; i++) {
            int numm = n - i;
            for (int j = numm; j >= 1; j--) {
                System.out.print(j + "\t");
            }
            for (int j = 2; j <= numm; j++) {
                System.out.print(j + "\t");
            }

            System.out.println();
        }

        System.out.println();
        System.out.print("soal no 9 " + "-".repeat(100));
        System.out.println();

        // soal no 9
        for (int i = 0; i < num; i++) {
            int angka9 = num;
            for (int j = 0; j < num; j++) {
                if (i % 2 == 0) {
                    System.out.print(angka9-- + "\t");
                } else {
                    System.out.print((j + 1) + "\t");
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.print("soal no 10 " + "-".repeat(100));
        System.out.println();

        // soal no 10

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (i % 2 == 0) {
                    if (j % 2 != 0) {
                        System.out.print(j + 1 + "\t");
                    } else {
                        System.out.print("-" + "\t");
                    }
                } else {
                    if (j % 2 == 0) {
                        System.out.print((j + 1) + "\t");
                    } else {
                        System.out.print("-" + "\t");
                    }
                }

            }
            System.out.println();
        }

    }

}
