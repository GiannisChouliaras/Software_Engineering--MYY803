package commands;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class InfoListener implements ActionListener {


    /**
     * Constructor
     * @param controller
     * @param author
     * @param title
     */
    public InfoListener(Controller controller, JTextField author, JTextField title) {
        this.controller = controller;
        this.author   	= author;
        this.title    	= title;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (controller.containsDocument(author.getText(), title.getText())) {
            JOptionPane.showMessageDialog(null, 
            		controller.getDocument(author.getText(), title.getText()).infoButton());
        }
        else {
            System.out.println("There is no Document with this Author and Title. :( ");
        }
    }

    /**
     * Private fields
     */
    private JTextField author, title;
    private Controller controller;
}
