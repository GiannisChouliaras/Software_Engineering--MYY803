package view;

import commands.ActionListener;
import commands.CommandsFactory;
import controller.Database;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class Text2SpeechEditorView {

    /**
     * Fields
     */
    private JFrame frame;
    private JTextField titleTextField;
    private JTextField authorTextField;
    private Database database;
    private JTextArea textArea;
    private CommandsFactory commandFactory;
    private JButton textToSpeechButton, lineToSpeechButton;
    /**
     * Create the application.
     */
    public Text2SpeechEditorView() {
        database = new Database();
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
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

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Text To Speech Editor");
        frame.setBackground(new Color(42, 42, 43));
        frame.getContentPane().setBackground(new Color(42, 42, 43));
        frame.getContentPane().setFont(new Font("Helvetica Neue", Font.PLAIN, 13));

        FormPanel();
        TextArea();
        MenuBar();

        frame.setMinimumSize(new Dimension(1000, 700));
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void FormPanel() {
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
        textToSpeechButton.setBounds(28, 134, 246, 29);
        middleFormPanel.add(textToSpeechButton);

        lineToSpeechButton = new JButton("Line To Speech");
        lineToSpeechButton.setIcon(new ImageIcon("icon/microphone.png"));
        lineToSpeechButton.setForeground(new Color(255, 255, 255));
        lineToSpeechButton.setBorderPainted(false);
        lineToSpeechButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        lineToSpeechButton.setBackground(new Color(42, 42, 43));
        lineToSpeechButton.setBounds(28, 175, 246, 29);
        middleFormPanel.add(lineToSpeechButton);

        JPanel bottomFormPanel = new JPanel();
        bottomFormPanel.setPreferredSize(new Dimension(100, 250));
        bottomFormPanel.setBackground(new Color(42, 42, 43));
        formPanel.add(bottomFormPanel, BorderLayout.SOUTH);
        bottomFormPanel.setLayout(null);

        JSlider volumeSlider = new JSlider();
        volumeSlider.setOrientation(SwingConstants.VERTICAL);
        volumeSlider.setBounds(61, 65, 46, 145);
        bottomFormPanel.add(volumeSlider);

        JSlider rateSlider = new JSlider();
        rateSlider.setOrientation(SwingConstants.VERTICAL);
        rateSlider.setBounds(119, 65, 46, 145);
        bottomFormPanel.add(rateSlider);

        JSlider pitchSlider = new JSlider();
        pitchSlider.setOrientation(SwingConstants.VERTICAL);
        pitchSlider.setBounds(177, 65, 46, 145);
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
    } // end of method FormPanel

    private void TextArea() {
        JScrollPane scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        textArea = new JTextArea();
        textArea.setSelectionColor(new Color(100, 149, 237));
        textArea.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        textArea.setCaretColor(Color.WHITE);
        textArea.setForeground(SystemColor.activeCaption);
        textArea.setBorder(null);
        textArea.setBackground(new Color(51, 51, 51));
        scrollPane.setViewportView(textArea);
    } // end of method TextArea

    private void MenuBar() {

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(null);
        menuBar.setBackground(new Color(0x2A2A2B));
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


        /**
         * Adding here the buttons in the Menu.
         */
        JMenuItem newDocumentMenuItem = new JMenuItem("New Document");
        newDocumentMenuItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        fileMenu.add(newDocumentMenuItem);

        JMenuItem openDocumentMenuItem = new JMenuItem("Open Document");
        openDocumentMenuItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        fileMenu.add(openDocumentMenuItem);

        JMenuItem saveDocumentMenuItem = new JMenuItem("Save Document");
        saveDocumentMenuItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        fileMenu.add(saveDocumentMenuItem);

        JSeparator separator = new JSeparator();
        fileMenu.add(separator);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        fileMenu.add(exitMenuItem);

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

        // ~~~~~~~~~~~~~ TTS ~~~~~~~~~~~~~~~~~~
        JMenuItem ttsAllItem = new JMenuItem("Trasform All");
        ttsAllItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        ttsMenu.add(ttsAllItem);

        JMenuItem ttsLineItem = new JMenuItem("Transform Line");
        ttsLineItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        ttsMenu.add(ttsLineItem);

        JMenuItem ttsRevAllItem = new JMenuItem("Transform All Reverse");
        ttsRevAllItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        ttsMenu.add(ttsRevAllItem);

        JMenuItem ttsRevLineItem = new JMenuItem("Transform Line Reverse");
        ttsRevLineItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        ttsMenu.add(ttsRevLineItem);

        // ~~~~~~~~~~~~~ ABOUT ~~~~~~~~~~~~~~~~~~
        JMenuItem infoItem = new JMenuItem("Document Info");
        infoItem.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        aboutMenu.add(infoItem);

        commandFactory = new CommandsFactory(database, authorTextField, titleTextField, textArea,
                editAuthorItem, editTitleItem, ttsLineItem);

        /**
         * ~~~~~~~~~~~~~~~~~~~~~~~ ACTIONS LISTENERS ~~~~~~~~~~~~~~~~~~~~~~~
         */

        ActionListener exitListener = commandFactory.createCommand("ExitListener");
        exitMenuItem.addActionListener(exitListener);

        ActionListener newDocument = commandFactory.createCommand("NewDocument");
        newDocumentMenuItem.addActionListener(newDocument);

        ActionListener openDocument = commandFactory.createCommand("OpenDocument");
        openDocumentMenuItem.addActionListener(openDocument);

        ActionListener editDocument = commandFactory.createCommand("EditDocument");
        editTextItem.addActionListener(editDocument);
        editAuthorItem.addActionListener(editDocument);
        editTitleItem.addActionListener(editDocument);

        ActionListener saveDocument = commandFactory.createCommand("SaveDocument");
        saveDocumentMenuItem.addActionListener(saveDocument);

        ActionListener documentToSpeech = commandFactory.createCommand("DocumentToSpeech");
        textToSpeechButton.addActionListener(documentToSpeech);

        ActionListener lineToSpeech = commandFactory.createCommand("LineToSpeech");
        lineToSpeechButton.addActionListener(lineToSpeech);
        ttsLineItem.addActionListener(lineToSpeech);

        ActionListener infoListener = commandFactory.createCommand("InfoListener");
        infoItem.addActionListener(infoListener);

    } // end of method MenuBar

} //end of class Text2SpeechEditorView
