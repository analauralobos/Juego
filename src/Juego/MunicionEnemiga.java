package Juego;

import java.awt.*;

public class MunicionEnemiga extends Municion {

    private double x2;
    private double y2;
    private double m;
    private double b;

    public MunicionEnemiga(String filename, int x, int y) {
        super(filename);
        this.positionX = x;
        this.positionY = y;
        this.isVisible = true;
        cajaColisionMunicion = new Rectangle((int) this.positionX, (int) this.positionY, 13, 13);
    }

    private void calcularRecta() {
        this.m = (y2 - this.positionY) / ((x2 - this.positionX));
        this.b = y2 - (m * x2);
    }

    private double calcularX(double y) {
        double x;
        x = (y - b) / m;
        return x;
    }

    private double calcularY(double x) {
        double y;
        y = (m * x) + b;
        return y;
    }

    @Override
    public void update(double delta) {
        if (hitEnemigo) {
            this.isVisible = false;
            return;
        }
        this.positionY = this.positionY + (5);//2

        updateHitbox();

        if (this.positionY > 600) {
            this.isVisible = false;
        }

    }

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 13, 13, null);
    }

}
