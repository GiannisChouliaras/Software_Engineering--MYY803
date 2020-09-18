package commands;

import model.Document;
import org.junit.jupiter.api.Test;
import view.Text2SpeechEditorView;

import javax.swing.*;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Text2SpeechEditorViewTest {

    private final Text2SpeechEditorView INSTANCE = Text2SpeechEditorView.getSingletonView();
    private JTextField authorField, titleField;
    private JTextArea textArea;


    @Test
     void newDocumentTest() {
        authorField = new JTextField();
        titleField = new JTextField();
        textArea = new JTextArea();
        JMenuItem newDocumentItem = new JMenuItem();

        authorField.setText("TestAuthor");
        titleField.setText("TestTitle");
        textArea.setText("TestArea");

        ActionListener newDocument = new NewDocument(authorField, titleField, textArea);
        newDocumentItem.addActionListener(newDocument);

        newDocumentItem.doClick();

        assertEquals("-", INSTANCE.getCurrentDocument().getText());
    }

    @Test
    void editDocumentTest() {
        authorField = new JTextField();
        titleField = new JTextField();
        textArea = new JTextArea();

        authorField.setText("TestAuthor");
        titleField.setText("TestTitle");
        textArea.setText("TestArea");

        JMenuItem editAuthorItem = new JMenuItem();
        JMenuItem editTitleItem = new JMenuItem();
        JMenuItem editTextItem = new JMenuItem();

        Document document = new Document(authorField.getText(),titleField.getText());
        document.editDocument(textArea.getText());
        INSTANCE.setCurrentDocument(document);
//      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        ActionListener editAuthor = new EditDocument(authorField,titleField,textArea,
                editAuthorItem, editTitleItem);
        editAuthorItem.addActionListener(editAuthor);
        String name = JOptionPane.showInputDialog("What NAME will you use for Test?");
        editAuthorItem.doClick();
        assertEquals(name, INSTANCE.getCurrentDocument().getAuthor());
//      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        ActionListener editTitle = new EditDocument(authorField,titleField,textArea,
                editAuthorItem, editTitleItem);
        editTitleItem.addActionListener(editTitle);
        String title = JOptionPane.showInputDialog("What TITLE will you use for Test?");
        editTitleItem.doClick();
        assertEquals(title, INSTANCE.getCurrentDocument().getTitle());
//      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        ActionListener editText = new EditDocument(authorField, titleField, textArea,
                editAuthorItem, editTitleItem);
        editTextItem.addActionListener(editText);
        String text = JOptionPane.showInputDialog("What TEXT will you use for Test?");
        textArea.setText(text);
        editTextItem.doClick();
        assertEquals(text, INSTANCE.getCurrentDocument().getText());
    }

//    @Test
//    void saveDocumentTest() {
//        Document document = new Document("authorName", "title");
//        INSTANCE.setCurrentDocument(document);
//        INSTANCE.getCurrentDocument().editDocument("testing text");
//
//        textArea = new JTextArea();
//        textArea.setText("testing text");
//
//        ActionListener saveDocument = new SaveDocument(textArea);
//        JMenuItem saveItem = new JMenuItem();
//        saveItem.addActionListener(saveDocument);
//        saveItem.doClick();
////      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        String filepath = "";
//        JFileChooser fileChooser = new JFileChooser();
//        int i = fileChooser.showOpenDialog(null);
//        if (i == JFileChooser.APPROVE_OPTION) {
//            File file = fileChooser.getSelectedFile();
//            filepath = file.getPath();
//        }
//        ArrayList<String> contents = new ArrayList<String>();
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(filepath));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                contents.add(line);
//            }
//        }
//        catch (Exception e) {e.printStackTrace();}
//
//        String newAuthor = contents.get(0);
//        String newTitle = contents.get(1);
//        String creational = contents.get(2);
//        String lastSaved = contents.get(3);
//        String text = "";
//
//        for (int j = 5; j < contents.size(); j ++)
//            text += contents.get(j) + "\n";
//
//        Document newDocument = new Document(newAuthor, newTitle);
//        newDocument.setBothDates(creational, lastSaved);
//        newDocument.editDocument(text);
////      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        assertEquals(newDocument.getText(), INSTANCE.getCurrentDocument().getText());
//    }

//    @Test
//    void openCommandTest() {
//
//    }

}