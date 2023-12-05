package Juego;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class ObjetoGrafico {
    protected BufferedImage imagen = null;
    protected boolean isVisible;
    protected double positionX = 0;
    protected double positionY = 0;

    
    public ObjetoGrafico(String filename) {
        try {
            imagen = ImageIO.read(getClass().getResource(filename));

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public int getWidth() {
        return imagen.getWidth();
    }

    public int getHeight() {

        return imagen.getHeight();
    }

    public void setPosition(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY,null);
    }

    public double getX() {
        return positionX;
    }

    public double getY() {
        return positionY;
    }

    public void setX(double x) {
        this.positionX=x;
    }

    public void setY(double y) {
        this.positionY=y;
    }
}