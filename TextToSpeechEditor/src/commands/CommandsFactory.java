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
    private final JMenuItem ttsReverseAllItem;
    private final JMenuItem rot13Encoding;
    private final JMenuItem replayItem;
    private final JMenuItem ttsRevLineItem;
    private final JMenuItem atBashEncodingLine;

    public CommandsFactory(JTextArea text, JTextField titleField, JTextField authorField,
                           JMenuItem editAuthorItem, JMenuItem editTitleItem,
                           JMenuItem ttsReverseAllItem, JMenuItem rot13Encoding,
                           JMenuItem replayItem, JMenuItem giveFilenameMenuItem,
                           JMenuItem ttsRevLineItem, JMenuItem atBashEncodingLine) {
        this.text = text;
        this.titleField = titleField;
        this.authorField = authorField;
        this.editAuthorItem = editAuthorItem;
        this.editTitleItem = editTitleItem;
        this.ttsReverseAllItem = ttsReverseAllItem;
        this.rot13Encoding = rot13Encoding;
        this.replayItem = replayItem;
        this.giveFilenameMenuItem = giveFilenameMenuItem;
        this.ttsRevLineItem = ttsRevLineItem;
        this.atBashEncodingLine = atBashEncodingLine;
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
            return new LineToSpeech(text, ttsRevLineItem);
        if (command.equals("EncodeDocument"))
            return new EncodeDocument(text, rot13Encoding);
        if (command.equals("ReplayCommand"))
            return new ReplayCommand(replayItem);
        if (command.equals("EncodeLine"))
            return new EncodeLine(atBashEncodingLine);

        throw new IllegalArgumentException("Command Factory Problem");
    }
}
