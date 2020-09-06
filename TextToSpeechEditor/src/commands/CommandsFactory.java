package commands;

import commands.replay.ReplayManager;

import javax.swing.*;

public class CommandsFactory {

    private final JTextArea text;
    private final JTextField titleField;
    private final JTextField authorField;
    private final JMenuItem editAuthorItem;
    private final JMenuItem editTitleItem;
    private final JMenuItem giveFilenameMenuItem;
    private final JMenuItem ttsReverseAllItem;;
    private final JMenuItem rot13Encoding;
    private final JMenuItem rot13EncodingLine;
    private final JMenuItem replayItem;
    private final JSlider volumeSlider;
    private final JSlider pitchSlider;
    private final JSlider rateSlider;

    public CommandsFactory(JTextArea text, JTextField titleField, JTextField authorField,
                           JMenuItem editAuthorItem, JMenuItem editTitleItem,
                           JMenuItem ttsReverseAllItem, JMenuItem rot13Encoding,
                           JMenuItem rot13EncodingLine, JMenuItem replayItem,
                           JSlider volumeSlider, JSlider pitchSlider, JSlider rateSlider,
                           JMenuItem giveFilenameMenuItem) {
        this.text = text;
        this.titleField = titleField;
        this.authorField = authorField;
        this.editAuthorItem = editAuthorItem;
        this.editTitleItem = editTitleItem;
        this.ttsReverseAllItem = ttsReverseAllItem;
        this.rot13Encoding = rot13Encoding;
        this.rot13EncodingLine = rot13EncodingLine;
        this.replayItem = replayItem;
        this.volumeSlider = volumeSlider;
        this.pitchSlider = pitchSlider;
        this.rateSlider = rateSlider;
        this.giveFilenameMenuItem = giveFilenameMenuItem;
    }

    public ActionListener createCommand(String command) {
        if (command.equals("ExitListener"))
            return new ExitListener();
        if (command.equals("NewDocument"))
            return new NewDocument(titleField, authorField, text);
        if (command.equals("OpenDocument"))
            return new OpenDocument(giveFilenameMenuItem, text, titleField, authorField);
        if (command.equals("EditDocument"))
            return new EditDocument(titleField, authorField, text, editAuthorItem,
                    editTitleItem);
        if (command.equals("AboutDocument"))
            return new AboutDocument();
        if (command.equals("SaveDocument"))
            return new SaveDocument(text);
        if (command.equals("DocumentToSpeech"))
            return new DocumentToSpeech(text, ttsReverseAllItem);
        if (command.equals("LineToSpeech"))
            return new LineToSpeech(text);
        if (command.equals("EncodeDocument"))
            return new EncodeDocument(text, rot13Encoding);
        if (command.equals("ReplayCommand"))
            return new ReplayCommand(replayItem);

        throw new IllegalArgumentException("Command Factory Problem");
    }
}
