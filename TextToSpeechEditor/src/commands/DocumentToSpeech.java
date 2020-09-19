package commands;

import commands.replay.ActionsManager;
import model.Document;
import model.text2speechapis.FreeTTSAdapter;
import view.Text2SpeechEditorView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DocumentToSpeech implements ActionListener {

    private final JTextArea textArea;
    private final JMenuItem ttsReverseAllItem;

    public DocumentToSpeech(JTextArea textArea, JMenuItem ttsReverseAllItem) {
        this.textArea = textArea;
        this.ttsReverseAllItem = ttsReverseAllItem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Text2SpeechEditorView instance = Text2SpeechEditorView.getSingletonView();
        addCommandToManager(instance, e);

        Document currentDocument = instance.getCurrentDocument();
        if (currentDocument == null) return;
        currentDocument.setAPI(new FreeTTSAdapter());

        if (textIsNotSameInDocumentAndTextArea(currentDocument)) {
            informUserForNotBeingSame(currentDocument);
        }

        if (e.getSource() == ttsReverseAllItem) {
            currentDocument.playReverseContents();
            textArea.setText(currentDocument.getText());
            instance.setReversed(!instance.isReversed());
        }
        currentDocument.playContents();
    }

    private boolean textIsNotSameInDocumentAndTextArea(Document document) {
        return !(document.getText().equals(textArea.getText()));
    }

    private void informUserForNotBeingSame(Document document) {
        int choice = JOptionPane.showConfirmDialog(null,
                "replace documents text with text area's text?",
                "Document's text is not equal to text area",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (choice == JOptionPane.NO_OPTION) return;
        document.editDocument(textArea.getText());
    }

    private void addCommandToManager(Text2SpeechEditorView instance, ActionEvent e) {
        if (!instance.canITrackCommands()) return;
        ActionsManager command = new ActionsManager(e, new DocumentToSpeech(textArea,
                ttsReverseAllItem));
        instance.addActionToReplayManager(command);
    }
}
