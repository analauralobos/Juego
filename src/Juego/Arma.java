package Juego;

public class Arma {

    private int cadencia = 1;
    private int x;
    private int y;

    public enum tipoMunicion {
        P38MUNICION,
        LASER,
        AMETRALLADORA,
        ESCOPETA,
        ENEMIGA,
        BOMBA
    }

    private tipoMunicion municionActual;
    Sonido musica= new Sonido();
    public Arma(tipoMunicion municionActual, int x, int y) {
        this.municionActual = municionActual;
        this.x = x;
        this.y = y;
    }

    public void cambiarMunicion(String municion){
        if(municion == null ? this.municionActual.toString() == null : municion.equals(this.municionActual.toString())){
            cadencia=2;
        }
        else{
            cadencia=1;
        }

        switch(municion){
            case "P38MUNICION":{
                this.municionActual=tipoMunicion.P38MUNICION;
            }
            
            break;
            case "LASER":{
                this.municionActual=tipoMunicion.LASER;
            }
            break;
            case "AMETRALLADORA":{
                this.municionActual=tipoMunicion.AMETRALLADORA;
            }
            break;
            case "ESCOPETA":{
                this.municionActual=tipoMunicion.ESCOPETA;
            }
            break;
        }
    }

    public void aumentarCadencia() {
       cadencia=2;
    }

    public void resetCadencia() {
        cadencia = 1;
    }

    public int getCadencia() {
        return cadencia;
    }

    public tipoMunicion municionActual(){
        return this.municionActual;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void disparo() {
        switch(cadencia){
            case 1:{
                if (municionActual == tipoMunicion.ENEMIGA){
                    Escenario.get_nivel().municionEnemiga.add(new MunicionEnemiga("imagenes/municionEnemigo.png", this.x, this.y));
                }
                if (municionActual == tipoMunicion.BOMBA){
                    Escenario.get_nivel().municionEnemiga.add(new Bomba("imagenes/municionEnemigobomba0.png", this.x, this.y));
                }
                if(Escenario.get_nivel().municionHeroe.size()<2){

                    if (municionActual == tipoMunicion.P38MUNICION) {
                        Escenario.get_nivel().municionHeroe.add(new P38Municion("imagenes/P38Municion0.png", this.x, this.y));
                        try {
                            musica.setFile(3);
                            musica.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    if(municionActual == tipoMunicion.ESCOPETA){
                        Escenario.get_nivel().municionHeroe.add(new Escopeta("imagenes/escopeta0.png", this.x, this.y));
                        try {
                           musica.setFile(3);
                            musica.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    if (municionActual == tipoMunicion.LASER) {
                        Escenario.get_nivel().municionHeroe.add(new Laser("imagenes/laser0.png", this.x, this.y));
                        try {
                            musica.setFile(3);
                            musica.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    if (municionActual == tipoMunicion.AMETRALLADORA) {
                        Escenario.get_nivel().municionHeroe.add(new Ametralladora("imagenes/ametralladora0.png", this.x, this.y));
                        try {
                           musica.setFile(3);
                            musica.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }
            }

        }break;
            case 2:{
                if(Escenario.get_nivel().municionHeroe.size()<3){
                    if (municionActual == tipoMunicion.LASER) {
                        Escenario.get_nivel().municionHeroe.add(new Laser("imagenes/laser0.png", this.x, this.y));
                        try {
                           musica.setFile(3);
                            musica.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    if (municionActual == tipoMunicion.AMETRALLADORA) {
                        Escenario.get_nivel().municionHeroe.add(new Ametralladora("imagenes/ametralladora0.png", this.x, this.y));
                        try {
                           musica.setFile(3);
                            musica.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                  
                    
                }
                if(Escenario.get_nivel().municionHeroe.size()<4){

                    if (municionActual == tipoMunicion.P38MUNICION) {
                        Escenario.get_nivel().municionHeroe.add(new P38Municion("imagenes/P38Municion.png", this.x-10, this.y));
                        Escenario.get_nivel().municionHeroe.add(new P38Municion("imagenes/P38Municion.png", this.x+10, this.y));
                        try {
                           musica.setFile(3);
                            musica.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }

                    if (municionActual == tipoMunicion.ESCOPETA) {
                        Escenario.get_nivel().municionHeroe.add(new Escopeta("imagenes/escopeta0.png", this.x-10, this.y));
                        Escenario.get_nivel().municionHeroe.add(new Escopeta("imagenes/escopeta0.png", this.x+10, this.y));
                        try {
                           musica.setFile(3);
                            musica.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }
            }
            
            

            }break;
        

        }
       
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
