package commands.replay;

import java.util.ArrayList;

public class ReplayManager {

    private final ArrayList<ActionsManager> actions = new ArrayList<ActionsManager>();

    public void replay() {
        for (ActionsManager action : actions) {
            action.replay();
        }
    }

    public void addAction(ActionsManager action) {
        actions.add(action);
    }

    public void clearActions() {
        actions.clear();
    }

    public boolean containsAction(ActionsManager action) {
        return (actions.contains(action));
    }
}
