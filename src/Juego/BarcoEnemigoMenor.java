package Juego;

import java.io.IOException;
import java.awt.*;

import javax.imageio.ImageIO;

public class BarcoEnemigoMenor extends Enemigo {

    public BarcoEnemigoMenor(String filename, int x, int y, boolean shot) {
        super(filename);
        this.cajaColision = new Rectangle((int) x, (int) y, 38, 170);
        spritePosition = 1;
        this.positionX = x;
        this.positionY = y;
        this.puntosAlMorir = 50;
        this.puedeDisparar = shot;
        if (puedeDisparar) {
            this.arma = new Arma(Arma.tipoMunicion.ENEMIGA, 0, 0);
        }

    }

    @Override
    public void update(double delta) {

        if (this.positionY > 600) {
            this.isVisible = false;
            return;
        }

        if (estado == estadoEnemigo.VIVO && this.isVisible) {
            this.positionY += 2;//VELOCIDAD 0.7
            this.updateCajaColision();
            if (puedeDisparar) {
                this.updateArma((int) this.positionX + 15, (int) this.positionY);
                if (timerDisparo > 2.5) {
                    this.disparar();
                    timerDisparo = 0;
                } else {
                    timerDisparo += delta;
                }

            }
            try {
                spriteCounter++;
                if (spriteCounter > 10) {

                    if (spritePosition == 2) {
                        spritePosition = 1;
                    } else if (spritePosition == 1) {
                        spritePosition = 2;
                    }

                    imagen = ImageIO.read(getClass().getResource("imagenes/BarcoEnemigoMenor" + spritePosition + ".png"));
                    spriteCounter = 0;
                }

            } catch (IOException e) {
                System.out.println(e);
            }

            return;
        }

        if (estado == estadoEnemigo.MUERTO) {
            try {

                spriteCounter++;
                if (spriteCounter > 8) {
                    spritePosition++;
                    if (spritePosition > 8) {
                        spritePosition = 1;
                    }

                    imagen = ImageIO.read(getClass().getResource("imagenes/fuegoAux" + spritePosition + ".png"));
                    spriteCounter = 0;

                    if (isVisible) {
                        this.morir(delta);
                    }
                }

            } catch (IOException e) {
                System.out.println(e);
            }
            return;
        }

        if (estado == estadoEnemigo.DESACTIVADO) {
            this.desactivado();

        }

    }

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 38, 176, null, null);
    }

    public void restaurar() {
        isVisible = false;
        this.estado = estadoEnemigo.DESACTIVADO;
        this.timer = 0;
        this.cajaColision.y = (int) this.positionY;
        this.cajaColision.x = (int) this.positionX;
        try {
            this.imagen = ImageIO.read(getClass().getResource("imagenes/BarcoEnemigoMenor1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
