package dmdev.mentoring.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilTest {

    @Test
    void prepend() {
        assertTrue(true);
    }

    @Test
    void capitalize() {
        String input = "to be capitalized";
        String expected = "To Be Capitalized";
        assertEquals(expected, StringUtil.capitalize(input));
    }
}