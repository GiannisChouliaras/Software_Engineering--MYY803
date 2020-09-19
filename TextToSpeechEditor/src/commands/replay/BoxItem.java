package commands.replay;

import view.Text2SpeechEditorView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BoxItem implements ItemListener {

    private final JCheckBoxMenuItem trackCommands;

    public BoxItem(JCheckBoxMenuItem trackCommands) {
        this.trackCommands = trackCommands;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (trackCommands.isSelected()) {
            Text2SpeechEditorView.getSingletonView().setCanTrackCommands(true);
            return;
        }
        Text2SpeechEditorView.getSingletonView().setCanTrackCommands(false);
    }
}
