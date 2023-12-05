package Juego;

import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.*;

public class AvionRojo extends Enemigo {

    private double variableMovimiento = Math.PI;
    private int x_inicial;
    private int mov;

    public AvionRojo(String filename, int x, int y, boolean shot, String direccion) {
        super(filename);
        this.cajaColision = new Rectangle((int) x, (int) y, 40, 40);
        spritePosition = 1;
        this.positionX = x;
        this.positionY = y;
        this.x_inicial = x;
        this.puntosAlMorir = 50;
        this.puedeDisparar = shot;
        switch (direccion) {
            case "LEFT":
                this.mov = -1;
                break;
            case "RIGHT":
                this.mov = 1;
                break;
        }
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

            this.positionY += 1.3;

            positionX = x_inicial + (Math.sin(variableMovimiento) * (400) * mov);
            variableMovimiento += delta;

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

                    imagen = ImageIO.read(getClass().getResource("imagenes/AvionRojo" + spritePosition + ".png"));
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

    public void restaurar() {
        isVisible = false;
        this.estado = estadoEnemigo.DESACTIVADO;
        this.timer = 0;
        this.cajaColision.y = (int) this.positionY;
        this.cajaColision.x = (int) this.positionX;
        this.variableMovimiento = Math.PI;
        try {
            this.imagen = ImageIO.read(getClass().getResource("imagenes/AvionRojo0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void display(Graphics2D g2) {
        if (estado == estadoEnemigo.MUERTO) {
            g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 40, 40, null, null);
            
        } else {
            g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 40, 40, null, null);
            
        }

    }

}
