package commands;

import view.Text2SpeechEditorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;

public class SaveDocument implements ActionListener {

    private JTextArea textArea;

    public SaveDocument(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Text2SpeechEditorView instance = Text2SpeechEditorView.getSingletonView();
        if (instance.getCurrentDocument() == null) return;
        String text = "";
        text += instance.getCurrentDocument().getAuthor() + "\n";
        text += instance.getCurrentDocument().getTitle() + "\n";
        text += instance.getCurrentDocument().getDocumentsCreationalDate() + "\n";
        text += new Date().toString() + "\n\n";

        if (saveTextAreaContents()) text += textArea.getText();
        else text += Text2SpeechEditorView.getSingletonView().getCurrentDocument().getText();
        String path = getPathFromWindow();
        writeToFile(path, text);
    }

    private boolean saveTextAreaContents() {
        Text2SpeechEditorView instance = Text2SpeechEditorView.getSingletonView();
        if (instance.getCurrentDocument().getText().equals(textArea.getText()))
            return true;
        int choice = JOptionPane.showConfirmDialog(null, "continue with text area?",
                "Text is different between Document and text area", JOptionPane.YES_NO_OPTION,
                JOptionPane.WHEN_FOCUSED);
        if (choice == 0) return true;
        return false;
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
}

// TODO When you save in same file, it keeps the previous data. Delete them before you save like a new file.