public class matricExtractor {
    static String getNumbers(String s) {

        String[] n = s.split(""); //array of strings
        StringBuffer f = new StringBuffer(); // buffer to store numbers

        for (int i = 0; i < n.length; i++) {
            if((n[i].matches("[0-9]+"))) {// validating numbers
                f.append(n[i]); //appending
            }else {
                //parsing to int and returning value
                String get=f.toString();
                return get;
            }
        }
        return null;
    }
}
