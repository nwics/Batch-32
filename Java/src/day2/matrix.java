package day2;

public class matrix {

    public static void main(String[] args) {

        int[][] hasil = matrixDiagonal(5);
        displayMatrix(hasil);

        System.out.println();
        int[][] result = customMatrix(7);
        displayMatrix(result);

        System.out.println();
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

    public static int[][] matrixDiagonal(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j) {
                    matrix[i][j] = i + 1;
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
