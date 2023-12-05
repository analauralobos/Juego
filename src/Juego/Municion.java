package Juego;

import java.awt.*;

public abstract class Municion extends ObjetoGrafico implements Movimiento {

    protected Rectangle cajaColisionMunicion;
    protected boolean hitEnemigo;
    protected boolean hitBonus;
    protected int velocidad;

    public Municion(String filename) {
        super(filename);
        this.hitEnemigo = false;
        this.hitBonus = false;
    }

    protected void updateHitbox() {
        cajaColisionMunicion.x = (int) this.positionX;
        cajaColisionMunicion.y = (int) this.positionY;
    }

    public void hitEnemigo() {
        this.hitEnemigo = true;
    }

    public void hitBonus() {
        this.hitBonus = true;
    }

    public void visibleOff() {
        this.isVisible = false;
    }
}
