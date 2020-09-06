package commands;

import java.awt.event.ActionEvent;

public class ExitListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
