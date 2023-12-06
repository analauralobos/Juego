package Juego;

import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;


public class BonusPowerUp extends ContenedorBonus {

    private tipoPowerUp power;
    
    public enum tipoPowerUp{
       POW,
       AUTO,
       SUPERSHELL,
       ESTRELLANINJA,
    }


    public BonusPowerUp(String filename,int x,int y, tipoPowerUp tp) {
        super(filename);
        this.positionX=x;
        this.positionY=y;
        this.x_inicial=x;
        this.power= tp;
        this.cajaColision= new Rectangle(x,y,50,50);
        
    }


    @Override
    public void update(double delta) {

        if(this.esTomado){
            otorgarPowerUp();
            P38Avion.p38avion.activarPowerUp(true);
            isVisible=false;
            return;
        }


        this.moverse(delta);    
       
        if(golpeado){
            //Sonido.ESFERA.play(-5.0f);
            vecesGolpeado++;
            this.cambiar();
            golpeado=false;
            if(vecesGolpeado>3){
                this.cambiarMovimiento();
            }
        }

        if (power == tipoPowerUp.POW) {
            try {
                spriteCounter++;
                if (spriteCounter > 2) {

                    if (spritePosition < 4) {
                        spritePosition++;
                    } else {
                        spritePosition = 0;
                    }

                    imagen = ImageIO.read(getClass().getResource("imagenes/Pow" + spritePosition + ".png"));
                    spriteCounter = 0;
                }
            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }


        if(power==tipoPowerUp.AUTO){
            try {
                spriteCounter++;
                if (spriteCounter > 2) {
                    if (spritePosition > 1)
                        spritePosition = 0;
                    
                    if (spritePosition == 0)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 0;

                    imagen = ImageIO.read(getClass().getResource("imagenes/auto" + spritePosition + ".png"));
                    spriteCounter = 0;                
                }

            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        if(power==tipoPowerUp.SUPERSHELL){
            try {
                spriteCounter++;
                if (spriteCounter > 2) {

                    if (spritePosition == 0)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 0;

                    imagen = ImageIO.read(getClass().getResource("imagenes/supershell" + spritePosition + ".png"));
                    spriteCounter = 0;                
                }

            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        if(power==tipoPowerUp.ESTRELLANINJA){
            try {
                spriteCounter++;
                if (spriteCounter > 2) {

                    if (spritePosition == 0)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 0;

                    imagen = ImageIO.read(getClass().getResource("imagenes/estrellaninja" + spritePosition + ".png"));
                    spriteCounter = 0;                
                }

            } catch (IOException e) {
                System.out.println(e);
            }

        }

        
    }

    public void otorgarPowerUp(){
        switch(power){
            case POW:{
                //Sonido.BONUS.play(-5.0f);
                BatallaMidway1943.sumarScore(1000);
            }
            break;
            case AUTO:{
                //Sonido.POWERUP.play(-20.0f);
                P38Avion.p38avion.activarAutoDisparo();
            }
            break;
            case ESTRELLANINJA:{
                //Sonido.POWERUP.play(-20.0f);
                BatallaMidway1943.llenarTanque();
            }
            break;
            case SUPERSHELL:{
                //Sonido.POWERUP.play(-20.0f);
                P38Avion.p38avion.activarSuperShell(true);
                
            }
            break;
        }
    }

    @Override
    protected void cambiar(){
        switch(power){
            case POW:
            this.power=tipoPowerUp.AUTO;
            break;
            case AUTO:
            this.power=tipoPowerUp.SUPERSHELL;
            break;
            case SUPERSHELL:
            this.power=tipoPowerUp.ESTRELLANINJA;
            break;
            case ESTRELLANINJA:
            this.power=tipoPowerUp.POW;
            break;
            
        }
    }

    @Override
    public void restaurar(){
        this.power=tipoPowerUp.POW;
        this.movimiento=tipoMovimiento.RECTO;
        this.esTomado=false;
        this.golpeado=false;
        this.isVisible=false;
        this.activado=false;
        this.cajaColision.x=(int)this.positionX;
        this.cajaColision.y=(int)this.positionY;
        
    }

    
    
}

