package commands.replay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionsManager {

    private ActionEvent actionEvent;
    private ActionListener actionListener;

    public ActionsManager(ActionEvent actionEvent, ActionListener actionListener) {
        this.actionEvent = actionEvent;
        this.actionListener = actionListener;
    }

    public void replay() {
        actionListener.actionPerformed(actionEvent);
    }

}