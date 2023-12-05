package Juego;

import java.io.IOException;
import java.awt.*;

import javax.imageio.ImageIO;

public class Vikingo extends Enemigo {

    private double timerMovimientoY=0;
    private int mov=1;


    public Vikingo(String filename, int x, int y, boolean shot) {
        super(filename);
        this.cajaColision = new Rectangle((int) x, (int) y, 45, 45);
        spritePosition = 1;
        this.positionX = x;
        this.positionY = y;
        this.puntosAlMorir=30;
        this.puedeDisparar=shot;
        if(puedeDisparar){
            this.arma=new Arma(Arma.tipoMunicion.ENEMIGA, 0, 0);
        }
        
    }

    @Override
    public void update(double delta) {
        
        if(this.positionY>600){
            this.isVisible=false;
            return;
        }

        if (estado == estadoEnemigo.VIVO && this.isVisible) {
            if(timerMovimientoY<2){
              this.positionY+=0.7;
            }
            if(timerMovimientoY>2 && timerMovimientoY<5){
                if(85>this.positionX){
                    mov=mov*-1;
                }
                if(680<this.positionX){
                    mov=mov*-1;
                }
                this.positionX=this.positionX+(1*mov);
                this.positionY+=0.2;
            }
            if(timerMovimientoY>5){
                timerMovimientoY=0;
                mov=mov*-1;
            }

            timerMovimientoY+=delta;
            
            this.updateCajaColision();
            if(puedeDisparar){
                this.updateArma((int)this.positionX+15, (int)this.positionY);
                if(timerDisparo>2.5){
                    this.disparar();
                    timerDisparo=0;     
                }
                else{
                    timerDisparo+=delta;
                }
                
            }
            try {
                spriteCounter++;
                if (spriteCounter > 10) {

                    if (spritePosition == 2)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 2;

                    imagen = ImageIO.read(getClass().getResource("imagenes/vikingo" + spritePosition + ".png"));
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
                if (spriteCounter > 2) {

                    if (spritePosition == 2)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 2;

                    imagen = ImageIO.read(getClass().getResource("imagenes/fuego" + spritePosition + ".png"));
                    spriteCounter = 0;
                    if (isVisible == true) {
                        this.morir(delta);
                    }
                    
                }

            } catch (IOException e) {
                System.out.println(e);
            }
            return;
        }

        if(estado == estadoEnemigo.DESACTIVADO){
            this.desactivado();
            
        }

    }

    public void display(Graphics2D g2) {
        if(estado ==  estadoEnemigo.MUERTO){
            g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 40, 40, null, null);
            // g2.draw(cajaColision);
        }
        else{
            g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 45, 45, null, null);
            // g2.draw(cajaColision);
        }

    }

    public void restaurar(){
        isVisible=false;
        this.estado = estadoEnemigo.DESACTIVADO;
        this.timer=0;
        this.cajaColision.y=(int)this.positionY;
        this.cajaColision.x=(int)this.positionX;
        try {
            this.imagen = ImageIO.read(getClass().getResource("imagenes/vikingo1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
