package commands;

import javax.swing.event.ChangeEvent;

public interface ChangeListener extends javax.swing.event.ChangeListener {

    /**
     * @param changeEvent
     * here is the method actionPerformed where TuneAudio will implement. is it gonna change cause of the swing (?)
     */
    public void stateChanged(ChangeEvent changeEvent);

}
