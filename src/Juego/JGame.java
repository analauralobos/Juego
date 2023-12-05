package Juego;

import java.awt.*;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.image.BufferStrategy;
import java.awt.image.*;
import java.util.*;
import java.io.*;

public abstract class JGame extends GameLoop {

    private JFrame frame;
    private JPanel canvas;
    private BufferStrategy buffer;
    private Keyboard keyboard;
    private static int winModeX, winModeY;          // top-left corner (x, y)
    private static int winModeWidth, winModeHeight; // width and height

    private boolean fullScreenSupported;

    private final GraphicsDevice defaultScreen;
    private DisplayMode origDisplayMode;
    private DisplayMode newDisplayMode;

    protected Properties appProperties = new Properties();

    public JGame(String title, int width, int height) {

        this.readPropertiesFile();
        boolean bFullScreen = Boolean.valueOf(appProperties.getProperty("fullScreen", "false"));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        winModeWidth = (int) dim.getWidth();
        winModeHeight = (int) dim.getHeight() - 35; // minus task bar
        winModeHeight = (int) dim.getHeight();
        winModeX = 0;
        winModeY = 0;

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.defaultScreen = env.getDefaultScreenDevice();
        fullScreenSupported = defaultScreen.isFullScreenSupported();

        frame = new JFrame(title);

        if ((fullScreenSupported) && (bFullScreen)) {
            newDisplayMode = new DisplayMode(width, height, 32, DisplayMode.REFRESH_RATE_UNKNOWN);
            frame.setUndecorated(true);
            defaultScreen.setFullScreenWindow(frame);


            /* Ocultar el puntero del mouse */
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Point hotSpot = new Point(0, 0);
            BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT);
            Cursor invisibleCursor = toolkit.createCustomCursor(cursorImage, hotSpot, "InvisibleCursor");
            frame.setCursor(invisibleCursor);
        } else {
            height -= 24;
        }

        frame.setResizable(false);
        canvas = new JPanel();
        canvas.setIgnoreRepaint(true);
        canvas.setSize(width, height);

        frame.setSize(width, height);
        frame.getContentPane().add(canvas, BorderLayout.CENTER);

        frame.getContentPane().setPreferredSize(new Dimension(width, height));

        frame.pack();
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);

        frame.createBufferStrategy(2);
        buffer = frame.getBufferStrategy();

        // create our input classess and add them to the canvas
        keyboard = new Keyboard();
        canvas.addKeyListener((KeyListener) keyboard);
        canvas.requestFocus();

        if (defaultScreen.isDisplayChangeSupported() && bFullScreen) {
            defaultScreen.setDisplayMode(newDisplayMode);
        }

    }

    public int getWidth() {
        return frame.getWidth();
    }

    public int getHeight() {
        return frame.getHeight();
    }

    public String getTitle() {
        return frame.getTitle();
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void startup() {
        gameStartup();
    }

    public void update(double delta) {
        // call the input updates first
        keyboard.update();
        //mouse.update();
        //mouseWheel.update();
        //call the abstract update
        gameUpdate(delta);
    }

    public void draw() {
        Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());

        g.setColor(Color.RED);
        g.drawRect(0, 0, frame.getWidth() - 1, frame.getHeight() - 1);

        gameDraw(g);

        // show our changes on the canvas
        buffer.show();
        // release the graphics resources
        g.dispose();
    }

    public void shutdown() {
        gameShutdown();
        frame.dispose();
    }

    protected void readPropertiesFile() {

        try {
            FileInputStream in = new FileInputStream("jgame.properties");
            appProperties.load(in);
            System.out.println("jgame");
            in.close();
        } catch (IOException e) {
            System.out.println("Error en metodo  readPropertiesFile(): " + e);
        }

    }

    public abstract void gameStartup();

    public abstract void gameUpdate(double delta);

    public abstract void gameDraw(Graphics2D g);

    public abstract void gameShutdown();

}
