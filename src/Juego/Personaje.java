package Juego;

import java.awt.*;
import java.awt.image.*;
import java.awt.Graphics2D;

public abstract class Personaje extends ObjetoGrafico implements Movimiento {

    protected int spriteCounter = 0;
    protected int spritePosition; 
    protected Rectangle cajaColision; 
    protected Arma arma; 
    protected boolean puedeDisparar;

    public Personaje(String filename) {
        super(filename);

    }

    public void setImagen(BufferedImage img) {
        this.imagen = img;
    }

    public abstract void display(Graphics2D g2);

    public abstract void update(double delta);

    protected void updateCajaColision() {
        cajaColision.x = (int) this.getX();
        cajaColision.y = (int) this.getY();
    }

    protected void updateArma(int x, int y) {
        arma.setPosition(x, y);
    }

    public void disparar() {
        if (puedeDisparar) {
            arma.disparo();
        }
    }

}
