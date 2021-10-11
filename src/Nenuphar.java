import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Nenuphar extends Truc_mobile{
    private boolean hostile;
    private ImageView imageView;

    public Nenuphar(Piste piste, Scene scene, double l_case) {
        super(piste,scene,l_case);

        try {
            Image image = new Image(new FileInputStream("nenuphar.png"));
            this.imageView = new ImageView(image);
            this.imageView.setX(this.X);
            this.imageView.setY(this.Y);
            this.imageView.setFitHeight(this.l_case);
            this.imageView.setPreserveRatio(true);
        } catch (FileNotFoundException var7) {
            System.out.println("f***nenuphar");
            System.out.println(var7);
        }
    }
}
