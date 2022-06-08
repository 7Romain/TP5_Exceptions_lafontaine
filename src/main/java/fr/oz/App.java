package fr.oz;

import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws IOException {
        boolean again = true;
        do {
            String input = "";

            try {
                input = JOptionPane.showInputDialog(null, "Quel fichier souhaitez-vous lire ?");
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "nom de fichier incorrecte");
                int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous lire un autre fichier", "Poursuite ?",
                        JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.NO_OPTION) {
                    again = false;
                }

            }

            if ((input == null || "".equals(input.trim()))) {
                JOptionPane.showMessageDialog(null, "nom de fichier incorrecte");
                int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous lire un autre fichier", "Poursuite ?",
                        JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.NO_OPTION) {
                    again = false;
                }
            } else {
                String fichier = input.replaceAll(String.valueOf((char) 0160), " ").trim();
                LineNumberReader lineNumberReader = null;
                String totale = " ";
                String ligneParTour;
                try {

                    FileReader fileReader = new FileReader(fichier);
                    fileReader = new FileReader(fichier);
                    lineNumberReader = new LineNumberReader(fileReader);

                    String ligneLue = null;
                    while ((ligneLue = lineNumberReader.readLine()) != null) {

                        ligneParTour = "Ligne - " + lineNumberReader.getLineNumber() + " - " + ligneLue;

                        System.out.println(ligneParTour);

                        totale += ligneParTour + "\n";
                    }

                    JOptionPane.showMessageDialog(null, totale, fichier, JOptionPane.INFORMATION_MESSAGE);
                    int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous lire un autre fichier", "Poursuite ?",
                            JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.NO_OPTION) {
                        break;
                    }

                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(null,
                            "Fichier introuvable \n" + fichier + " (Le fichier spécifié est introuvable)");
                    int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous lire un autre fichier", "Poursuite ?",
                            JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.NO_OPTION) {
                        again = false;
                    }

                } catch (HeadlessException e) {
                    JOptionPane.showMessageDialog(null, "Il ne reste plus de ligne à afficher");

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Impossible de lire le conten du fichier");
                    int reply = JOptionPane.showConfirmDialog(null, "Voulez-vous lire un autre fichier", "Poursuite ?",
                            JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.NO_OPTION) {
                        again = false;
                    }

                } finally {
                    try {
                        lineNumberReader.close();
                    } catch (IOException ioe) {
                        System.err.println("Impossible de fermer le fichier  " + fichier.toString());

                    } catch (NullPointerException npe) {
                        System.err.println("Impossible d'ouvrir le fichier  " + fichier.toString());

                    }
                }
            }

        } while (again);

    }
}
