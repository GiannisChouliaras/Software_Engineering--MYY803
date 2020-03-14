package commands;

import controller.Database;
import model.Document;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;

public class TuneAudio implements ChangeListener {


    /**
     * Constructor.
     * @param database
     * @param author
     * @param title
     * @param volumeSlider
     * @param pitchSlider
     * @param rateSlider
     */
    public TuneAudio(Database database, JTextField author, JTextField title,
                     JSlider volumeSlider, JSlider pitchSlider, JSlider rateSlider,
                     JLabel volumeValue, JLabel pitchValue, JLabel rateValue)
    {
        this.database     = database;
        this.author       = author;
        this.title        = title;
        this.volumeSlider = volumeSlider;
        this.rateSlider   = rateSlider;
        this.pitchSlider  = pitchSlider;
        this.volumeValue  = volumeValue;
        this.pitchValue   = pitchValue;
        this.rateValue    = rateValue;
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        String authorString = author.getText();
        String titleString = title.getText();

        if (database.containsDocument(authorString,titleString))
        {
            Document document = database.getDocument(authorString, titleString);
            if (changeEvent.getSource() == volumeSlider) {
                document.setVolumeDocument(volumeSlider.getValue());
                volumeValue.setText(Integer.toString(volumeSlider.getValue()));
            } else if (changeEvent.getSource() == rateSlider) {
                document.setRateDocument(rateSlider.getValue());
                rateValue.setText(Integer.toString(rateSlider.getValue()));
            } else {
                document.setPitchDocument(pitchSlider.getValue());
                pitchValue.setText(Integer.toString(pitchSlider.getValue()));
            }
        }
        else
            JOptionPane.showMessageDialog(null, "no database with this author and title");
    }


    /**
     * Fields
     */
    private Database database;
    private JTextField author, title;
    private JSlider volumeSlider, pitchSlider, rateSlider;
    private JLabel volumeValue, pitchValue, rateValue;
}
