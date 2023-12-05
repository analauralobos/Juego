package Juego;


public abstract class Enemigo extends Personaje{

    protected boolean isHited=false;
    protected estadoEnemigo estado;
    protected double timerDisparo=0;
    protected int puntosAlMorir;
    protected int timer=0;

    public enum estadoEnemigo{
        VIVO,
        MUERTO,
        CONGELADO,
        DESACTIVADO,
    }
    

    public Enemigo(String filename){
        super(filename);
        this.estado = estadoEnemigo.DESACTIVADO;
        isVisible=false;
    }

    public abstract void restaurar();

    public void desactivado(){
        this.positionY++;
        this.cajaColision.y++;
    }


    public void hitted(){
        this.isHited=true;
    }

    public void visible(){
        this.isVisible=false;
    }

    public void morir(double delta){

        if(timer==0){
            try {
//                FXPlayer.FUEGO.play(-5.0f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (timer > 3) {
        timer=0;
        this.isVisible=false;
        BatallaMidway1943.sumarScore(this.puntosAlMorir);
        }
        timer++;
        this.update(delta);
        

    }

    public boolean activado(){
        if(estado == estadoEnemigo.DESACTIVADO){
            return false;
        }
        
        return true;
    }
    
    
    

    
}
