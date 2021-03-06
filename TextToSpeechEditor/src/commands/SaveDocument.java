package commands;

import view.Text2SpeechEditorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;

public class SaveDocument implements ActionListener {

    private final JTextArea textArea;

    public SaveDocument(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Text2SpeechEditorView instance = Text2SpeechEditorView.getSingletonView();
        if (cannotSaveDocument(instance)) return;
        String text = createHeaderOfFile(instance);
        if (saveTextAreaContents()) text += textArea.getText();
        else text += instance.getCurrentDocument().getText();
        String path = getPathFromWindow();
        writeToFile(path, text);
    }

    private String createHeaderOfFile(Text2SpeechEditorView instance) {
        String text = "";
        text += instance.getCurrentDocument().getAuthor() + "\n";
        text += instance.getCurrentDocument().getTitle() + "\n";
        text += instance.getCurrentDocument().getDocumentsCreationalDate() + "\n";
        text += new Date().toString() + "\n\n";
        return text;
    }

    private boolean cannotSaveDocument(Text2SpeechEditorView instance) {
        if (instance.getCurrentDocument() == null) return true;
        if (instance.isEncoded()) {informEncodedText(instance); return true;}
        if (instance.isReversed()) {informReversedText(); return true;}
        return false;
    }

    private boolean saveTextAreaContents() {
        Text2SpeechEditorView instance = Text2SpeechEditorView.getSingletonView();
        if (instance.getCurrentDocument().getText().equals(textArea.getText()))
            return true;
        int choice = JOptionPane.showConfirmDialog(null, "continue with text area?",
                "Text is different between Document and text area", JOptionPane.YES_NO_OPTION,
                JOptionPane.WHEN_FOCUSED);
        return choice == 0;
    }

    private String getPathFromWindow() {
        JFileChooser fileChooser = new JFileChooser();
        int i = fileChooser.showSaveDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getPath();
            return filePath;
        }
        return "";
    }

    private void writeToFile(String path, String text) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
            writer.write(text);
            writer.close();
        } catch (Exception e) {
            System.err.format("Exception occurred trying to write '%s'.", path);
        }
    }

    private void informEncodedText(Text2SpeechEditorView instance) {
        JOptionPane.showMessageDialog(null,
                "Cannot save a Document with" +
                " encoded text! Encode: " + instance.getEncode() +". ", "Cannot" +
                " Save Document", JOptionPane.INFORMATION_MESSAGE);
    }

    private void informReversedText() {
        JOptionPane.showMessageDialog(null,
                "Cannot save a Document with" +
                        " reversed text!", "Cannot" +
                        " Save Document", JOptionPane.INFORMATION_MESSAGE);
    }
}
