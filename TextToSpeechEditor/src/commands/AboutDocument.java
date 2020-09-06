package commands;

import view.Text2SpeechEditorView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AboutDocument implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Text2SpeechEditorView instance = Text2SpeechEditorView.getSingletonView();
        JOptionPane.showInternalMessageDialog(null, instance.getCurrentDocument(),
                "Documents info!", 1);
    }
}
