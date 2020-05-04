package commands;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ReplayCommand implements ActionListener {

    /**
     * Constructor of the class ReplayCommand
     */
    public ReplayCommand(ReplayManager replayManager, JMenuItem replayItem)
    {
        this.replayManager = replayManager;
        this.replayItem    = replayItem;
    }

    /**
     * @param actionEvent
     * We implement here the method actionPerformed(ActionEvent actionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if (actionEvent.getSource() == replayItem) {
            replayManager.replay();
        }
        else {
            replayManager.clearData();
        }
    }

    /** Fields */
    private ReplayManager replayManager;
    private JMenuItem     replayItem;
}
