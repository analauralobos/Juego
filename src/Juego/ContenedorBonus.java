package Juego;

import java.awt.*;

public abstract class ContenedorBonus extends Bonus{

    protected boolean golpeado;
    protected boolean activado=false;
    protected double variableMovimiento=0;
    protected int x_inicial;
    protected int vecesGolpeado=0;
    protected int spriteCounter=0;
    protected int spritePosition=0;
    
    
    protected enum tipoMovimiento{
        RECTO,
        SENO,
    }

    protected tipoMovimiento movimiento;

    public ContenedorBonus(String filename) {
        super(filename);
        this.movimiento=tipoMovimiento.RECTO;
        this.esTomado=false;
        this.golpeado=false;
        this.isVisible=false;
    }
    protected abstract void cambiar();
    @Override
    public abstract void update(double delta);

    @Override
    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, 50, 50, null, null);
        // g2.draw(cajaColision);
    }

  
    protected void moverse(double delta){
        switch(movimiento){
            case RECTO:{
                this.positionY+=0.7;
                this.updateCajaColision();
            }
            
            break;
            case SENO:{
                this.positionY+=0.7;
                variableMovimiento+=delta;
                positionX = x_inicial +(int) (Math.sin(variableMovimiento)*40); // Se mueve de a 40
                this.updateCajaColision(); 
            }
            break;
        }
    }

    protected void cambiarMovimiento(){
       this.movimiento=tipoMovimiento.SENO;

    }


    
}
