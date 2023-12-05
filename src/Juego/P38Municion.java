package Juego;

import java.awt.*;

public class P38Municion extends Municion {

    P38Municion(String filename, int x, int y) {
        super(filename);
        this.positionX = x;
        this.positionY = y;
        this.isVisible = true;
        cajaColisionMunicion = new Rectangle((int) this.positionX, (int) this.positionY, 14, 30);
    }

    @Override
    public void update(double delta) {

        if (hitEnemigo || hitBonus) {
            this.isVisible = false;
            return;
        }

        this.positionY = this.positionY - (10);
        updateHitbox();

        if (this.positionY < 30) {
            this.isVisible = false;
        }
    }

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 14, 30, null);
    }
}
