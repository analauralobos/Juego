package Juego;

import java.awt.*; //imagenes
import java.io.IOException;
import javax.imageio.*; //imagenes

public class P38Avion extends Personaje {

    

    private int velocidad;
    private estados estadoActual;
    private boolean animacion=false;
    private static int powerVelocidad=0;
    private boolean powerUpActivo;
    private boolean superShellActivo;
    private boolean autoDisparo;
    private static double energia;
    
    public static P38Avion p38avion;

    public enum estados { 
        VIVO,
        MURIENDO,
    }

    public P38Avion(String filename) {
        super(filename);
        estadoActual = estados.VIVO;
        this.velocidad = 1000;
        isVisible=true;
        cajaColision = new Rectangle((int) this.positionX, (int) this.positionY, 45, 45); // Tamaño total de la imagen
        puedeDisparar=true;
        p38avion=this;
        powerUpActivo = false;
        superShellActivo = false;
        energia = 100;
        arma=new Arma(Arma.tipoMunicion.P38MUNICION, 0, 0);
    }

    @Override
    public void display(Graphics2D g2){
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 45, 45, null, null);           
    }
    
    public double getEnergia(){
        return P38Avion.energia;
    }

    public static void setEnergia(double energia) {
        P38Avion.energia = energia;
    }
  
    
    public boolean getPowerUpActivo(){
        return this.powerUpActivo;
    }
    public void activarPowerUp(boolean estado){
        this.powerUpActivo = estado;
    }
    public void activarSuperShell(boolean estado){
        this.superShellActivo = estado;
    }
    public boolean getSuperShellActivo(){
        return this.superShellActivo;
    }
    
    
    public void update(double delta) {
        
        if (animacion){
            if(this.positionY<65){
                this.animacion=false;
                return;
            }
            if((int)this.positionX==400){
                this.positionY--;
            }
            else if(this.positionX<=400){
                this.positionX+=1;
            }else if(this.positionX>=400){
                this.positionX-=1;
            }
            
            try {
                spriteCounter++;
                if (spriteCounter > 10) { 

                    if (spritePosition == 0)
                        spritePosition = 1;
                    else if (spritePosition == 1)
                        spritePosition = 0;

                    imagen = ImageIO.read(getClass().getResource("imagenes/"+BatallaMidway1943.juego.PERSONAJE+ spritePosition + ".png"));
                    spriteCounter = 0; 
                }

            } catch (IOException e) {
                System.out.println(e);
            }
        }
        
        else
        
        {
            
            updateCajaColision(); 
            updateArma((int)this.positionX+15, (int)this.positionY);
            Escenario.get_nivel().colisionBonus(this.cajaColision);
            

            
            if(estadoActual != estados.MURIENDO && estadoActual == estados.VIVO){
                if(Escenario.get_nivel().colisionMunicionEnemiga(this)){
                    this.cambiar(estados.MURIENDO);
                }
            }
            if(this.cajaColision.y+30>500 && estadoActual != estados.MURIENDO){
                this.cambiar(estados.MURIENDO);
            }
    
            
            if (estadoActual == estados.VIVO) {

                try {
                    spriteCounter++;
                    if (spriteCounter > 10) { 
    
                        if (spritePosition == 0)
                            spritePosition = 1;
                        else if (spritePosition == 1)
                            spritePosition = 0;
    
                        imagen = ImageIO.read(getClass().getResource("imagenes/"+BatallaMidway1943.juego.PERSONAJE+ spritePosition + ".png"));
                        spriteCounter = 0; 
                    }
    
                } catch (IOException e) {
                    System.out.println(e);
                }
    
                if(Escenario.get_nivel().colisionEnemigo(this.cajaColision)){
                    this.cambiar(estados.MURIENDO);
                    spritePosition=4;
                    spriteCounter=0;
                }
    
                return;
            }

            if (estadoActual == estados.MURIENDO) {
                try {
                    spriteCounter++;

                    
                    if (spritePosition < 7) {
                        if (spriteCounter > 7) {
                            spritePosition++;
                            
                            String nombreImagen = "fuego" + spritePosition + ".png";
                            imagen = ImageIO.read(getClass().getResource("imagenes/" + nombreImagen));
                            spriteCounter = 0;
                        }
                    }
                } catch (IOException e) {
                    
                    System.out.println(e);
                }
            }

            
        }
    }

   

    public void cambiar(estados nuevo) {

        switch (nuevo) {
            case VIVO:{
                puedeDisparar=true;
                spritePosition = 0;
                this.velocidad=1000;
            }
                break;
            case MURIENDO:{
                spritePosition= 1;
            }
                break;
        }

        this.estadoActual = nuevo;

    }

    public void down(double delta) {

        Escenario nivel = Escenario.get_nivel();
        Rectangle SiguientePosicion = new Rectangle(this.cajaColision.x, (int) (this.positionY + (velocidad + powerVelocidad) * delta),this.cajaColision.width, this.cajaColision.height);
        if(cajaColision.y+cajaColision.getHeight()<500){
            positionY = (this.positionY + (velocidad + powerVelocidad) * delta);
        }
        

    }

    public void up(double delta) {
        Escenario nivel = Escenario.get_nivel();
        Rectangle SiguientePosicion = new Rectangle(this.cajaColision.x, (int) (this.positionY - (velocidad + powerVelocidad) * delta),this.cajaColision.width, this.cajaColision.height);
       
        if(cajaColision.y>80){
            positionY = (this.positionY - (velocidad + powerVelocidad) * delta);
        }
        

    }

    public void left(double delta) {
        Escenario nivel = Escenario.get_nivel();
        Rectangle SiguientePosicion = new Rectangle((int) (this.positionX - (velocidad + powerVelocidad) * delta),this.cajaColision.y,this.cajaColision.width,this.cajaColision.height);
    
        if(this.cajaColision.x>0){
            positionX = (this.positionX - (velocidad + powerVelocidad) * delta);
        }
       

    }

    public void right(double delta){
        Escenario nivel = Escenario.get_nivel();
        Rectangle SiguientePosicion = new Rectangle((int) (this.positionX + (velocidad + powerVelocidad) * delta),this.cajaColision.y,this.cajaColision.width,this.cajaColision.height);
        
        if(this.cajaColision.x+this.getWidth()<780){
            positionX = (this.positionX + (velocidad + powerVelocidad) * delta);
            
        }

    }

    public estados getEstado(){
        return this.estadoActual;
    }

    public static void velocidad(){
        if(powerVelocidad<90){
            powerVelocidad+=20;
        }
    }
    
    public boolean animacion(){
        return this.animacion;
    }

    public void setAnimacion(){
        this.animacion=true;
    }

    public void restaurar(){
        powerVelocidad=0;
        this.cambiar(estados.VIVO);
    }

    public void finalizaPower(){
        
    }
    
    public void activarAutoDisparo() {
        autoDisparo = true;
    }

    public void desactivarAutoDisparo() {
        autoDisparo = false;
    }
    
     public void disparoAutomatico() {
    if (autoDisparo) {
        arma.disparo();
    }
}

    
 

}