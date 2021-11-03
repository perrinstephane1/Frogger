package frogger;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
     * This method adds the score of the player if it is good enough
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
            for (Score score : this) {
                pw.println(score);
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
            String st;
            while ((st = br.readLine()) != null) {
//                st.replace(" ", "");
                String[] temp = st.split(":");
                temp[1] = temp[1].replace("s", "");
                this.addScore(temp[0], Float.parseFloat(temp[1]));
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

    public void affiche(){
        Stage stage=new Stage();
        GridPane gridPane = new GridPane();
        Label joueurs= new Label("Player");
        Label timer=new Label("Time");
        gridPane.add(joueurs,0,0);
        gridPane.add(timer,1,0);
        for (int i=0;i<this.size();i++){
            Label name=new Label(this.get(i).getName()+" : ");
            Label time=new Label(String.valueOf(this.get(i).getTime()));
            gridPane.add(name,0,i+1);
            gridPane.add(time,1,i+1);
        }
        gridPane.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(gridPane,200,300));
        stage.setTitle("Hall of Fame");
        stage.show();
    }
}
