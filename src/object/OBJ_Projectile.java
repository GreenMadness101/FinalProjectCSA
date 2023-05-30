package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Projectile extends Projectile {

    GamePanel gp;
    public OBJ_Projectile(GamePanel gp) {
        super(gp);
        this.gp = gp;
        //TODO Auto-generated constructor stub
        setName("Fireball");
        setSpeed(5);
        setMaxLife(80);
        setLife(getMaxLife());
        //attack = 2;
        //alive = false;
        getImage();


    }
    //update image files here
    public void getImage()
    {
     try {
        setUp1(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/fireball_up_1.png")));
        setUp2(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/fireball_up_2.png")));
        setDown1(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/fireball_down_1.png")));
        setDown2(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/fireball_down_2.png")));
        

    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
     


    }
}
