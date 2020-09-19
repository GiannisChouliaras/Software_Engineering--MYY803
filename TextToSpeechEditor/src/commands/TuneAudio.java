package commands;

import view.Text2SpeechEditorView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TuneAudio implements ChangeListener {

    private final JSlider volumeSlider;
    private final JSlider rateSlider;
    private final JSlider pitchSlider;

    public TuneAudio(JSlider volumeSlider, JSlider rateSlider, JSlider pitchSlider) {
        this.volumeSlider = volumeSlider;
        this.rateSlider = rateSlider;
        this.pitchSlider = pitchSlider;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Text2SpeechEditorView.getSingletonView().setTunes(volumeSlider.getValue(),
                pitchSlider.getValue(), rateSlider.getValue());
    }
}
