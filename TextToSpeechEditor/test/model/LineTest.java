package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {

    @Test
    void getWholeLine() {
        Line line = new Line("Hello everyone");
        assertEquals("Hello everyone", line.getWholeLine());
    }

    @Test
    void reverseWords() {
        Line line = new Line("Hello everyone");
        line.reverseWords();
        assertEquals("everyone Hello", line.getWholeLine());
    }
}