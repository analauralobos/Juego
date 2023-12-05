package Juego;

import java.awt.*;

public abstract class Bonus extends ObjetoGrafico{

    protected int dureza;
    protected boolean esTomado;
    protected Rectangle cajaColision;

    public Bonus(String filename){
        super(filename);
        
    }

    @Override
    public abstract void display(Graphics2D g2);
    public abstract void update(double delta);
    protected abstract void restaurar();
    

    protected void updateCajaColision(){
        this.cajaColision.x=(int)this.positionX;
        this.cajaColision.y=(int)this.positionY;
    }

    public boolean getVisible(){
        return isVisible;
    }

    public void setVisible(boolean set){
        isVisible=set;
    }

    public void setTomado(boolean set){
        this.esTomado=set;
    }

    

    
    
}
