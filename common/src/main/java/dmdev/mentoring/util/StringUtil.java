package dmdev.mentoring.util;

public class StringUtil {
    public static String prepend(String prefix, String text) {
        if (prefix == null && text == null) {
            return "Unknown";
        } else if (prefix == null) {
            return text;
        } else if (text == null) {
            return prefix;
        } else {
            return prefix + " " + text;
        }
    }

}
