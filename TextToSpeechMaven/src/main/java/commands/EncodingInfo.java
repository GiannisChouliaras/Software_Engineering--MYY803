package commands;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.MouseInputListener;

import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;

public class EncodingInfo implements MouseInputListener {

    public EncodingInfo(JTextArea textArea, JLabel atBashEncodeLabel) {
        this.textArea		   = textArea;
        this.atBashEncodeLabel = atBashEncodeLabel;
        strategiesFactory      = new StrategiesFactory();
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == atBashEncodeLabel) {
            String textString = textArea.getText();
            originalText = textString;
            encodingStrategy = strategiesFactory.createStrategy("AtBashEncoding");
            String encoded = encodingStrategy.encode(originalText);
            textArea.setText(encoded);
        }
        else {
            originalText = textArea.getText();
            encodingStrategy = strategiesFactory.createStrategy("Rot13Encoding");
            String encoded = encodingStrategy.encode(originalText);
            textArea.setText(encoded);
        }


    }
    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == atBashEncodeLabel) {
            textArea.setText(originalText);
        }
        else {
            textArea.setText(originalText);
        }

    }
    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    private JTextArea textArea;
    private EncodingStrategy encodingStrategy;
    private StrategiesFactory strategiesFactory;
    private String originalText;
    private JLabel atBashEncodeLabel;

}
