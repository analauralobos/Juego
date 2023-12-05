package Juego;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public final class PrimerNivel extends Escenario{


    public PrimerNivel(){
        super("imagenes/nivel1.png");
        generarEnemigos();
        generarEsferas();
        this.jefeFinal=new Tone("imagenes/Tone1.png", 350, 5000-6400);
        
        
        checkpoint=new int[]{-4850,-4250,-2000,-700,0};
        
        
    }

    

    @Override
    public void generarEnemigos(){

        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 520, 5400-5450, true));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 120, 5380-5450, true));
        this.addEnemigo(new AvionVerdeMayor("imagenes/AvionVerdeMayor1.png", 515, 5260-5450, true));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 655, 5150-5450, false));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 400, 5050-5450, false));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 280, 5000-5450, true));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 110, 4900-5450, false));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 520, 4880-5450, true));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4800-5450, false,"LEFT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4795-5450, false,"LEFT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4790-5450, false,"LEFT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4785-5450, false,"LEFT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4780-5450, false,"LEFT"));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4700-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4695-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4690-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4685-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4680-5450, false,"RIGHT"));

        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 450, 4680-5450, true));

        this.addEnemigo(new BarcoEnemigoMenor("imagenes/BarcoEnemigoMenor1.png", 460, 4650-5450, false));

        this.addEnemigo(new BarcoEnemigoMenor("imagenes/BarcoEnemigoMenor1.png", 260, 4450-5450, true));
   
        
        try {
    
            raf = new RandomAccessFile("nivel.dat", "rw");
 
            for (Enemigo e: enemigos) {
                raf.writeDouble(e.positionX);
                raf.writeDouble(e.positionY);
            }
         
         
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void generarEsferas(){
        
        this.addEsfera(new BonusPowerUp("imagenes/Pow0.png", 630, 4650-5450));
       //this.addEsfera(new BonusPowerUp("imagenes/powerNegro0.png", 350, 4000-5450));
        //this.addEsfera(new BonusPowerUp("imagenes/powerNegro0.png", 350, 2350-5450));
        //this.addEsfera(new BonusPowerUp("imagenes/powerNegro0.png", 150, 1700-5450));
        //this.addEsfera(new BonusPowerUp("imagenes/powerNegro0.png", 400, 1100-5450));
      

        this.addEsfera(new BonusArma("imagenes/ametralladora0.png",500, 4650-5450));
        this.addEsfera(new BonusArma("imagenes/escopeta0.png", 360, 3350));
        try {
            raf2 = new RandomAccessFile("esferas.dat", "rw");
 
            for (ContenedorBonus e: esferas) {
                raf2.writeDouble(e.positionX);
                raf2.writeDouble(e.positionY);
            }
         
         
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }

    


    
}
