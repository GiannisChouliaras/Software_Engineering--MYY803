package commands;

import model.Document;
import model.encodingStrategies.StrategiesFactory;
import view.Text2SpeechEditorView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EncodeLine implements ActionListener {

    private final JMenuItem atBashEncoding;
    private final StrategiesFactory strategiesFactory = new StrategiesFactory();

    public EncodeLine(JMenuItem atBashEncoding) {
        this.atBashEncoding = atBashEncoding;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Text2SpeechEditorView INSTANCE = Text2SpeechEditorView.getSingletonView();
        if (INSTANCE.isEncoded()) {informThatTextIsEncoded(); return;}

        Document currentDocument = INSTANCE.getCurrentDocument();
        if (currentDocument == null) return;

       String strategy = getStrategy(e);
       int line = getLineFromUser();
       if (line == -1) return;

       currentDocument.tuneEncodingStrategy(strategiesFactory.createStrategy(strategy));
       currentDocument.playEncodedLine(line);
    }

    private void informThatTextIsEncoded() {
        JOptionPane.showMessageDialog(null, "You can't encode a line" +
                " when the document is encoded", "Document uses an Encode",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private String getStrategy(ActionEvent e) {
        if (e.getSource() == atBashEncoding) return "AtBashEncoding";
        return "Rot13Encoding";
    }

    private int getLineFromUser() {
        String line =  JOptionPane.showInputDialog(null,
                "give me the line you want to encode");
        if (line.isBlank()) return -1;
        return Integer.parseInt(line)-1;
    }
}
