package commands;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class ExitListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		int option;
		System.out.println("1 exit");
		option = JOptionPane.showConfirmDialog(null,"Are you sure?",
				"Exit Confirmation", JOptionPane.YES_NO_OPTION);
		if (option == 0) {
			System.exit(0);
		}
	}

}
