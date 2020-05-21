package commands;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.*;


import controller.Controller;
import view.FileTypeFilter;

public class OpenDocument implements ActionListener {

    public OpenDocument(Controller controller, JTextField authorString, JTextField titleString, JTextArea textArea,
						JSlider volumeSlider, JSlider pitchSlider, JSlider rateSlider,
						JLabel volumeValue, JLabel pitchValue, JLabel rateValue)
	{
        this.authorString = authorString;
        this.titleString  = titleString;
        this.textArea     = textArea;
        this.controller   = controller;
        this.volumeSlider = volumeSlider;
        this.volumeValue  = volumeValue;
        this.pitchSlider  = pitchSlider;
        this.pitchValue   = pitchValue;
        this.rateSlider   = rateSlider;
        this.rateValue    = rateValue;
    }

	/**
	 * In this method,
	 * 1. open a dialog file chooser.
	 * 2. im choosing one file (.tts).
	 * 3. i read the 4 first lines so i can see in my db if i have a doc with these fields.
	 * 4. If my db contains this doc, i should ask: you want to change the db text to whatever txt file has?
	 * 5. depends on the button he press. if Yes, we replace all the text and ArrayList of db's doc to tts.
	 * 6. If No, i keep the db doc and ask if he wants to change/save the tts content to the current db.doc.text
	 * @param actionEvent
	 */
	@Override
    public void actionPerformed(ActionEvent actionEvent)
	{
    	JFileChooser fs = new JFileChooser(new File("C:\\"));
    	fs.setDialogTitle("Open a File");
    	fs.setFileFilter(new FileTypeFilter(".tts", "TTS File"));
    	
    	int result = fs.showOpenDialog(null);
    	
    	if (result == JFileChooser.APPROVE_OPTION) {
    		try {
    			File file = fs.getSelectedFile();
    			BufferedReader bf = new BufferedReader(new FileReader(file.getPath()));
    			String line = "";
    			String fullTextString = "";

    			String author = bf.readLine(); // take author.
    			String title = bf.readLine();  // take title.
    			String date = bf.readLine();   // take original date.
    			String modDate = bf.readLine();// take modified date.
				//read all text.
    			while ((line = bf.readLine()) != null)
    				fullTextString += line + "\n";
    			fullTextString = fullTextString.substring(0,fullTextString.length() -1);

				// close the buffer
    			if (bf != null) bf.close();
    			
    			//here we actually do the work.
    			doWork(author,title,fullTextString,date,modDate);
    			
    		} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,"oh wow");
			}
    	}
    }

    private void placeInfo(ArrayList<String> info)
	{
		authorString.setText(info.get(0));
		titleString.setText(info.get(1));
		textArea.setText(info.get(2));
	}

    /**
     * Here we actually do the work with the controller. 
     * We can test this to see if Open Command is working fine.
     * @param author
     * @param title
     * @param text
     * @param date
     * @param modDate
     */
    private void doWork(String author, String title, String text,String date, String modDate)
    {
    	//here we implement 4,5,6.
		if (controller.containsDocument(author,title)) {
			ArrayList<String> info = new ArrayList<String>();
			if (controller.sameContents(author, title, text, info)) {
				placeInfo(info);
			}
			else {
				int option = JOptionPane.showConfirmDialog(null,"There are changes" +
						" in database of this document.\nDo you want to keep the txt file and delete the changes?");
				
				info = controller.getInfoToOpen(author, title, text, option);
				placeInfo(info);
				
			}
			volumeSlider.setValue(Integer.valueOf(info.get(3)));
			pitchSlider.setValue(Integer.valueOf(info.get(4)));
			rateSlider.setValue(Integer.valueOf(info.get(5)));
			volumeValue.setText(info.get(3));
			pitchValue.setText(info.get(4));
			rateValue.setText(info.get(5));
		}
		else { // database does not contain a document like this.
			controller.secondNewDocument(author, title, date, modDate, text);
			authorString.setText(author);
			titleString.setText(title);
			textArea.setText(text);
			volumeSlider.setValue(50);
			pitchSlider.setValue(100);
			rateSlider.setValue(100);
			volumeValue.setText("50");
			pitchValue.setText("100");
			rateValue.setText("100");
		}
    }

	private JTextField	authorString;
	private JTextField	titleString;
	private JTextArea	textArea;
	private Controller	controller;
	private JSlider 	volumeSlider, rateSlider, pitchSlider;
	private JLabel		volumeValue, rateValue, pitchValue;
} // end of class
