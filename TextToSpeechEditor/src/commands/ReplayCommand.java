package commands;

import view.Text2SpeechEditorView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ReplayCommand implements ActionListener {
    private JMenuItem replayCommand;

    public ReplayCommand(JMenuItem replayCommand) {
        this.replayCommand = replayCommand;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == replayCommand) {
            Text2SpeechEditorView.getSingletonView().replay();
            return;
        }
        Text2SpeechEditorView.getSingletonView().clearReplayManager();
    }

}
