import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HallOfFame extends ArrayList<Score> {
    public void addScore(String pseudo, float time) {
        Score score = new Score(time, pseudo);
        this.sort(Comparator.naturalOrder());
        if (this.size()<5) {
            this.add(score);
            this.sort(Comparator.naturalOrder());
        } else if (score.compareTo(this.get(4))>0) {
            this.add(score);
            this.sort(Comparator.naturalOrder());
            this.remove(5);
        }
    }

    public void display() {
        System.out.println(this);
    }

    public void save() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("Scores.txt"));
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

    public void load(String file) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
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
