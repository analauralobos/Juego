package Juego;

import java.awt.*;

public class Escopeta extends Municion {

    public Escopeta(String filename, int x, int y) {
        super(filename);
        this.positionX=x;
        this.positionY=y;
        this.isVisible=true;
        cajaColisionMunicion=new Rectangle((int)this.positionX,(int)this.positionY,14,30);

    }

    public void update(double delta) {

        if(hitEnemigo || hitBonus){
            this.isVisible=false;
            return;
        }
        
        updateHitbox();
        this.positionY=this.positionY-(15);

        

        if(this.positionY<30){
        this.isVisible=false;
        }
    }

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY,14,30,null);
        // g2.draw(cajaColisionMunicion); 
    }
}
