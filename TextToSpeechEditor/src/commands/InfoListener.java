package commands;

import controller.Database;
import model.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class InfoListener implements ActionListener {


    /**
     * Constructor
     * @param database
     * @param author
     * @param title
     */
    public InfoListener(Database database, JTextField author, JTextField title) {
        this.database = database;
        this.author   = author;
        this.title    = title;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (database.containsDocument(author.getText(), title.getText())) {
            Document document = database.getDocument(author.getText(), title.getText());
            JOptionPane.showMessageDialog(null, document.infoButton());
        }
        else {
            System.out.println("There is no Document with this Author and Title. :( ");
        }
    }

    /**
     * Private fields
     */
    private JTextField author, title;
    private Database database;
}
