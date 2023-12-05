package Juego;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.awt.*;


public class SegundoNivel extends Escenario{


    public SegundoNivel(){
        super("imagenes/nivel2.png");
        generarEnemigos();
        generarEsferas();
        this.jefeFinal=new Yamato("imagenes/Yamato1.png", 350, 100-5450);
        
        
        checkpoint=new int[]{-4850,-4250,-2000,-700,0};
        
        
    }   

    public void generarEnemigos(){
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 520, 5400-5450, false));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 120, 5380-5450, false));
        this.addEnemigo(new AvionVerdeMayor("imagenes/AvionVerdeMayor1.png", 515, 5260-5450, true));
        this.addEnemigo(new AvionVerdeMayor("imagenes/AvionVerdeMayor1.png", 400, 5200-5450, true));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 655, 5150-5450, false));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 400, 5050-5450, false));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 280, 5000-5450, true));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 110, 4900-5450, false));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 520, 4880-5450, true));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4800-5450, false,"LEFT"));
        //this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4795-5450, false,"LEFT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4790-5450, false,"LEFT"));
        //this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4785-5450, false,"LEFT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4780-5450, false,"LEFT"));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4700-5450, false,"RIGHT"));
        //this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4695-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4690-5450, false,"RIGHT"));
        //this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4685-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4680-5450, false,"RIGHT"));

        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 450, 4680-5450, true));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4650-5450, false,"RIGHT"));
        //this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4645-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4640-5450, false,"RIGHT"));
        //this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4635-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4630-5450, false,"RIGHT"));

        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 260, 4450-5450, true));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 500, 4400-5450, true));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 380, 4255-5450, true));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 300, 4200-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 300, 4195-5450, false,"RIGHT"));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 500, 4200-5450, false,"LEFT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 500, 4195-5450, false,"LEFT"));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4090-5450, false,"LEFT"));
        //this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4085-5450, false,"LEFT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4080-5450, false,"LEFT"));
        //this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4075-5450, false,"LEFT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 4070-5450, false,"LEFT"));

        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 520, 4000-5450, true));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 150, 3950-5450, true));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 3840-5450, false,"LEFT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 3840-5450, false,"RIGHT"));
/*
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 3700-5450, false,"LEFT"));
       // this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 3650-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 3600-5450, false,"LEFT"));
       // this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 3580-5450, false,"RIGHT"));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 500, 3540-5450, false,"LEFT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 300, 3540-5450, false,"RIGHT"));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 500, 3440-5450, false,"LEFT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 300, 3440-5450, false,"RIGHT"));
        
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 235, 3340-5450, true));
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 435, 3335-5450, true));
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 235, 3240-5450, true));
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 435, 3235-5450, true));
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 235, 3140-5450, false));
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 435, 3135-5450, true));
        
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 530, 3030-5450, true));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 115, 2850-5450, true));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 2770-5450, false,"LEFT"));
        //this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 2765-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 2760-5450, false,"LEFT"));
        //this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 2755-5450, false,"RIGHT"));

        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 625, 2525-5450, true));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 430, 2470-5450, true));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 2315-5450, false,"RIGHT"));
        //this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 2310-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 2305-5450, false,"RIGHT"));
        //this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 2300-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 2295-5450, false,"RIGHT"));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 2265-5450, false,"RIGHT"));
       // this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 2260-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 2255-5450, false,"RIGHT"));
       // this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 2250-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 2245-5450, false,"RIGHT"));

        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 220, 2065-5450, true));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 135, 2000-5450, true));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 500, 2015-5450, false,"LEFT"));
     //   this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 500, 2010-5450, false,"LEFT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 500, 2005-5450, false,"LEFT"));
      //  this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 500, 2000-5450, false,"LEFT"));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 300, 1905-5450, false,"RIGHT"));
       // this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 300, 1900-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 300, 1895-5450, false,"RIGHT"));
       // this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 300, 1890-5450, false,"RIGHT"));

        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 525, 1800-5450, true));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 300, 1750-5450, true));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 160, 1600-5450, true));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 650, 1645-5450, true));

        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 575, 1470-5450, true));
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 575, 1400-5450, true));
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 115, 1410-5450, true));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 300, 1275-5450, false,"RIGHT"));
        //this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 300, 1230-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 500, 1280-5450, false,"LEFT"));
        //this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 500, 1240-5450, false,"LEFT"));

        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 250, 1000-5450, true));
        this.addEnemigo(new AvionVerdeMenor("imagenes/AvionVerdeMenor1.png", 120, 900-5450, true));

        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 800-5450, false,"RIGHT"));
        this.addEnemigo(new AvionRojo("imagenes/AvionRojo0.png", 460, 820-5450, false,"LEFT"));
        
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 300, 700-5450, true));
        this.addEnemigo(new Vikingo("imagenes/vikingo1.png", 400, 700-5450, true));
*/

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

    public void generarEsferas(){
        /*
        //Esferas Power
        this.addEsfera(new EsferaPowerUp("imagenes/powerNegro0.png", 630, 4650-5450));
        this.addEsfera(new EsferaPowerUp("imagenes/powerNegro0.png", 350, 4000-5450));
        this.addEsfera(new EsferaPowerUp("imagenes/powerNegro0.png", 350, 2350-5450));
        this.addEsfera(new EsferaPowerUp("imagenes/powerNegro0.png", 150, 1700-5450));
        this.addEsfera(new EsferaPowerUp("imagenes/powerNegro0.png", 400, 1100-5450));
        //Esferas Arma
        
        try {
            raf2 = new RandomAccessFile("esferas.dat", "rw");
 
            for (Esfera e: esferas) {
                raf2.writeDouble(e.positionX);
                raf2.writeDouble(e.positionY);
            }
         
         
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
*/

    }

    


    
}
