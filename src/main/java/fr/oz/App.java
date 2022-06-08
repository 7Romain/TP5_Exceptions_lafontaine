package fr.oz;

import static fr.oz.ui.DialogBoxDisplay.askUserInput;
import static fr.oz.ui.DialogBoxDisplay.showContinueQuestion;

import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.swing.JOptionPane;

import fr.oz.exception.CustomException;

public class App {
	public static void main(String[] args) {

		String input = "";
		String fichier;
		String totale;
		boolean again = true;

		do {

			input = askUserInput();

			if ((input == null || "".equals(input.trim()))) {

				again = exitOrContinue("nom de fichier incorrecte");
			}

			else {

				fichier = input.replaceAll(String.valueOf((char) 0160), " ").trim();

				try {
					totale = read(input);
					JOptionPane.showMessageDialog(null, totale, fichier, JOptionPane.INFORMATION_MESSAGE);

					again = (showContinueQuestion() != JOptionPane.NO_OPTION);

				}

				catch (CustomException e) {

					again = exitOrContinue(e.getMessage());
				}

			}

		} while (again);

	}

	private static String read(String input) throws CustomException {

		String fichier = input.replaceAll(String.valueOf((char) 0160), " ").trim();
		String totale = " ";
		String ligneParTour;

		try (final FileReader fileReader = new FileReader(fichier);
				final LineNumberReader lineNumberReader = new LineNumberReader(fileReader);) {

			// filereader and lineNumberReader will be closed automatically.

			String ligneLue = null;

			while ((ligneLue = lineNumberReader.readLine()) != null) {

				ligneParTour = "Ligne - " + lineNumberReader.getLineNumber() + " - " + ligneLue;

				System.out.println(ligneParTour);

				totale += ligneParTour + "\n";
			}

		} catch (FileNotFoundException fnfe) {

			throw new CustomException("Fichier introuvable \n" + fichier + " (Le fichier spécifié est introuvable)");

		} catch (HeadlessException e) {

			throw new CustomException("Il ne reste plus de ligne à afficher");

		} catch (IOException e) {

			throw new CustomException("Impossible de lire le conten du fichier");

		}

		return totale;

	}

	private static boolean exitOrContinue(final String message) {

		JOptionPane.showMessageDialog(null, message);

		// System.exit(0) close the application.

		return (showContinueQuestion() != JOptionPane.NO_OPTION);

	}

}
