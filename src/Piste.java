import java.util.ArrayList;

public class Piste {
    private String terrain;
    private boolean hostile;
    private double sens;
    private double vitesse;
    private double taille_obstacle;
    private java.util.ArrayList<Truc_mobile> trucs;
    private boolean arrivee; // si c'est la dernière ligne c'est FINI
}
