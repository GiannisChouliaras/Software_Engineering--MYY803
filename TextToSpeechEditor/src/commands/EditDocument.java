package commands;

import commands.replay.ActionsManager;
import model.Document;
import view.Text2SpeechEditorView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditDocument implements ActionListener {

    private final JTextField authorField;
    private final JTextField titleField;
    private final JMenuItem editAuthorItem;
    private final JMenuItem editTitleItem;
    private final JTextArea textArea;

    public EditDocument(JTextField authorField, JTextField titleField,
                        JTextArea textArea, JMenuItem editAuthorItem,
                        JMenuItem editTitleItem) {
        this.authorField = authorField;
        this.titleField = titleField;
        this.textArea = textArea;
        this.editAuthorItem = editAuthorItem;
        this.editTitleItem = editTitleItem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addCommandToManager(e);
        Text2SpeechEditorView instance = Text2SpeechEditorView.getSingletonView();
        Document currendDocument = instance.getCurrentDocument();
        if (currendDocument == null) return;

        if (e.getSource() == editAuthorItem) {
            authorField.setText(JOptionPane.showInputDialog("Give me new Author name: "));
            currendDocument.setAuthor(authorField.getText());
            return;
        }
        if (e.getSource() == editTitleItem) {
            titleField.setText(JOptionPane.showInputDialog("Give me new Title: "));
            currendDocument.setTitle(titleField.getText());
            return;
        }
        currendDocument.editDocument(textArea.getText());
    }

    private void addCommandToManager(ActionEvent e) {
        if (!Text2SpeechEditorView.getSingletonView().canITrackCommands()) return;
        ActionsManager command = new ActionsManager(e, new EditDocument(authorField,
                titleField, textArea, editAuthorItem, editTitleItem));
        Text2SpeechEditorView.getSingletonView().addActionToReplayManager(command);
    }
}
