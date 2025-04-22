import java.util.HashMap;

public class challenge2Day01 {

    public static void main(String[] args) {
        // System.out.println("hello world");
        System.out.println("Reverse : " + Reverse(12));
        System.out.println("isPalindrome : " + isPalindrome(121));
        System.out.println("Capitalize : " + Capitalize("belajar java"));
        System.out.println("IsNoDuplicateChar : " + IsNoDuplicateChar("abccc"));
        System.out.println("IsBrace : " + IsBrace("()(())"));

    }

    /*
     * 1. Reverse
     * input : 42
     * output : 24
     * 
     */

    public static int Reverse(int n) {
        int result = 0;
        while (n > 0) {
            int num = n % 10;
            result = result * 10 + num;
            n = n / 10;
        }
        return result;
    }

    /*
     * 2.isPalindrome
     * input : 121
     * output : true
     */
    public static boolean isPalindrome(int n) {
        int result = 0;
        int number = n;
        while (n > 0) {
            int num = n % 10;
            result = result * 10 + num;
            n = n / 10;
        }
        return result == number;
    }

    /*
     * 3. Capitalize
     * 
     * input : "this is a very special title"
     * output : "This Is A Very Special Title"
     * 
     */
    public static String Capitalize(String input) {
        String[] s = input.split(" ");
        String result = "";
        for (int i = 0; i < s.length; i++) {
            String str = s[i].substring(0, 1).toUpperCase() + s[i].substring(1);
            result += str;

            if (i < s.length - 1) {
                result += " ";
            }
        }
        return result.trim();
    }

    /*
     * 4.IsNoDuplicateChar
     * input : "abcde"
     * output : true
     */

    public static boolean IsNoDuplicateChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    return false;
                }
            }
        }

        return true;
    }

    /*
     * 5.IsBrace
     * input :"(())"
     * output : true
     * 
     * input :"((()"
     * output : false
     */

    public static boolean IsBrace(String s) {
        String result = "";
        while (!s.equals(result)) {
            result = s;
            s = s.replace("()", "");
        }
        return s.isEmpty();
    }
}
