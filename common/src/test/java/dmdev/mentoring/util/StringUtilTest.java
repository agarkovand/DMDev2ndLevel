package dmdev.mentoring.util;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {

    @org.junit.jupiter.api.Test
    void prepend() {
        assertTrue(true);
    }

    @org.junit.jupiter.api.Test
    void capitalize() {
        String input = "to be capitalized";
        String expected = "To Be Capitalized";
        assertEquals(expected, StringUtil.capitalize(input));
    }
}