import java.util.ArrayList;

public class Piste {
    protected String terrain;
    protected boolean hostile;
    protected double sens;
    protected double vitesse;
    protected double taille_obstacle;
    protected java.util.ArrayList<Truc_mobile> trucs;
    protected boolean arrivee; // si c'est la dernière ligne c'est FINI


    public Piste(String terrain, boolean hostile, double sens, double vitesse, double taille_obstacle, boolean arrivee){
        this.vitesse = vitesse;
        this.sens = sens;
        this.taille_obstacle = taille_obstacle;
        //this.scene = scene;
        //this.height = this.scene.getHeight() / 10.0D;
    }
}
