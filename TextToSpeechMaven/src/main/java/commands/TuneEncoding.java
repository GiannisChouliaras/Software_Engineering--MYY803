package commands;

import controller.Controller;
import encodingstrategies.Rot13Encoding;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TuneEncoding implements ActionListener {

    /**
     * Constructor of the class TuneEncoding
     */
    public TuneEncoding(Controller controller, JTextField authorField, JTextField titleField,
                        JTextArea textArea, JMenuItem atBashEncoding, JMenuItem atBashEncodingLine,
                        JMenuItem rot13Encoding, JMenuItem rot13EncodingLine)
    {
        this.controller         = controller;
        this.authorField        = authorField;
        this.titleField         = titleField;
        this.textArea           = textArea;
        this.atBashEncoding     = atBashEncoding;
        this.atBashEncodingLine = atBashEncodingLine;
        this.rot13Encoding      = rot13Encoding;
        this.rot13EncodingLine  = rot13EncodingLine;
    }

    /**
     * @param actionEvent
     * We implement here the method actionPerformed(ActionEvent actionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String author = authorField.getText();
        String title  = titleField.getText();
        String option = "";

        if (actionEvent.getSource() == atBashEncoding) {
            option = "AtBashEncoding";
            controller.encodeAll(author, title, option);
        }
        else if (actionEvent.getSource() == rot13Encoding) {
            option = "Rot13Encoding";
            controller.encodeAll(author, title, option);
        }
        else if (actionEvent.getSource() == atBashEncodingLine) {
            option = "AtBashEncoding";
            int line = Integer.parseInt(JOptionPane.showInputDialog("Give me line"));
            controller.encodeLine(author, title, line-1, option);
        }
        else if (actionEvent.getSource() == rot13EncodingLine) {
            option = "Rot13Encoding";
            int line = Integer.parseInt(JOptionPane.showInputDialog("Give me line"));
            controller.encodeLine(author, title, line-1, option);
        }

    }


    /**
     * private Fields
     */
    private Controller controller;
    private JTextField authorField, titleField;
    private JTextArea textArea;
    private JMenuItem atBashEncoding, atBashEncodingLine,
            rot13Encoding, rot13EncodingLine;
}
