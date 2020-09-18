package view;

import commands.CommandsFactory;
import commands.ActionListener;
import commands.TuneAudio;
import commands.replay.ActionsManager;
import commands.replay.BoxItem;
import commands.replay.ReplayManager;
import model.Document;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

public final class Text2SpeechEditorView {

    private static volatile Text2SpeechEditorView singleInstance = null;
    private JTextArea text;
    private boolean canTrackCommands = false;
    private boolean reversed = false, encoded = false;
    private String encode = "none";
    private JFrame frame;
    private final ReplayManager replayManager = new ReplayManager();
    private Document currentDocument;
    private JTextField titleTextField, authorTextField;
    private JButton textToSpeechButton, lineToSpeechButton;
    private JSlider pitchSlider, rateSlider, volumeSlider;


    public Text2SpeechEditorView() {
        initialize();
    }

    public static Text2SpeechEditorView getSingletonView() {
        if (singleInstance == null)
            singleInstance = new Text2SpeechEditorView();
        return singleInstance;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Text2SpeechEditorView window = new Text2SpeechEditorView();
                    window.frame.setLocationRelativeTo(null);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    public boolean isEncoded() {
        return encoded;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncoded(boolean encoded, String encode) {
        this.encoded = encoded;
        this.encode = encode;
    }

    public int getRate() {
        return rateSlider.getValue();
    }

    public int getVolume() {
        return volumeSlider.getValue();
    }

    public int getPitch() {
        return pitchSlider.getValue();
    }

    public void setTunes(int volume, int pitch, int rate) {
        this.volumeSlider.setValue(volume);
        this.pitchSlider.setValue(pitch);
        this.rateSlider.setValue(rate);
    }

    public void setCanTrackCommands(boolean canTrackCommands) {
        this.canTrackCommands = canTrackCommands;
    }

    public boolean canITrackCommands() {
        return canTrackCommands;
    }

    public Document getCurrentDocument() {
        return this.currentDocument;
    }

    public void addActionToReplayManager(ActionsManager action) {
        replayManager.addAction(action);
    }

    public void clearReplayManager() {
        replayManager.clearActions();
    }

    public void replay() {
        replayManager.replay();
    }

    public void setCurrentDocument(Document currentDocument) {this.currentDocument = currentDocument;}

    private void initialize() {
        frame = new JFrame("Text To Speech Editor");
        frame.setBackground(new Color(42, 42, 43));
        frame.getContentPane().setBackground(new Color(42, 42, 43));
        frame.getContentPane().setFont(new Font("Helvetica Neue", Font.PLAIN, 13));

        formPanel();
        textArea();
        menuBar();

        frame.setMinimumSize(new Dimension(1000, 700));
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void formPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setBorder(new CompoundBorder());
        formPanel.setBackground(new Color(42, 42, 43));
        formPanel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        formPanel.setPreferredSize(new Dimension(300, 300));
        frame.getContentPane().add(formPanel, BorderLayout.WEST);
        formPanel.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel = new JLabel("MYY 803");
        lblNewLabel.setIcon(new ImageIcon("icon/java.png"));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setPreferredSize(new Dimension(150, 150));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        formPanel.add(lblNewLabel, BorderLayout.NORTH);

        JPanel middleFormPanel = new JPanel();
        middleFormPanel.setPreferredSize(new Dimension(100, 250));
        middleFormPanel.setBackground(new Color(42, 42, 43));
        formPanel.add(middleFormPanel, BorderLayout.CENTER);
        middleFormPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Title : ");
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        titleLabel.setBounds(28, 69, 61, 16);
        middleFormPanel.add(titleLabel);

        JLabel authorLabel = new JLabel("Author :");
        authorLabel.setForeground(new Color(255, 255, 255));
        authorLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        authorLabel.setBounds(28, 35, 61, 16);
        middleFormPanel.add(authorLabel);

        titleTextField = new JTextField();
        titleTextField.setForeground(new Color(255, 255, 255));
        titleTextField.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        titleTextField.setBorder(null);
        titleTextField.setBackground(new Color(42, 42, 43));
        titleTextField.setBounds(101, 30, 193, 26);
        middleFormPanel.add(titleTextField);
        titleTextField.setColumns(10);

        authorTextField = new JTextField();
        authorTextField.setForeground(new Color(255, 255, 255));
        authorTextField.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        authorTextField.setBorder(null);
        authorTextField.setBackground(new Color(42, 42, 43));
        authorTextField.setBounds(101, 64, 193, 26);
        middleFormPanel.add(authorTextField);
        authorTextField.setColumns(10);

        textToSpeechButton = new JButton("Text To Speech");
        textToSpeechButton.setIcon(new ImageIcon("icon/microphone.png"));
        textToSpeechButton.setForeground(new Color(255, 255, 255));
        textToSpeechButton.setBorderPainted(false);
        textToSpeechButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        textToSpeechButton.setBackground(new Color(42, 42, 43));
        textToSpeechButton.setBounds(28, 114, 246, 29);
        middleFormPanel.add(textToSpeechButton);

        lineToSpeechButton = new JButton("Line To Speech");
        lineToSpeechButton.setIcon(new ImageIcon("icon/microphone.png"));
        lineToSpeechButton.setForeground(new Color(255, 255, 255));
        lineToSpeechButton.setBorderPainted(false);
        lineToSpeechButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        lineToSpeechButton.setBackground(new Color(42, 42, 43));
        lineToSpeechButton.setBounds(28, 150, 246, 29);
        middleFormPanel.add(lineToSpeechButton);

        JPanel bottomFormPanel = new JPanel();
        bottomFormPanel.setPreferredSize(new Dimension(100, 250));
        bottomFormPanel.setBackground(new Color(42, 42, 43));
        formPanel.add(bottomFormPanel, BorderLayout.SOUTH);
        bottomFormPanel.setLayout(null);

        volumeSlider = new JSlider();
        volumeSlider.setOrientation(SwingConstants.VERTICAL);
        volumeSlider.setBounds(61, 65, 46, 145);
        volumeSlider.setMinimum(0);
        volumeSlider.setMaximum(99);
        volumeSlider.setValue(50);
        bottomFormPanel.add(volumeSlider);

        rateSlider = new JSlider();
        rateSlider.setBackground(new Color(42, 42, 43));
        rateSlider.setOrientation(SwingConstants.VERTICAL);
        rateSlider.setBounds(119, 65, 46, 145);
        rateSlider.setMinimum(0);
        rateSlider.setMaximum(200);
        rateSlider.setValue(100);
        bottomFormPanel.add(rateSlider);

        pitchSlider = new JSlider();
        pitchSlider.setOrientation(SwingConstants.VERTICAL);
        pitchSlider.setBounds(177, 65, 46, 145);

        pitchSlider.setMinimum(0);
        pitchSlider.setMaximum(200);
        pitchSlider.setValue(100);
        bottomFormPanel.add(pitchSlider);

        JLabel volumeLabel = new JLabel("V");
        volumeLabel.setForeground(new Color(255, 255, 255));
        volumeLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        volumeLabel.setBounds(80, 45, 16, 16);
        bottomFormPanel.add(volumeLabel);

        JLabel rateLabel = new JLabel("R");
        rateLabel.setForeground(new Color(255, 255, 255));
        rateLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        rateLabel.setBounds(138, 45, 16, 16);
        bottomFormPanel.add(rateLabel);

        JLabel pitchLabel = new JLabel("P");
        pitchLabel.setForeground(new Color(255, 255, 255));
        pitchLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        pitchLabel.setBounds(196, 45, 16, 16);
        bottomFormPanel.add(pitchLabel);
    }

    private void textArea() {
        JScrollPane scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        text = new JTextArea();
        text.setSelectionColor(new Color(100, 149, 237));
        text.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        text.setCaretColor(Color.WHITE);
        text.setForeground(SystemColor.activeCaption);
        text.setBorder(null);
        text.setBackground(new Color(51, 51, 51));
        scrollPane.setViewportView(text);
    }

    private void menuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(null);
        menuBar.setBackground(new Color(255, 255, 255));
        menuBar.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        menuBar.add(fileMenu);

        JMenu editMenu = new JMenu("Edit");
        editMenu.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        menuBar.add(editMenu);

        JMenu ttsMenu = new JMenu("Trasform Text");
        ttsMenu.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        menuBar.add(ttsMenu);

        JMenu encodeMenu = new JMenu("Encode");
        encodeMenu.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        menuBar.add(encodeMenu);

        JMenu aboutMenu = new JMenu("About");
        aboutMenu.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        menuBar.add(aboutMenu);

        JMenu replayMenu = new JMenu("Replay");
        aboutMenu.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        menuBar.add(replayMenu);

        /**
         * ~~~~~~~~~~~~~~~~~~~~~~~ ADDING BUTTONS ~~~~~~~~~~~~~~~~~~~~~~~
         */
        JMenuItem newDocumentMenuItem = new JMenuItem("New Document");
        newDocumentMenuItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        fileMenu.add(newDocumentMenuItem);
        KeyStroke keyStrokeToNew = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
        newDocumentMenuItem.setAccelerator(keyStrokeToNew);

        JMenuItem openDocumentMenuItem = new JMenuItem("Open Document");
        openDocumentMenuItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        fileMenu.add(openDocumentMenuItem);
        KeyStroke keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
        openDocumentMenuItem.setAccelerator(keyStrokeToOpen);

        JMenuItem giveFilenameMenuItem = new JMenuItem("Open Document via path");
        giveFilenameMenuItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        fileMenu.add(giveFilenameMenuItem);

        JMenuItem saveDocumentMenuItem = new JMenuItem("Save Document");
        saveDocumentMenuItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        fileMenu.add(saveDocumentMenuItem);

        JSeparator separator = new JSeparator();
        fileMenu.add(separator);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        fileMenu.add(exitMenuItem);
        KeyStroke keyStrokeToExit = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK);
        exitMenuItem.setAccelerator(keyStrokeToExit);

        // ~~~~~~~~~~ EDIT ~~~~~~~~~~~~~~~~~~~
        JMenuItem editAuthorItem = new JMenuItem("Edit Author");
        editAuthorItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        editMenu.add(editAuthorItem);

        JMenuItem editTitleItem = new JMenuItem("Edit Title");
        editAuthorItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        editMenu.add(editTitleItem);

        JMenuItem editTextItem = new JMenuItem("Edit Text");
        editTextItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        editMenu.add(editTextItem);
        KeyStroke keyStrokeToEdit = KeyStroke.getKeyStroke(KeyEvent.VK_S,
                KeyEvent.CTRL_DOWN_MASK);
        editTextItem.setAccelerator(keyStrokeToEdit);


        // ~~~~~~~~~~~~~ TTS ~~~~~~~~~~~~~~~~~~
        JMenuItem ttsAllItem = new JMenuItem("Trasform All");
        ttsAllItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        ttsMenu.add(ttsAllItem);

        JMenuItem ttsLineItem = new JMenuItem("Transform Line");
        ttsLineItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        ttsMenu.add(ttsLineItem);
        KeyStroke keyStrokeToLine = KeyStroke.getKeyStroke(KeyEvent.VK_L,
                KeyEvent.CTRL_DOWN_MASK);
        ttsLineItem.setAccelerator(keyStrokeToLine);

        JMenuItem ttsRevAllItem = new JMenuItem("Transform All Reverse");
        ttsRevAllItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        ttsMenu.add(ttsRevAllItem);

        JMenuItem ttsRevLineItem = new JMenuItem("Transform Line Reverse");
        ttsRevLineItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        ttsMenu.add(ttsRevLineItem);

        // ~~~~~~~~~~~~~ ENCODE ~~~~~~~~~~~~~~~~~~
        JMenuItem atBashEncoding = new JMenuItem("AtBash Encode All");
        atBashEncoding.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        encodeMenu.add(atBashEncoding);

        JMenuItem atBashEncodingLine = new JMenuItem("AtBash Encode Line");
        atBashEncodingLine.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        encodeMenu.add(atBashEncodingLine);

        encodeMenu.add(new JSeparator());

        JMenuItem rot13Encoding = new JMenuItem("Rot13 Encode All");
        rot13Encoding.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        encodeMenu.add(rot13Encoding);

        JMenuItem rot13EncodingLine = new JMenuItem("Rot13 Encode Line");
        rot13EncodingLine.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        encodeMenu.add(rot13EncodingLine);

        // ~~~~~~~~~~~~~ ABOUT ~~~~~~~~~~~~~~~~~~
        JMenuItem infoItem = new JMenuItem("Document Info");
        infoItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        aboutMenu.add(infoItem);

        // ~~~~~~~~~~~~~ REPLAY ~~~~~~~~~~~~~~~~~~
        JMenuItem replayItem = new JMenuItem("Replay Commands");
        infoItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        replayMenu.add(replayItem);
        KeyStroke keyStrokeToReplay = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
        replayItem.setAccelerator(keyStrokeToReplay);

        JMenuItem clearReplayItem = new JMenuItem("Clear Replay");
        clearReplayItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        replayMenu.add(clearReplayItem);

        JCheckBoxMenuItem trackCommandsItem = new JCheckBoxMenuItem("Tracking Commands");
        trackCommandsItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        replayMenu.add(trackCommandsItem);

        /**
         * ~~~~~~~~~~~~~~~~~~~~~~~ COMMAND FACTORY ~~~~~~~~~~~~~~~~~~~~~~~
         */
        CommandsFactory commandsFactory = new CommandsFactory(text, titleTextField, authorTextField,
                editAuthorItem, editTitleItem, ttsRevAllItem, rot13Encoding,
                rot13EncodingLine, replayItem, volumeSlider, pitchSlider,
                rateSlider, giveFilenameMenuItem, ttsRevLineItem);

        /**
         * ~~~~~~~~~~~~~~~~~~~~~~~ ACTIONS LISTENERS ~~~~~~~~~~~~~~~~~~~~~~~
         */

        ActionListener exitListener = commandsFactory.createCommand("ExitListener");
        exitMenuItem.addActionListener(exitListener);

        ActionListener newDocumentListener = commandsFactory.createCommand("NewDocument");
        newDocumentMenuItem.addActionListener(newDocumentListener);

        ActionListener openDocumentListener = commandsFactory.createCommand("OpenDocument");
        openDocumentMenuItem.addActionListener(openDocumentListener);
        giveFilenameMenuItem.addActionListener(openDocumentListener);

        ActionListener editDocumentListener = commandsFactory.createCommand("EditDocument");
        editTextItem.addActionListener(editDocumentListener);
        editAuthorItem.addActionListener(editDocumentListener);
        editTitleItem.addActionListener(editDocumentListener);

        ActionListener aboutDocumentListener = commandsFactory.createCommand("AboutDocument");
        infoItem.addActionListener(aboutDocumentListener);

        ActionListener saveDocumentListener = commandsFactory.createCommand("SaveDocument");
        saveDocumentMenuItem.addActionListener(saveDocumentListener);

        ActionListener textToSpeechListener = commandsFactory.createCommand("DocumentToSpeech");
        ttsAllItem.addActionListener(textToSpeechListener);
        textToSpeechButton.addActionListener(textToSpeechListener);
        ttsRevAllItem.addActionListener(textToSpeechListener);

        ActionListener lineToSpeechListener = commandsFactory.createCommand("LineToSpeech");
        ttsLineItem.addActionListener(lineToSpeechListener);
        lineToSpeechButton.addActionListener(lineToSpeechListener);
        ttsRevLineItem.addActionListener(lineToSpeechListener);

        ActionListener encodeDocument = commandsFactory.createCommand("EncodeDocument");
        rot13Encoding.addActionListener(encodeDocument);
        atBashEncoding.addActionListener(encodeDocument);

        ActionListener replayCommand = commandsFactory.createCommand("ReplayCommand");
        replayItem.addActionListener(replayCommand);
        clearReplayItem.addActionListener(replayCommand);

        ItemListener boxItem = new BoxItem(trackCommandsItem);
        trackCommandsItem.addItemListener(boxItem);

        ChangeListener tuneAudio = new TuneAudio(volumeSlider, rateSlider, pitchSlider);
        volumeSlider.addChangeListener(tuneAudio);
        rateSlider.addChangeListener(tuneAudio);
        pitchSlider.addChangeListener(tuneAudio);

    }
}
