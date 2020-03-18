package commands;

import controller.Controller;
import model.Document;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

public class TuneAudio implements ChangeListener {


    /**
     * Constructor.
     * @param controller
     * @param author
     * @param title
     * @param volumeSlider
     * @param pitchSlider
     * @param rateSlider
     */
    public TuneAudio(Controller controller, JTextField author, JTextField title,
                     JSlider volumeSlider, JSlider pitchSlider, JSlider rateSlider,
                     JLabel volumeValue, JLabel pitchValue, JLabel rateValue)
    {
        this.controller   = controller;
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

        if (controller.containsDocument(authorString,titleString))
        {
            Document document = controller.getDocument(authorString, titleString);
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
    private Controller controller;
    private JTextField author, title;
    private JSlider volumeSlider, pitchSlider, rateSlider;
    private JLabel volumeValue, pitchValue, rateValue;
}
