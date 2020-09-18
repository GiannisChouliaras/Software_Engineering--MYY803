package model.encodingStrategies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtBashEncodingTest {

    @Test
    void mapCharacter() {
        AtBashEncoding atbash = new AtBashEncoding();
        char h = 'H';
        assertEquals('S', atbash.mapCharacter(h));
    }
}