package Juego;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.LinkedList;
import java.awt.Graphics2D;


public class BatallaMidway1943 extends JGame {
    private final ObjetoGrafico menuprincipal = new ObjetoGrafico("imagenes/menuprincipal.png");
    private P38Avion personajePrincipal;
    private Escenario nivel;
    private Rectangle rectangulo;
    private Rectangle bonus;
    private Ranking rank;
    private Font font;
    private gameStatus estadoJuego;
    private int up, down, left, right;
    private int hiScore = 0;
    private boolean musicaGeneral;
    private double timer = 0;
    private double timerBonus = 0;
    private double timerPower = 0;
    private static int score = 0;
    private static int numeroNivel = 1;
    //private static double energia = 100;
    private int aux = 0;
    private double tiempoTranscurridoEnergia = 0;
    private static final double TIEMPO_PERDIDA_ENERGIA = 5.0;

    
    public static boolean esjefe = false;
    public static BatallaMidway1943 juego;
    public final String PERSONAJE = appProperties.getProperty("personaje");
    public final String MUSICAFONDO = appProperties.getProperty("pista") + ".wav";
    

    

    public enum gameStatus {
        MENU_PRINCIPAL,
        LOOP,
        PAUSA,
        CARGANDO,
        GAME_OVER,
        BONUS,
        POWERUP,
        SIGUIENTE,
        WIN,
    }

    public BatallaMidway1943() {
        super("Juego", 800, 600);
        juego = this;
        System.out.println("Musicafondo" + MUSICAFONDO);
    }

    public static void main(String args[]) {
        BatallaMidway1943 game = new BatallaMidway1943();
        game.run(1.0 / 60.0);
        System.exit(0);
    }

    @Override
    public void gameStartup() {

        /*switch(appProperties.getProperty("sonidoGeneral")){
            case "false":
            FXPlayer.volume = FXPlayer.Volume.MUTE;
            musicaGeneral=false;
            break;
            case "true":
            FXPlayer.volume = FXPlayer.Volume.LOW;
            musicaGeneral=true;
            break;
        }*/
        //FXPlayer.init();

        this.personajePrincipal = new P38Avion("imagenes/" + PERSONAJE + "0.png");
        menuprincipal.setPosition(100, 5);

        rank = new Ranking(); 
        this.hiScore = rank.getTop();
        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("letras/ARCADEPI.ttf"))
                    .deriveFont(20f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        } catch (IOException e) {
            System.out.println(e);
        } catch (FontFormatException e) {
            System.out.println(e);
        }

