package fr.oz.ui;

import javax.swing.JOptionPane;

public class DialogBoxDisplay {

	private DialogBoxDisplay() {

		// static class.
	}

	public static String askUserInput() {

		return JOptionPane.showInputDialog(null, "Quel fichier souhaitez-vous lire ?");
	}

	public static int showContinueQuestion() {

		return JOptionPane.showConfirmDialog(null, "Voulez-vous lire un autre fichier", "Poursuite ?",
				JOptionPane.YES_NO_OPTION);
	}

}
