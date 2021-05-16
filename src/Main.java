import javax.swing.SwingUtilities;

import za.ac.uj.acsse.csc3a.gui.Frame;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{
			new Frame("Theorem Prover");
		});

	}

}
