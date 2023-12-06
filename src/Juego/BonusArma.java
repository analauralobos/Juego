package Juego;

import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BonusArma extends ContenedorBonus{

    public enum tipoArma{
        P38MUNICION,
        LASER,
        AMETRALLADORA,
        REFUERZOS,
        ESCOPETA,
        BOMBA,
    }

    tipoArma bonusArma;

    public BonusArma(String filename,int x,int y, tipoArma tp) {
        super(filename);
        this.positionX=x;
        this.positionY=y;
        this.x_inicial=x;
        this.bonusArma=tp;
        this.cajaColision= new Rectangle(x,y,50,50);
       
    }

    @Override
    public void update(double delta) {
        int cadencia=1;

        if(this.esTomado){
            otorgarArma();
            isVisible=false;
            return;
        }

        this.moverse(delta);    
       
        
        if(golpeado){
            //Sonido.P38MUNICION.play(-5.0f);
            vecesGolpeado++;
            this.cambiar();
            golpeado=false;
            if(vecesGolpeado>3){
                this.cambiarMovimiento();
            }
        }

        if(bonusArma==tipoArma.P38MUNICION){
            try {
                spriteCounter++;
                System.out.println("spritepostion: " + spritePosition);
                if (spriteCounter > 2) {
                    imagen = ImageIO.read(getClass().getResource("imagenes/P38Municion" + spritePosition + ".png"));
                    spriteCounter = 0;
                    spritePosition++;
                    if(spritePosition>2){
                        spritePosition=0;
                    } 
               
                }

            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        if(bonusArma==tipoArma.LASER){
            if("LASER".equals(P38Avion.p38avion.arma.municionActual().toString())){
                cadencia=2;
            }
            try {
                spriteCounter++;
                if (spriteCounter > 2) {
                    imagen = ImageIO.read(getClass().getResource("imagenes/laser"+spritePosition+".png"));
                    spriteCounter = 0;
                    spritePosition++;
                    if(spritePosition>1){
                        spritePosition=0;
                    } 
   
                }              
            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        if(bonusArma==tipoArma.AMETRALLADORA){
            try {
                spriteCounter++;
                if (spriteCounter > 2) {
                    imagen = ImageIO.read(getClass().getResource("imagenes/ametralladora"+spritePosition+".png"));
                    spriteCounter = 0;
                    spritePosition++;
                    if(spritePosition>1){
                        spritePosition=0;
                    } 
   
                }              
            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        if(bonusArma==tipoArma.REFUERZOS){
            try {
                spriteCounter++;
                if (spriteCounter > 2) {
                    imagen = ImageIO.read(getClass().getResource("imagenes/refuerzos"+spritePosition+".png"));
                    spriteCounter = 0;
                    spritePosition++;
                    if(spritePosition>1){
                        spritePosition=0;
                    } 
   
                }              
            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        if(bonusArma==tipoArma.ESCOPETA){
            if("ESCOPETA".equals(P38Avion.p38avion.arma.municionActual().toString())){
                cadencia=2;
            }
            try {
                spriteCounter++;
                if (spriteCounter > 2) {                    
                    imagen = ImageIO.read(getClass().getResource("imagenes/escopeta"+spritePosition+".png"));
                    spriteCounter = 0;
                    spritePosition++;
                    if(spritePosition>1){
                        spritePosition=0;
                    } 
   
                }              
            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        if(bonusArma==tipoArma.BOMBA){
            try {
                spriteCounter++;
                if (spriteCounter > 2) {
                    imagen = ImageIO.read(getClass().getResource("imagenes/municionEnemigobomba"+spritePosition+".png"));
                    spriteCounter = 0;
                    spritePosition++;
                    if(spritePosition>1){
                        spritePosition=0;
                    } 
   
                }              
            } catch (IOException e) {
                System.out.println(e);
            }
            return;

        }

        

          
    }

    @Override
    protected void cambiar() {
        this.spritePosition=0;

        switch(bonusArma){
            case P38MUNICION:{
                this.bonusArma=tipoArma.LASER;             
            }
            break;
            case LASER:{
                this.bonusArma=tipoArma.AMETRALLADORA; 
            }
            break;
            case AMETRALLADORA:{
                this.bonusArma=tipoArma.REFUERZOS;
            }
            break;
            case REFUERZOS:{
                this.bonusArma=tipoArma.ESCOPETA;
            }
            break;
            case ESCOPETA:{
                this.bonusArma=tipoArma.BOMBA;
            }
            case BOMBA:{
                this.bonusArma=tipoArma.LASER;
            }
            break;
        }
        
        
    }

    protected void restaurar(){
        this.bonusArma=tipoArma.P38MUNICION;
        this.movimiento=tipoMovimiento.RECTO;
        this.esTomado=false;
        this.golpeado=false;
        this.isVisible=false;
        this.activado=false;
        this.cajaColision.x=(int)this.positionX;
        this.cajaColision.y=(int)this.positionY;
        
    }

    private void otorgarArma(){
        switch(bonusArma){
            case P38MUNICION:{
                //Sonido.BONUS.play(-5.0f);
                BatallaMidway1943.sumarScore(1000);
            }
            break;
            case LASER:{
                //Sonido.BONUS.play(-5.0f);
                P38Avion.p38avion.arma.cambiarMunicion("LASER");
            }
            break;
            case AMETRALLADORA:{
                //Sonido.BONUS.play(-5.0f);
                P38Avion.p38avion.arma.cambiarMunicion("AMETRALLADORA");
            }
            break;
            case REFUERZOS:{
                //Sonido.BONUS.play(-5.0f);
                P38Avion.p38avion.arma.cambiarMunicion("REFUERZOS");
            }
            break;
            case ESCOPETA:{
                //Sonido.BONUS.play(-5.0f);
                P38Avion.p38avion.arma.cambiarMunicion("ESCOPETA"); 
            }
            break;
            case BOMBA:{
                //Sonido.BONUS.play(-5.0f);
                P38Avion.p38avion.arma.cambiarMunicion("BOMBA"); 
            }
            break;
        }

    }
    
}
