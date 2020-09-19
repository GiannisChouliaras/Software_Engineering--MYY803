package commands;

import model.Document;
import model.encodingStrategies.StrategiesFactory;
import model.text2speechapis.FreeTTSAdapter;
import view.Text2SpeechEditorView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EncodeDocument implements ActionListener {

    private final JTextArea textArea;
    private final JMenuItem rot13Encoding;
    private final StrategiesFactory strategiesFactory = new StrategiesFactory();

    public EncodeDocument(JTextArea textArea, JMenuItem rot13Encoding) {
        this.textArea = textArea;
        this.rot13Encoding = rot13Encoding;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Text2SpeechEditorView instance = Text2SpeechEditorView.getSingletonView();

        Document currentDocument = instance.getCurrentDocument();
        if (currentDocument == null) return;

        currentDocument.setAPI(new FreeTTSAdapter());
        checkIfTextBetweenAreaAndDocumentAreSame(currentDocument);

        if (e.getSource() == rot13Encoding) {
            if (!canIEncodeWithRot(instance)) {informTheAlreadyEncode(instance); return;}
            currentDocument.tuneEncodingStrategy(strategiesFactory.createStrategy("Rot13Encoding"));
            currentDocument.playEncodedContents();
            textArea.setText(currentDocument.getText());
            if (instance.isEncoded()) instance.setEncoded(false, "none");
            else instance.setEncoded(true, "Rot13Encoding");
            return;
        }
        if (!canIEncodeWithAtBash(instance)) {informTheAlreadyEncode(instance); return;}
        currentDocument.tuneEncodingStrategy(strategiesFactory.createStrategy("AtBashEncoding"));
        currentDocument.playEncodedContents();
        textArea.setText(currentDocument.getText());

        if (instance.isEncoded()) instance.setEncoded(false, "none");
        else instance.setEncoded(true, "AtBashEncoding");
    }

    private void checkIfTextBetweenAreaAndDocumentAreSame(Document document) {
        if (!document.getText().equals(textArea.getText())) {
            int choice = JOptionPane.showConfirmDialog(null,
                    "replace Document's text with area's?",
                    "Document's text is different from area's text",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION)
                document.editDocument(textArea.getText());
        }
    }

    private boolean canIEncodeWithRot(Text2SpeechEditorView instance) {
        return !(instance.isEncoded() && instance.getEncode().equals("AtBashEncoding"));
    }

    private boolean canIEncodeWithAtBash(Text2SpeechEditorView instance) {
        return !(instance.isEncoded() && instance.getEncode().equals("Rot13Encoding"));
    }

    private void informTheAlreadyEncode(Text2SpeechEditorView instance) {
        JOptionPane.showMessageDialog(null,
                "You already encoded your text with: " +
                instance.getEncode() + ".", "Cannot Encode",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

