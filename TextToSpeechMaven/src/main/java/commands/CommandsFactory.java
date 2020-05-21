package commands;

import controller.Controller;

import javax.swing.*;

public class CommandsFactory {

    public CommandsFactory(Controller controller, ReplayManager replayManager,
                           JTextField titleField, JTextField authorField,
                           JTextArea textArea,JMenuItem editAuthorItem, JMenuItem editTitleItem,
                           JMenuItem ttsLineItem, JMenuItem ttsReverseLineItem,
                           JMenuItem ttsReverseAllItem, JSlider volumeSlider, JSlider pitchSlider,
                           JSlider rateSlider,JLabel volumeValue, JLabel pitchValue,
                           JLabel rateValue, JMenuItem atBashEncoding,
                           JMenuItem atBashEncodingLine, JMenuItem rot13Encoding,
                           JMenuItem rot13EncodingLine, JMenuItem replayItem)
    {
        this.authorField        = authorField;
        this.textArea           = textArea;
        this.controller			= controller;
        this.titleField         = titleField;
        this.editAuthorItem     = editAuthorItem;
        this.editTitleItem      = editTitleItem;
        this.ttsLineItem        = ttsLineItem;
        this.ttsReverseLineItem = ttsReverseLineItem;
        this.ttsReverseAllItem  = ttsReverseAllItem;
        this.volumeSlider       = volumeSlider;
        this.pitchSlider        = pitchSlider;
        this.rateSlider         = rateSlider;
        this.volumeValue        = volumeValue;
        this.pitchValue         = pitchValue;
        this.rateValue          = rateValue;
        this.atBashEncoding     = atBashEncoding;
        this.atBashEncodingLine = atBashEncodingLine;
        this.rot13Encoding      = rot13Encoding;
        this.rot13EncodingLine  = rot13EncodingLine;
        this.replayManager      = replayManager;
        this.replayItem         = replayItem;
    }

    /**
     * @param command
     * method that we create a command giving a String and
     * @return an ActionListener
     */
    public ActionListener createCommand(String command)
    {
        if (command.equals("NewDocument")) {
            return new NewDocument(controller,authorField,titleField,textArea,
                    volumeSlider,rateSlider,pitchSlider);
        }
        else if (command.equals("EditDocument")) {
            return new EditDocument(controller, authorField, titleField,
                    textArea,editAuthorItem, editTitleItem);
        }
        else if (command.equals("ExitListener")) {
            return new ExitListener();
        }
        else if (command.equals("OpenDocument")) {
            return new OpenDocument(controller, authorField, titleField, textArea,
                    volumeSlider,pitchSlider,rateSlider, volumeValue,pitchValue,
                    rateValue);
        }
        else if (command.equals("SaveDocument")) {
            return new SaveDocument(controller, authorField, titleField, textArea);
        }
        else if (command.equals("DocumentToSpeech")) {
            return new DocumentToSpeech(controller, replayManager, authorField,
                    titleField, ttsReverseAllItem);
        }
        else if (command.equals("LineToSpeech")) {
            return new LineToSpeech(controller, replayManager,authorField,titleField,
                    textArea, ttsLineItem, ttsReverseLineItem);
        }
        else if (command.equals("InfoListener")) {
            return new InfoListener(controller, authorField, titleField);
        }
        else if (command.equals("TuneEncoding")) {
            return new TuneEncoding(controller, replayManager, authorField, titleField,
                    atBashEncoding, atBashEncodingLine, rot13Encoding,rot13EncodingLine);
        }
        else if (command.equals("ReplayCommand")) {
            return new ReplayCommand(replayManager, replayItem);
        }
        else {
            throw new IllegalArgumentException("Factory problem");
        }
    }

    public ChangeListener createChangeCommand(String command) {
        if (command.equals("TuneAudio")) {
            return new TuneAudio(controller, authorField, titleField, volumeSlider,
                    pitchSlider, rateSlider, volumeValue, pitchValue, rateValue);
        } else
            throw new IllegalArgumentException("Factory problem");
    }


    private Controller      controller;
    private JTextArea       textArea;
    private JTextField      titleField;
    private JTextField      authorField;
    private JMenuItem       editAuthorItem, editTitleItem, ttsLineItem,
                            ttsReverseLineItem, ttsReverseAllItem,
                            atBashEncoding, atBashEncodingLine, rot13Encoding,
                            rot13EncodingLine, replayItem;

    private JSlider         volumeSlider, pitchSlider, rateSlider;
    private JLabel          volumeValue, pitchValue, rateValue;
    private ReplayManager   replayManager;
}
