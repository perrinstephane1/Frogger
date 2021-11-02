package frogger;

import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class represents and array containing the scores of the game
 * @author Williams HOARAU
 * @author Louis JOGUET
 * @author Aurelien PARAIRE
 * @author Stephane PERRIN
 *
 */
public class HallOfFame extends ArrayList<Score> {

    /**
     * This method
     * @param pseudo This String is the pseudo chosen by the player.
     * @param time This float corresponds to the time it took the player to finish the game
     */
    public void addScore(String pseudo, float time) {
        Score score = new Score(time, pseudo);
        this.sort(Comparator.naturalOrder());
        if (this.size()<5) {
            this.add(score);
            this.sort(Comparator.naturalOrder());
        } else if (score.compareTo(this.get(4))<0) {
            this.add(score);
            this.sort(Comparator.naturalOrder());
            this.remove(5);
        }
    }

    /**
     * This method shows the 5 best times ever made in the game
     */
    public void display() {
        System.out.println(this);
    }

    /**
     * This method saves the time the player took to finish the game
     * @param file This String is the name of the text file used to store the times the players took to finish the game
     */
    public void save(String file) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(file));
            for (int ii=0; ii<this.size(); ii++) {
                pw.println(this.get(ii));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method loads the file that contains the times the players took to finish the game
     * @param file This String is the name of the text file used to store the times the players took to finish the game
     */
    public void load(String file) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String st = null;
            while ((st = br.readLine()) != null) {
                st.replace(" ", "");
                String[] temp = st.split(":");
                temp[1] = temp[1].replace("s", "");
                this.addScore(temp[0], Float.valueOf(temp[1]));
            }
            this.sort(Comparator.naturalOrder());
        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable");
        } catch (IOException e) {
            System.out.println("Impossible de lire les données");
        } finally {
            try {   
                if (br != null)
                    br.close();
            } catch (IOException e) {
                System.out.println("Impossible de fermer le fichier");
            }
        }
    }

}
