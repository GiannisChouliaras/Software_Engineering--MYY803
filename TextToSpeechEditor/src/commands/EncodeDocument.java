package commands;

import model.Document;
import model.encodingStrategies.StrategiesFactory;
import model.text2speechapis.FreeTTSAdapter;
import view.Text2SpeechEditorView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EncodeDocument implements ActionListener {

    private JTextArea textArea;
    private JMenuItem rot13Encoding;
    private StrategiesFactory strategiesFactory = new StrategiesFactory();

    public EncodeDocument(JTextArea textArea, JMenuItem rot13Encoding) {
        this.textArea = textArea;
        this.rot13Encoding = rot13Encoding;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Text2SpeechEditorView instance = Text2SpeechEditorView.getSingletonView();

        Document currentDocument = instance.getCurrentDocument();
        if (currentDocument == null) return;

        if (!currentDocument.getText().equals(textArea.getText())) {
            int choice = JOptionPane.showConfirmDialog(null,
                    "replace Document's text with area's?",
                    "Document's text is different from area's text",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION)
                currentDocument.editDocument(textArea.getText());
        }

        currentDocument.setAPI(new FreeTTSAdapter());

        if (e.getSource() == rot13Encoding) {
            currentDocument.tuneEncodingStrategy(strategiesFactory.createStrategy("Rot13Encoding"));
            currentDocument.playEncodedContents();
            return;
        }
        currentDocument.tuneEncodingStrategy(strategiesFactory.createStrategy("AtBashEncoding"));
        currentDocument.playEncodedContents();
    }
}
