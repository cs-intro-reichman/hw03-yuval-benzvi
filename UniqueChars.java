/** String processing exercise 2. */
public class UniqueChars {
    public static void main(String[] args) {  
        String str = args[0];
        System.out.println(uniqueChars(str));
    }

    /**
     * Returns a string which is identical to the original string, 
     * except that all the duplicate characters are removed,
     * unless they are space characters.
     */
    public static String uniqueChars(String s) {
        String unique = "";
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
            flag = false;
            int j = 0;
            /* it is a bit strange what going on here
                the while and flag could be done with a for and even better in a helper function
                */
            while (j < i && flag == false) {
                if (s.charAt(i) == s.charAt(j)) {
                    flag = true;
                }
                j++;
            }
            if (flag == false || (flag == true && s.charAt(i) == 32)) {
                unique = unique + s.charAt(i);
            }
        }
        return unique;
    }
}
