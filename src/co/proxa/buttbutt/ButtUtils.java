package co.proxa.buttbutt;

public class ButtUtils {

    public static String getArgs(String[] args) {
        args[0] = "";
        StringBuilder sb = new StringBuilder();
        for (String x : args) {
            if (!x.isEmpty()) {
                sb.append(x).append(" ");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public static String concatenateUrlArgs(String[] args) {
        args[0] = "";
        StringBuilder sb = new StringBuilder();
        for (String x : args) {
            if (!x.isEmpty()) {
                sb.append(x).append("+");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

}