        estadoJuego = gameStatus.MENU_PRINCIPAL;
        rectangulo = new Rectangle(0, 500, 850, 500);
        bonus = new Rectangle(650, 95, 67, 34); 
    }
    
    //Color de letras
    private void drawStyledString(Graphics2D g2, String str, int x, int y, boolean center) {
        int offset = 0;
        if (center) {
            FontMetrics metrics = g2.getFontMetrics(font);
            offset = -metrics.stringWidth(str) / 2 + 4;
        }
        g2.setColor(Color.BLACK);
        g2.drawString(str, x + offset, y + 1);
        g2.drawString(str, x - 1 + offset, y + 1);
        g2.setColor(Color.white);
        g2.drawString(str, x + offset, y);
    }

    @Override
    public void gameDraw(Graphics2D g) {

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        if (estadoJuego == gameStatus.MENU_PRINCIPAL) {
            g.setFont(font);
            menuprincipal.display(g);
            g.setColor(Color.BLACK);
            drawStyledString(g, "THE BATTLE OF MIDWAY", 400, 250, true);
            //drawStyledString(g, "GAME OVER", 400, 300, true);
            if (timer % 1 < 0.5) {
                drawStyledString(g, "PLEASE INSERT COIN", 405, 350, true);
            }
            drawStyledString(g, "© 2023 Ana-Eliana-Magali", 400, 450, true);
            drawStyledString(g, "ALL RIGHTS RESERVED", 400, 500, true);
            return;
        }

        if (estadoJuego == gameStatus.CARGANDO) {

            //g.setFont(font);
            //drawStyledString(g, "STAGE " + Integer.toString(numeroNivel), 400, 200, true);
            //g.setColor(Color.BLACK);
            //g.fill(rectangulo);
            //updateHud(g);

            return;
        }

        if (estadoJuego == gameStatus.GAME_OVER) {

            g.setFont(font);
            menuprincipal.display(g);
            g.setColor(Color.BLACK);
            drawStyledString(g, "THE BATTLE OF MIDWAY", 400, 250, true);
            drawStyledString(g, "GAME OVER", 400, 300, true);
            if (timer % 1 < 0.5) {
                drawStyledString(g, "PLEASE INSERT COIN", 405, 350, true);
            }
            drawStyledString(g, "© 2023 Ana-Eliana-Magali", 400, 450, true);
            drawStyledString(g, "ALL RIGHTS RESERVED", 400, 500, true);
            return;
        }

        if (estadoJuego == gameStatus.LOOP || estadoJuego == gameStatus.PAUSA || estadoJuego == gameStatus.SIGUIENTE) {
            
            nivel.display(g);
            personajePrincipal.display(g);
            actualizarRect(g);
            return;
        }

        if (estadoJuego == gameStatus.BONUS || estadoJuego == gameStatus.POWERUP) {
            
            nivel.display(g);
            personajePrincipal.display(g);
            //g.setColor(Color.BLACK);
            //g.draw(rectangulo);
            g.draw(bonus);
            g.fill(bonus);
            //g.fill(rectangulo);
            updateBonus(g);
            actualizarRect(g);
            return;
        }

        if (estadoJuego == gameStatus.WIN) {

            g.setFont(font);
            drawStyledString(g, "WIN", 400, 200, true);
            g.setColor(Color.BLACK);
            g.draw(rectangulo);
            g.fill(rectangulo);
            actualizarRect(g);
            return;
        }

    }

    private void updateBonus(Graphics2D g) {

        g.setFont(font.deriveFont(20f));

        if (estadoJuego == gameStatus.BONUS) {
            
            drawStyledString(g, Integer.toString((int) timerBonus), 671, 120, false);
        } else if (estadoJuego == gameStatus.POWERUP) {
            drawStyledString(g, Integer.toString((int) timerPower), 671, 120, false);
        }

    }

    private void actualizarRect(Graphics2D g) {

        g.setFont(font.deriveFont(20f));
        drawStyledString(g, "1PLAYER", 100, 70, false);
        drawStyledString(g, Integer.toString(score), 100, 90, false);
        drawStyledString(g, "HI-SCORE", 255, 70, false);
        drawStyledString(g, Integer.toString(hiScore), 255, 90, false);
        drawStyledString(g, "REST", 100, 530, false);
        //drawStyledString(g, Integer.toString(energia), 100, 570, false);
        //drawHealthBar(g,100,570,200,10,100,energia);
        drawHealthBar(g, 100, 570, 200, 10, 100, personajePrincipal.getEnergia());

        drawStyledString(g, "STAGE", 600, 530, false);
        drawStyledString(g, Integer.toString(numeroNivel), 670, 570, false);
        //g.setColor(Color.BLACK);

    }
    /**
     * **************
     */
    public void drawHealthBar(Graphics g, int x, int y, int width, int height, int maxHealth, double currentHealth) {
        // Calcular el porcentaje de vida actual
        double healthPercentage = (double) currentHealth / maxHealth;

        // Calcular el ancho de la barra de vida según el porcentaje
        int barWidth = (int) (width * healthPercentage);

        // Determinar el color según el porcentaje de energía
        Color barColor;
        if (healthPercentage <= 0.3) {
            // Menor o igual al 30%, color rojo
            barColor = Color.RED;
        } else if (healthPercentage <= 0.6) {
            // Entre el 40% y el 60%, color naranja
            barColor = new Color(255, 102, 0);
        } else {
            // Mayor al 60%, color amarillo
            barColor = Color.YELLOW;
        }

        // Dibujar el fondo de la barra de vida en color gris
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);

        // Dibujar la barra de vida actual en el color correspondiente
        g.setColor(barColor);
        g.fillRect(x, y, barWidth, height);

        // Dibujar el contorno de la barra de vida en color negro
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }


    /**
     * ***************************
     */

    @Override
    public void gameShutdown() {
    }

    @Override
    public void gameUpdate(double delta) {
        Keyboard keyboard = this.getKeyboard();
        LinkedList<KeyEvent> keyEvents = keyboard.getEvents();

        if (score >= hiScore) {
            hiScore = score;
        }

        if (estadoJuego == gameStatus.MENU_PRINCIPAL) {
            System.out.println("Menu Principal");
            timer += delta;
            for (KeyEvent event : keyEvents) {
                if ((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                    stop();
                }

                if (event.getID() == KeyEvent.KEY_PRESSED) {
                    /*borrado ana laura*/
 /*try {
                        FXPlayer.INTRO.play(-20.0f);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ;*/
                    System.out.println("Cambio a Cargando");
                    timer = 0;
                    estadoJuego = gameStatus.CARGANDO;
                    numeroNivel = 1;
                    break;
                }
            }
        }

        if (estadoJuego == gameStatus.SIGUIENTE) {
            System.out.println("Siguiente");
            if (personajePrincipal.animacion()) {
                personajePrincipal.update(delta);
            } else {
                if (numeroNivel == 2) {
                    estadoJuego = gameStatus.WIN;
                    return;
                }
                Escenario.clear();
                numeroNivel++;
                estadoJuego = gameStatus.CARGANDO;
                timer = 0;
            }
        }

        if (estadoJuego == gameStatus.CARGANDO) {
            System.out.println("Cargando");
            timer += delta;
            nivel = Escenario.get_nivel();
            personajePrincipal.setPosition(382, 373);
            if (timer > 0) {
                System.out.println("Cambio a Loop");
                estadoJuego = gameStatus.LOOP;
                try {
                    // FXPlayer.STAGE1.loop(-20.0f);
                    //reproducirMusica();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ;
                timer = 0;
            }

        }

        if (estadoJuego == gameStatus.WIN) {
            timer += delta;
            if (timer > 11) {
                estadoJuego = gameStatus.MENU_PRINCIPAL;
                P38Avion.setEnergia(100);
                
                Escenario.clear();
                timer = 0;
                rank.actualizar(score);
                score = 0;
                rank = new Ranking();
                hiScore = rank.getTop();
            }

        }

        if (estadoJuego == gameStatus.GAME_OVER) {
            timer += delta;
            if (timer > 11) {
                estadoJuego = gameStatus.MENU_PRINCIPAL;
                P38Avion.setEnergia(100);                
                timer = 0;
            }

        }

        if (estadoJuego == gameStatus.PAUSA) {
            System.out.println("Pausa");
            for (KeyEvent event : keyEvents) {
                if ((event.getID() == KeyEvent.KEY_RELEASED) && (event.getKeyCode() == KeyEvent.VK_P) /*&& (KeyEvent.getKeyText(event.getKeyCode()).toUpperCase().equals(appProperties.getProperty("pausa")))*/) {
                    this.estadoJuego = gameStatus.LOOP;
                    // FXPlayer.STAGE1.loop(-20.0f);
                    reproducirMusica();
                    //FXPlayer.PAUSA.play(-5.0f);
                    keyEvents.clear();
                }
            }

        }

        if (estadoJuego == gameStatus.LOOP || estadoJuego == gameStatus.BONUS || estadoJuego == gameStatus.POWERUP) {

            System.out.println("Loop o Bonus o Powerup");
            /**
             * *********
             */
            tiempoTranscurridoEnergia += delta;
            if (tiempoTranscurridoEnergia >= TIEMPO_PERDIDA_ENERGIA) {
                
                double aux_  = personajePrincipal.getEnergia() - 5;
                
                personajePrincipal.setEnergia(aux_);
                //energia -= 5; // Disminuir la energía en un 5%
                if (personajePrincipal.getEnergia() <= 0) {
                    personajePrincipal.setEnergia(0);
                    //energia = 0;
                }
                tiempoTranscurridoEnergia = 0; // Reiniciar el contador

                // Verificar si la energía es cero y cambiar a GAME_OVER
                if (personajePrincipal.getEnergia() == 0) {
                    estadoJuego = gameStatus.GAME_OVER;
                    try {
                        //FXPlayer.GAME_OVER.play(-20.0f);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    rank.actualizar(score);
                    score = 0;
                    rank = new Ranking();
                    hiScore = rank.getTop();
                    Escenario.clear();
                }
            }
            

            /**
             * **************
             */
            if(personajePrincipal.getPowerUpActivo() && aux == 0){
                
                bonus();
                aux=1;
            }

            if ((estadoJuego == gameStatus.BONUS) && !(personajePrincipal.getEstado() == P38Avion.estados.MURIENDO)) {
                
                if (timerBonus == 20) {
                    try {
                        
                        // FXPlayer.STAGE1.stop();
                        stopMusica();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (timerBonus < 0) {
                    if(personajePrincipal.getSuperShellActivo()){
                        personajePrincipal.activarSuperShell(false);
                    }
                    estadoJuego = gameStatus.LOOP;
                    // FXPlayer.STAGE1.loop(-20.0f);
                    reproducirMusica();
                    nivel.stop = false;
                }
                timerBonus -= delta * 3;

            }

            if ((estadoJuego == gameStatus.POWERUP) && !(personajePrincipal.getEstado() == P38Avion.estados.MURIENDO)) {

                if (timerPower < 10) {
                    //FXPlayer.POWEREND.play2(-20.0f);
                    personajePrincipal.finalizaPower();
                }

                if (timerPower < 0) {
                    //FXPlayer.POWEREND.stop();
                    estadoJuego = gameStatus.LOOP;
                    personajePrincipal.cambiar(P38Avion.estados.VIVO);
                }

                timerPower -= delta * 3;
            }

            nivel.update(delta);

            if (personajePrincipal.getEstado() == P38Avion.estados.MURIENDO) {
                if (timer == 0) {
                    try {
                        // FXPlayer.STAGE1.stop();
                        //stopMusica();
                        if (esjefe) {
                            //FXPlayer.BOSS1.stop();
                        }
                        //FXPlayer.MUERTE.play(-20.0f);
                        nivel.stop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (timer > 4) {
                    personajePrincipal.setEnergia(personajePrincipal.getEnergia()-1);
                    //energia--;
                    esjefe = false;
                    personajePrincipal.restaurar();
                    if (personajePrincipal.getEnergia() <= 0) {
                        personajePrincipal.setEnergia(0);
                        //energia = 0;
                        this.estadoJuego = gameStatus.GAME_OVER;
                        try {
                            //FXPlayer.GAME_OVER.play(-20.0f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        rank.actualizar(score);
                        score = 0;
                        rank = new Ranking();
                        hiScore = rank.getTop();
                        Escenario.clear();
                    } else {
                        this.estadoJuego = gameStatus.CARGANDO;
                        nivel.reCargarObjetos();
                        rank.actualizar(score);
                        try {
                            //FXPlayer.INTRO.play(-20.0f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        ;
                        timer = 0;
                    }
                }

                timer += delta ;
                personajePrincipal.update(delta);
                
                return;
            }

            for (KeyEvent event : keyEvents) {
                

                if ((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                    
                    stop();
                }

                if ((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_SPACE)/*&&(KeyEvent.getKeyText(event.getKeyCode()).toUpperCase().equals(appProperties.getProperty("disparo")))*/) {
                    
                    personajePrincipal.disparar();
                }

                if ((event.getID() == KeyEvent.KEY_RELEASED) && (event.getKeyCode() == KeyEvent.VK_P) /*&&(KeyEvent.getKeyText(event.getKeyCode()).toUpperCase().equals(appProperties.getProperty("pausa")))*/) {
                    
                    this.estadoJuego = gameStatus.PAUSA;
                    // FXPlayer.STAGE1.stop();
                    stopMusica();
                    //FXPlayer.PAUSA.play(-5.0f);
                }

                if ((event.getID() == KeyEvent.KEY_RELEASED) && (KeyEvent.getKeyText(event.getKeyCode()).toUpperCase().equals(appProperties.getProperty("toggle")))) {
                    if (musicaGeneral) {
                        stopMusica();
                        musicaGeneral = false;
                    } else {
                        reproducirMusica();
                        musicaGeneral = true;
                    }
                }

                /*if (keyboard.isKeyPressed(KeyEvent.VK_K)) {
                    personajePrincipal.cambiar(P38Avion.estados.MURIENDO);
                }*/

                if ((event.getKeyCode() == KeyEvent.VK_DOWN) /*&& (KeyEvent.getKeyText(event.getKeyCode()).toUpperCase().equals(appProperties.getProperty("abajo")))*/) {
                    //this.down = event.getKeyCode();
                    
                    this.down = KeyEvent.VK_DOWN;
                }

                if ((event.getKeyCode() == KeyEvent.VK_UP) /*&& (KeyEvent.getKeyText(event.getKeyCode()).toUpperCase().equals(appProperties.getProperty("arriba")))*/) {
                    //this.up = event.getKeyCode();
                   
                    this.up = KeyEvent.VK_UP;
                }

                if ((event.getKeyCode() == KeyEvent.VK_LEFT) /*&& (KeyEvent.getKeyText(event.getKeyCode()).toUpperCase().equals(appProperties.getProperty("izquierda")))*/) {
                    //this.left = event.getKeyCode();
                    
                    this.left = KeyEvent.VK_LEFT;
                }

                if ((event.getKeyCode() == KeyEvent.VK_RIGHT)/*&&(KeyEvent.getKeyText(event.getKeyCode()).toUpperCase().equals(appProperties.getProperty("derecha")))*/) {
                    //this.right = event.getKeyCode();
                    
                    this.right = KeyEvent.VK_RIGHT;
                }

            }

            if (keyboard.isKeyPressed(down) ) {
               
                personajePrincipal.down(delta);
                this.down = 0;
            }
            if (keyboard.isKeyPressed(up) ) {
                
                personajePrincipal.up(delta);
                this.up=0;
            }
            if (keyboard.isKeyPressed(left) ) {
                
                personajePrincipal.left(delta);
                this.left = 0;
            }
            if (keyboard.isKeyPressed(right) ) {
                
                personajePrincipal.right(delta);
                this.right=0;
            }

            personajePrincipal.update(delta);
            

            
        }

    }

    public static int nivel() {
        return numeroNivel;
    }

    public static void sumarScore(int puntos) {
        score = score + puntos;
    }

    public static void bossModeMusic() {

        if (esjefe) {
            stopMusica();
            //FXPlayer.BOSS1.loop(-20.0f);
        }

    }

    public static void llenarTanque() {
        P38Avion.setEnergia(100);
        //energia = 100;
    }
    protected void bonus() {
        this.estadoJuego = gameStatus.BONUS;
        timerBonus = 20;
        if (personajePrincipal.getSuperShellActivo()) {
            disparoContinuo();
        }
    }
    private void disparoContinuo() {
        
    }
    
    protected void powerup() {
        this.estadoJuego = gameStatus.POWERUP;
        timerPower = 35;
    }

    public static void esJefe() {
        esjefe = true;
    }



    public void siguienteNivel() {
        stopMusica();
        //FXPlayer.BOSS1.stop();
        if (numeroNivel == 2) {
            this.estadoJuego = gameStatus.SIGUIENTE;
            this.personajePrincipal.setAnimacion();
            //FXPlayer.WINGAME.play(-10f);
        } else {
            this.estadoJuego = gameStatus.SIGUIENTE;
            this.personajePrincipal.setAnimacion();
        }

    }

    private static void reproducirMusica() {
        switch (numeroNivel) {
            case 1: {
                //FXPlayer.MUSICA.loop(-20.0f);
            }
            break;
            default:
                //FXPlayer.MUSICA.loop(-20.0f);
                break;
        }
    }

    private static void stopMusica() {
        switch (numeroNivel) {
            case 1: {
                //FXPlayer.MUSICA.stop();
            }
            break;
            default:
                //FXPlayer.MUSICA.stop();
                break;
        }
    }

}
