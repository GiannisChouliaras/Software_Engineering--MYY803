package commands;

import java.awt.event.ActionEvent;

public interface ActionListener extends java.awt.event.ActionListener {

    /**
     * @param actionEvent
     * here is the method actionPerformed where all the others will implement. is it gonna change cause of the swing (?)
     */
    public void actionPerformed(ActionEvent actionEvent);

}
