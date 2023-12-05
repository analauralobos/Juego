package Juego;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class Keyboard implements KeyListener
{
    private final int KEY_CODE_MAX = 256;
    private boolean[] keys;
    private LinkedList<KeyEvent> eventList;
   //agregado ana laura
    public Keyboard() {
        keys = new boolean[KEY_CODE_MAX];
        eventList = new LinkedList<>();
    }
    
    public boolean isKeyPressed(int keyCode)
    {
        // check if the key a fast key
        if((keyCode >= 0) && (keyCode < KEY_CODE_MAX))
        {
            return keys[keyCode];
        }
        return false;
    }

    public void keyPressed(KeyEvent event)
    {
        int keyCode = event.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            keys[keyCode] = true;
        }
        addEvent(event);
    }


    public void keyReleased(KeyEvent event)
    {
        addEvent(event);
    }


    public void keyTyped(KeyEvent event)
    {
        addEvent(event);
    }

    void update() {
        
    }

    LinkedList<KeyEvent> getEvents() {
        
        
    LinkedList<KeyEvent> events = new LinkedList<>(eventList);
        eventList.clear(); // Limpiar la lista después de obtener los eventos
        return events;
        
        
    
    
    }

    private void addEvent(KeyEvent event) {
        /*agregado ana laura*/
       eventList.add(event);
    }

    void processEvent(KeyEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

/*
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private boolean[] keys;
    
    public Keyboard() {
        keys = new boolean[256];
    }
    
    public boolean isKeyDown(int keyCode) {
        if (keyCode >= 0 && keyCode < keys.length) {
            return keys[keyCode];
        }
        return false;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            keys[keyCode] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            keys[keyCode] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No es necesario implementar este método para el manejo de teclado en un juego
    }
}

*/