package commands;

import commands.replay.ActionsManager;
import model.Document;
import model.text2speechapis.FreeTTSAdapter;
import view.Text2SpeechEditorView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LineToSpeech implements ActionListener {

    private JTextArea textArea;
    private JMenuItem ttsRevLineItem;

    public LineToSpeech(JTextArea textArea, JMenuItem ttsRevLineItem) {
        this.textArea = textArea;
        this.ttsRevLineItem = ttsRevLineItem;
    }

    @Override
    public void actionPerformed(ActionEvent e)  {
        Text2SpeechEditorView instance = Text2SpeechEditorView.getSingletonView();
        addCommandToManager(instance, e);

        Document currentDocument = instance.getCurrentDocument();
        if (currentDocument == null) return;

        if (documentsTextIsNotTheSameAsTextArea(currentDocument))
            replaceDocumentsTextWithTextArea(currentDocument);

        int line = askWhatLineToPlay();
        if (line == -1) return;

        currentDocument.setAPI(new FreeTTSAdapter());
        if (e.getSource() == ttsRevLineItem) {
            currentDocument.playReverseLine(line);
            return;
        }
        currentDocument.playLine(line);
    }

    private boolean documentsTextIsNotTheSameAsTextArea(Document document) {
        return !(document.getText().equals(textArea.getText()));
    }

    private void replaceDocumentsTextWithTextArea(Document document) {
        document.editDocument(textArea.getText());
    }

    private int askWhatLineToPlay() {
        String line = JOptionPane.showInputDialog("Choose line to play");
        if (line.isBlank()) return -1;
        return Integer.parseInt(line) - 1;
    }

    private void addCommandToManager(Text2SpeechEditorView instance, ActionEvent e) {
        if (!instance.canITrackCommands()) return;
        ActionsManager command = new ActionsManager(e, new LineToSpeech(textArea, ttsRevLineItem));
        instance.addActionToReplayManager(command);
    }
}
