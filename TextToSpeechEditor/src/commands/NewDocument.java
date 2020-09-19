package commands;

import commands.replay.ActionsManager;
import model.Document;
import view.Text2SpeechEditorView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewDocument implements ActionListener {

    private final JTextField authorField;
    private final JTextField titleField;
    private final JTextArea area;

    public NewDocument(JTextField authorField, JTextField titleField, JTextArea area) {
        this.authorField = authorField;
        this.titleField = titleField;
        this.area = area;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addCommandToManager(e);
        Document document = checkVariablesAndCreateNewDocument();
        updateText2SpeechEditorView(document);
    }

    private Document checkVariablesAndCreateNewDocument() {
        String author = authorField.getText();
        String title = titleField.getText();

        if (author.length() == 0)
            author = JOptionPane.showInputDialog("Give the Author: ");
        if (title.length() == 0)
            title = JOptionPane.showInputDialog("Give Title: ");

        authorField.setText(author);
        titleField.setText(title);
        area.setText("");

        return new Document(author, title);
    }

    private void updateText2SpeechEditorView(Document document) {
        Text2SpeechEditorView instance = Text2SpeechEditorView.getSingletonView();
        instance.setCurrentDocument(document);
        if (instance.getCurrentDocument().equals(document)) {
            JOptionPane.showMessageDialog(null,
                    "Created a new Document",
                    "Successfully created",1);
        }
    }

    private void addCommandToManager(ActionEvent e) {
        Text2SpeechEditorView instance = Text2SpeechEditorView.getSingletonView();
        if (!instance.canITrackCommands()) return;
        ActionsManager command = new ActionsManager(e, new NewDocument(authorField, titleField, area));
        instance.addActionToReplayManager(command);
    }
}
