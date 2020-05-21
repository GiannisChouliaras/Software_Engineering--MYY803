package commands;

import java.awt.event.ActionEvent;

import javax.swing.*;

import controller.Controller;
import model.Document;

public class EditDocument implements ActionListener {


    public EditDocument(Controller controller, JTextField author, JTextField title,
                        JTextArea textArea, JMenuItem editAuthorItem, JMenuItem editTitleItem)
    {
        this.author         = author;
        this.title          = title;
        this.controller     = controller;
        this.textArea       = textArea;
        this.editAuthorItem = editAuthorItem;
        this.editTitleItem  = editTitleItem;
    }

    /**
     * @param actionEvent
     * We implement here the method actionPerformed(ActionEvent actionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
    	int choice = -1;
    	String element = "";
    	String authorString = author.getText();
        String titleString = title.getText();
        if (controller.containsDocument(authorString, titleString)) {
        	if (actionEvent.getSource() == editAuthorItem) {
                element = JOptionPane.showInputDialog(null, "give new Author name:");
                choice = 0;
            }
        	else if (actionEvent.getSource() == editTitleItem) {
                element = JOptionPane.showInputDialog(null, "give new title");
                choice = 1;
            }
        	else {
        		element = textArea.getText();
                choice = 2;
            }
        	
        	controller.editDocument(authorString, titleString, choice, element);
        	if (choice == 1) {
        		title.setText(element);
        	} else if (choice == 0) {
        		author.setText(element);
        	}
        }
        else {
        	System.out.println("There is no doc with this author and title");
        }
    }


    private Controller controller;
    private JTextField author;
    private JTextField title;
    private JTextArea  textArea;
    private JMenuItem  editAuthorItem, editTitleItem;

}
