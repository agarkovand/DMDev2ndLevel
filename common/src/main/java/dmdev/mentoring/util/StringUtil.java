package dmdev.mentoring.util;

import org.apache.commons.text.WordUtils;

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

    public static String capitalize (String input) {
        return WordUtils.capitalize(input);
    }

}
