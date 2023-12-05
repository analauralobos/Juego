package Juego;

import java.awt.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public abstract class Escenario {

    protected boolean stop = false;
    protected int lastCheckPoint = 1; //1
    protected int pos = 0;
    protected int pos2 = 0;
    protected int counterMapa = 0;
    protected int[] checkpoint;
    protected RandomAccessFile raf, raf2;
    protected Fondo fondo;
    protected Enemigo jefeFinal;
    
    protected ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>(1);
    protected ArrayList<ContenedorBonus> esferas = new ArrayList<ContenedorBonus>(1);
    protected ArrayList<Bonus> bonusObtenibles = new ArrayList<Bonus>(1);
    protected ArrayList<ContenedorBonus> esferasColisionables = new ArrayList<ContenedorBonus>(1);
    
    protected ArrayList<Enemigo> enemigosColisionables = new ArrayList<Enemigo>(1);
    protected ArrayList<Municion> municionEnemigaColisionada = new ArrayList<Municion>(1);
    protected ArrayList<Municion> municionPersColisionada = new ArrayList<Municion>(1);
    protected ArrayList<Municion> municionEnemiga = new ArrayList<Municion>(1);
    protected static Escenario NIVEL = null;
    protected final Rectangle limites;
    protected final int PILAR = 94;

    public ArrayList<Municion> municionHeroe = new ArrayList<Municion>(1);

    protected Escenario(String filename) {
        fondo = new Fondo(filename);
        limites = new Rectangle(0, 80, 805, 500);
    }

    protected static void crear_nivel() {
        if (NIVEL == null) {
            synchronized (Escenario.class) {
                if (NIVEL == null) {
                    switch (BatallaMidway1943.nivel()) {
                        case 1:
                            NIVEL = new PrimerNivel();
                            break;
                        case 2:
                            NIVEL = new SegundoNivel();
                            break;
                    }

                }
            }
        }

    }

    public static Escenario get_nivel() {
        if (NIVEL == null) {
            crear_nivel();
        }
        return NIVEL;
    }

    public static void clear() {
        NIVEL = null;
    }

    protected abstract void generarEsferas();

    protected abstract void generarEnemigos();

    public void update(double delta) {

        esferasColisionables.clear();
        enemigosColisionables.clear();
        municionHeroe.removeAll(municionPersColisionada);
        bonusObtenibles.clear();
        municionEnemigaColisionada.clear();

        if (fondo.positionY == 0) {
            this.stop = true;
        }

        if (fondo.positionY > -350) {
            BatallaMidway1943.toggleBoss();
            BatallaMidway1943.bossModeMusic();
        }

        if (counterMapa > 2 && stop == false) {
            //fondo.positionY++;
            fondo.positionY =fondo.positionY + 4 ;//MODIFICAR EL 4 PARA CAMBIAR LA VELOCIDAD DEL FONDO
            jefeFinal.update(delta);

            for (Enemigo e : enemigos) {
                if (e.estado == Enemigo.estadoEnemigo.DESACTIVADO) {
                    e.update(delta);
                    if (e.cajaColision.intersects(this.limites)) {
                        e.isVisible = true;
                        e.estado = Enemigo.estadoEnemigo.VIVO;
                    }
                }
            }

            for (ContenedorBonus esfera : esferas) {
                if (!esfera.activado) {
                    esfera.positionY++;
                    esfera.updateCajaColision();
                    if (Escenario.get_nivel().limites.intersects(esfera.cajaColision)) {
                        esfera.setVisible(true);
                        esfera.activado = true;
                    }
                }

            }
            counterMapa = 0;
        }
        counterMapa++;

        for (Enemigo e : enemigos) {
            if (e.isVisible) {
                if (!stop && e.estado == Enemigo.estadoEnemigo.CONGELADO) {
                    e.estado = Enemigo.estadoEnemigo.VIVO;
                }
                e.update(delta);
                enemigosColisionables.add(e);
            }
        }

        if (this.jefeFinal.estado != Enemigo.estadoEnemigo.DESACTIVADO) {
            this.jefeFinal.update(delta);
        } else if (stop) {
            this.jefeFinal.update(delta);
        }

        for (ContenedorBonus esfera : esferas) {
            if (esfera.isVisible) {
                esfera.update(delta);
                esferasColisionables.add(esfera);
                if (!esfera.esTomado && this.limites.contains(esfera.cajaColision)) {
                    bonusObtenibles.add(esfera);
                }
            }
        }

        

        for (Municion m : municionHeroe) {
            this.colisionMunicion(m);
            m.update(delta);
            if (!m.isVisible) {
                this.municionPersColisionada.add(m);
            }

        }
        for (Municion m : municionEnemiga) {
            m.update(delta);

            if (!m.isVisible) {
                municionEnemigaColisionada.add(m);
            }

        }

        municionEnemiga.removeAll(municionEnemigaColisionada);

        // Los checkpoint se encuentran guardados por posicion del fondo, si esta posicion se supera movemos la posicion del ultimo checkpoint
        for (int i = 0; i < checkpoint.length; i++) {
            if (checkpoint[i] == fondo.positionY) {
                lastCheckPoint = checkpoint[i];
            }
        }

    }

    public float getWidth() {
        return (float) fondo.getWidth();
    }

    public float getHeight() {
        return (float) fondo.getY();
    }

    public void display(Graphics2D g2) {

        fondo.display(g2);

        for (ContenedorBonus e : esferasColisionables) {
            e.display(g2);
        }

        for (Municion m : municionHeroe) {
            m.display(g2);
        }

        for (Enemigo e : enemigosColisionables) {
            e.display(g2);
        }

        if (jefeFinal.isVisible) {
            jefeFinal.display(g2);
        }

        for (Municion m : municionEnemiga) {
            m.display(g2);
        }

    }
   

    public void addEnemigo(Enemigo enemigo) {
        this.enemigos.add(enemigo);
    }

    public void addEsfera(ContenedorBonus esfera) {
        this.esferas.add(esfera);
    }


    public boolean colisionMunicionEnemiga(P38Avion p38avion) {
        for (Municion municion : municionEnemiga) {
            if (p38avion.cajaColision.intersects(municion.cajaColisionMunicion)) {
                municion.hitEnemigo();
                return true;
            }
        }
        return false;
    }

    public boolean colisionEnemigo(Rectangle heroeHitbox) {

        if (heroeHitbox.intersects(jefeFinal.cajaColision)) {
            return true;
        }
        for (Enemigo enemigo : enemigosColisionables) {
            if (enemigo.cajaColision.intersects(heroeHitbox)) {
                return true;
            }
        }
        return false;

    }

    public boolean colisionBonus(Rectangle heroeHitbox) {
        for (Bonus b : bonusObtenibles) {
            if (b.cajaColision.intersects(heroeHitbox)) {
                b.setTomado(true);
                return true;
            }
        }
        return false;
    }

    public boolean colisionMunicion(Municion municion) {

        if (jefeFinal.cajaColision.intersects(municion.cajaColisionMunicion)) {
            municion.hitBonus();
            municion.hitEnemigo();
            jefeFinal.hitted();
            return true;
        }

        for (Enemigo e : enemigosColisionables) {
            if (e.cajaColision.intersects(municion.cajaColisionMunicion)) {
                municion.hitEnemigo();
                e.estado = Enemigo.estadoEnemigo.MUERTO;
                return true;
            }
        }

        

        for (ContenedorBonus e : esferasColisionables) {
            if (e.cajaColision.intersects(municion.cajaColisionMunicion)) {
                municion.hitBonus();
                e.golpeado = true;
                return true;
            }
        }

        return false;

    }

    public void stop() {
        stop = true;
    }

    public void reCargarObjetos() {
        ArrayList<ContenedorBonus> esferasRemover = new ArrayList<ContenedorBonus>(1);
        ArrayList<Enemigo> enemigosRemover = new ArrayList<Enemigo>(1);
        this.municionEnemiga.clear();
        this.municionHeroe.clear();

        int movimiento; //Variable para guardar cuanto se movio el mapa desde que arranco el nivel
        this.stop = false;    // Para que el mapa vuelva a moverse

        // Calculo cuanto se movio el mapa desde el ultimo checkpoint, en caso de que no se haya llegado, se restaura por defecto.
        if (lastCheckPoint != 1) {
            movimiento = (-(lastCheckPoint) + ((int) this.fondo.positionY));
            this.fondo.positionY = lastCheckPoint;
        } else {
            movimiento = (5450) + ((int) this.fondo.positionY);
            this.fondo.positionY = -5450;
        }
       

        jefeFinal.restaurar();
        if (lastCheckPoint != 1) {
            jefeFinal.positionY = jefeFinal.positionY + (5450 + lastCheckPoint);
        }

        try {
            raf.seek(pos);
            raf2.seek(pos2);
            for (Enemigo e : enemigos) {
                e.setX(raf.readDouble());
                if (lastCheckPoint == 1) {
                    e.setY(raf.readDouble());
                } else {
                    e.setY(raf.readDouble() + (5450 + lastCheckPoint));
                }
                e.restaurar();
                if (limites.contains(e.cajaColision.x, e.cajaColision.y) || e.cajaColision.y >= 580) {
                    enemigosRemover.add(e);
                    pos += 16;
                }
            }

            for (ContenedorBonus e : esferas) {
                e.setX(raf2.readDouble());

                if (lastCheckPoint == 1) {
                    e.setY(raf2.readDouble());
                } else {
                    e.setY(raf2.readDouble() + (5450 + lastCheckPoint));
                }
                e.restaurar();
                if (limites.contains(e.cajaColision.x, e.cajaColision.y) || e.cajaColision.y >= 580) {
                    esferasRemover.add(e);
                    pos2 += 16;
                }
            }

        } catch (IOException e1) {

            e1.printStackTrace();
        }

        esferas.removeAll(esferasRemover);
        enemigos.removeAll(enemigosRemover);
        esferasRemover.clear();
        enemigosRemover.clear();

    }

    

    protected void clearAll() {

        enemigos.clear();
        esferas.clear();
        bonusObtenibles.clear();
        esferasColisionables.clear();
        enemigosColisionables.clear();
        municionEnemigaColisionada.clear();
        municionPersColisionada.clear();
        municionEnemiga.clear();
        municionHeroe.clear();

    }

}
