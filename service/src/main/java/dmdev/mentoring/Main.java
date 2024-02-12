package dmdev.mentoring;

import static dmdev.mentoring.util.StringUtil.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, " + prepend("Wonderful!", null));
        System.out.println(capitalize("capitalize each word in phrase"));
    }
}