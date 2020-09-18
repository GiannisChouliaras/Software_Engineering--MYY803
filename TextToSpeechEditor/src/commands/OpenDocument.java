package commands;

import commands.replay.ActionsManager;
import model.Document;
import view.Text2SpeechEditorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class OpenDocument implements ActionListener {

    private JMenuItem giveFilenameMenuItem;
    private JTextArea textArea;
    private JTextField authorField;
    private JTextField titleField;
    private ArrayList<String> fileContents = new ArrayList<String>();

    public OpenDocument(JMenuItem giveFilenameMenuItem, JTextArea textArea,
                        JTextField authorField, JTextField titleField) {
        this.giveFilenameMenuItem = giveFilenameMenuItem;
        this.textArea = textArea;
        this.authorField = authorField;
        this.titleField = titleField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String path = "";
        textArea.setText("");
        authorField.setText("");
        titleField.setText("");
        addCommandToReplay(e);
        if (e.getSource() == giveFilenameMenuItem) path = viaPath();
        if (e.getSource() != giveFilenameMenuItem) path = viaWindow();
        fillContentsFromFile(path);
        if (fileContents.isEmpty()) return;
        checkIfDocumentOpenedAndUpdateFields();
    }

    private final String viaPath()  {
        return JOptionPane.showInputDialog(null,
                "Give me the direct path");
    }

    private final String viaWindow() {
       JFileChooser fileChooser = new JFileChooser();
        int i = fileChooser.showOpenDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            return file.getPath();
        }
        return "";
    }

    private final void fillContentsFromFile(String path) {
        fileContents.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                fileContents.add(line);
            }
            reader.close();
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", path);
        }
    }

    private final Document formatDocument() {
        String author = fileContents.get(0);
        String title = fileContents.get(1);
        String creationalDate = fileContents.get(2);
        String lastSavedDate = fileContents.get(3);
        String text = "";

        for (int i = 5; i < fileContents.size(); i ++) {
            text += fileContents.get(i) + "\n";
        }

        Document document = new Document(author, title);
        document.setBothDates(creationalDate, lastSavedDate);
        document.editDocument(text);
        return document;
    }

    private void checkIfDocumentOpenedAndUpdateFields() {
        Text2SpeechEditorView instance = Text2SpeechEditorView.getSingletonView();
        Document document = formatDocument();
        instance.setCurrentDocument(document);

        if (instance.getCurrentDocument().equals(document)) {
            JOptionPane.showMessageDialog(null, "Successfull loading");
            textArea.setText(instance.getCurrentDocument().getText());
            authorField.setText(instance.getCurrentDocument().getAuthor());
            titleField.setText(instance.getCurrentDocument().getTitle());
            return;
        }
        JOptionPane.showMessageDialog(null, "Document is not Equal",
                "Document: ", 0);
    }

    private void addCommandToReplay(ActionEvent e) {
        if (!Text2SpeechEditorView.getSingletonView().canITrackCommands()) return;
        ActionListener openDocument = new OpenDocument(giveFilenameMenuItem, textArea,
                authorField, titleField);
        ActionsManager action = new ActionsManager(e,openDocument);
        Text2SpeechEditorView.getSingletonView().addActionToReplayManager(action);
    }
}
