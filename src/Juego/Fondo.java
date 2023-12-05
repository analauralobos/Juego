package Juego;

import java.awt.*;

public class Fondo extends ObjetoGrafico {

    public Fondo(String filename) {
        super(filename);
        setPosition(0, -6400);
    }

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 815, 7000, null, null);
    }

}