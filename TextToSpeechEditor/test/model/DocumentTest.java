package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    @Test
    void getText() {
        Document document = new Document("Ioannis", "Corona");
        document.editDocument("Hello Everyone");
        document.playReverseContents();
        assertEquals("Everyone Hello", document.getText());
    }
}