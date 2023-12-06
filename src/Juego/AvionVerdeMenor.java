package Juego;

import java.io.IOException;
import java.awt.*;

import javax.imageio.ImageIO;

public class AvionVerdeMenor extends Enemigo {
    
    public AvionVerdeMenor(String filename, int x, int y, boolean shot) {
        super(filename);
        this.cajaColision = new Rectangle((int) x, (int) y, 40, 40);
        spritePosition = 1;
        this.positionX = x;
        this.positionY = y;
        this.puntosAlMorir = 10;
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
                this.updateArma((int) this.positionX + 15, (int) this.positionY - 15);
                if (timerDisparo > 2.5) {
                    this.disparar();
                    timerDisparo = 0;
                } else {
                    timerDisparo += delta;
                }

            }
            try {
                spriteCounter++;
                spritePosition = 0;
                if (spriteCounter > 10) {

                    if (spritePosition == 1) {
                        spritePosition = 2;
                    } else if (spritePosition == 2) {
                        spritePosition = 1;
                    }

                    imagen = ImageIO.read(getClass().getResource("imagenes/AvionVerdeMenor" + spritePosition + ".png"));
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
                if (spriteCounter > 7) {
                    spritePosition++;
                    if (spritePosition > 7) {
                        spritePosition = 1;
                    }

                    imagen = ImageIO.read(getClass().getResource("imagenes/fuego" + spritePosition + ".png"));
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
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 40, 40, null, null);
    }

    public void restaurar() {
        isVisible = false;
        this.estado = estadoEnemigo.DESACTIVADO;
        this.timer = 0;
        this.cajaColision.y = (int) this.positionY;
        this.cajaColision.x = (int) this.positionX;
        try {
            this.imagen = ImageIO.read(getClass().getResource("imagenes/AvionVerdeMenor1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
