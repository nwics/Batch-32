package day2;

public class matrix {

    public static void main(String[] args) {

        System.out.println("soal 1 " + "-".repeat(50));
        int[][] hasil = matrixDiagonal(5);
        displayMatrix(hasil);

        System.out.println();
        System.out.println("soal 2 " + "-".repeat(50));
        int[][] hasil2 = matrixDiagona2l(5);
        displayMatrix(hasil2);

        System.out.println();
        System.out.println("soal 3 " + "-".repeat(50));
        int[][] result = customMatrix(7);
        displayMatrix(result);

        System.out.println();
        System.out.println("soal 4 " + "-".repeat(50));
        int[][] result3 = matrix3(8);
        displayMatrix(result3);

    }

    public static void displayMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.printf("%3s", matrix[row][col]);
            }
            System.out.println();
        }
    }

    // soal 1
    public static int[][] matrixDiagonal(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j) {
                    matrix[i][j] = i + 1;
                }
                if (i > j) {
                    matrix[i][j] = 20;
                } else if (j > i) {
                    matrix[i][j] = 10;
                }
            }
        }
        return matrix;
    }

    // soal 2
    public static int[][] matrixDiagona2l(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j) {
                    matrix[i][j] = n--;
                }
                if (i > j) {
                    matrix[i][j] = 10;
                } else if (j > i) {
                    matrix[i][j] = 20;
                }
            }
        }
        return matrix;
    }

    // soal 3
    public static int[][] customMatrix(int n) {
        int[][] matrix = new int[n][n];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0) {
                    matrix[i][j] = j;
                } else if (j == 0) {
                    matrix[i][j] = i;
                } else if (i == n - 1) {
                    matrix[i][j] = (n - 1) + j;
                } else if (j == n - 1) {
                    matrix[i][j] = (n - 1) + i;
                }
            }
        }

        return matrix;
    }

    // soal 4
    public static int[][] matrix3(int n) {

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            int sumRow = 0;
            for (int j = 0; j < n; j++) {

                if (i == n - 1 && j == n - 1) {
                    int total = 0;
                    for (int k = 0; k < n - 1; k++) {
                        total += matrix[n - 1][k];
                    }
                    matrix[i][j] = total;
                } else if (i == n - 1) {
                    int sum = 0;
                    for (int k = 0; k < n - 1; k++) {
                        sum += matrix[k][j];
                    }
                    matrix[i][j] = sum;
                }

                else if (j == n - 1) {
                    matrix[i][j] = sumRow;
                }

                else {
                    matrix[i][j] = i + j;
                    sumRow += matrix[i][j];
                }
            }

        }
        return matrix;
    }

}
