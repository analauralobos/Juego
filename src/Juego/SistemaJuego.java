package Juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class SistemaJuego extends JFrame implements ActionListener {

    Fondo fondo = new Fondo();
    JPanel configuracion;
    Juego j1, j2, j3, j4, j5, j6;
    CardLayout cl = new CardLayout();
    Font font;
    JPanel opcionesInicio;
    JPanel contenedor;
    JPanel titulo = new JPanel();
    JButton iniciar, config, volver;
    JPanelBackgroundSemiOpaco juegos;

    public SistemaJuego() {

        super("Proyecto POO");
        fondo.setLayout(new BorderLayout());
        elegirOpciones();
        contenedor = new JPanel();
        contenedor.setLayout(cl);
        juegos = new JPanelBackgroundSemiOpaco();

        this.setContentPane(fondo);
        this.getContentPane().add(opcionesInicio, BorderLayout.EAST);
        this.getContentPane().add(contenedor, BorderLayout.CENTER);
        //this.getContentPane().add(titulo, BorderLayout.NORTH);

        titulo.add(new JLabel("JUEGOS"));
        contenedor.add("lista", juegos);

        j1 = new Juego("Batalla Midway 1943");
        j1.addActionListener(this);
        j1.setActionCommand(j1.get_titulo());
        juegos.add(j1);
        contenedor.add(j1.get_titulo() + "_info", j1.descripcion());
        contenedor.add("cfg", j1.config_panel());

        j2 = new Juego("Mario Bros");
        j2.addActionListener(this);
        j2.setActionCommand(j2.get_titulo());
        juegos.add(j2);

        j3 = new Juego("Sonic");
        j3.addActionListener(this);
        j3.setActionCommand(j3.get_titulo());
        juegos.add(j3);

        j4 = new Juego("Pacman");
        j4.addActionListener(this);
        j4.setActionCommand(j4.get_titulo());
        juegos.add(j4);

        j5 = new Juego("Final Fantasy");
        j5.addActionListener(this);
        j5.setActionCommand(j5.get_titulo());
        juegos.add(j5);

        j6 = new Juego("Mario Kart");
        j6.addActionListener(this);
        j6.setActionCommand(j6.get_titulo());
        juegos.add(j6);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(650, 600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

    }

    public static void main(String args[]) {
        new SistemaJuego();

    }

    private void elegirOpciones() {

        opcionesInicio = new JPanel(new GridBagLayout());
        
        opcionesInicio.setBackground(Color.BLACK);
        iniciar = new JButton("JUGAR");
        config = new JButton("CONFIGURACION");
        volver = new JButton("VOLVER");

        // Set the size of all buttons
        Dimension buttonSize = new Dimension(200, 50);
        iniciar.setPreferredSize(buttonSize);
        config.setPreferredSize(buttonSize);
        volver.setPreferredSize(buttonSize);
        iniciar.setBackground(Color.WHITE);
        iniciar.setForeground(Color.BLACK);
        config.setBackground(Color.WHITE);
        config.setForeground(Color.BLACK);
        volver.setBackground(Color.WHITE);
        volver.setForeground(Color.BLACK);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("letras/ARCADEPI.ttf"))
                    .deriveFont(15f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
            iniciar.setFont(font);
            config.setFont(font);
            volver.setFont(font);
        } catch (IOException e) {
            System.out.println(e);
        } catch (FontFormatException e) {
            System.out.println(e);
        }
        
        // Create GridBagConstraints for center alignment
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 0, 5, 0);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Add buttons to the panel with center alignment
        constraints.gridy = 0;
        opcionesInicio.add(iniciar, constraints);

        constraints.gridy = 1;
        opcionesInicio.add(config, constraints);

        constraints.gridy = 2;
        opcionesInicio.add(volver, constraints);

        iniciar.addActionListener(this);
        volver.addActionListener(this);
        config.addActionListener(this);

        opcionesInicio.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == j1.getActionCommand()) {
            System.out.println("juego 1");
            opcionesInicio.setVisible(true);
            cl.show(contenedor, j1.get_titulo() + "_info");
        } else if (e.getActionCommand() == j2.getActionCommand()) {
            System.out.println("juego 2");
        }
        if (e.getActionCommand() == iniciar.getActionCommand()) {
            BatallaMidway1943 game = new BatallaMidway1943();

            Thread t = new Thread() {
                public void run() {
                    game.run(1.0 / 60.0);
                    System.exit(0);
                }
            };

            t.start();

        }
        if (e.getActionCommand() == volver.getActionCommand()) {
            cl.show(contenedor, "lista");
            opcionesInicio.setVisible(false);
        }
        if (e.getActionCommand() == config.getActionCommand()) {
            cl.show(contenedor, "cfg");

        }

    }

    private class Juego extends JButton {

        private Configuracion configuracion;
        private String titulo;
        private JPanel descripcion = new JPanel(new GridBagLayout());

        public Juego(String titulo) {
            this.titulo = titulo;
            this.setMargin(new Insets(0, 0, 0, 0));
            this.setIcon(new ImageIcon(getClass().getResource("imagenes/" + titulo + ".jpg")));
        }

        public Container config_panel() {

            configuracion = new Configuracion();
            configuracion.setVisible(false);

            return configuracion.getContentPane();
        }

        public String get_titulo() {
            return titulo;
        }

        public JPanel descripcion() {

            //descripcion.setLayout(new BorderLayout());
            descripcion.setBackground(Color.BLACK);
            JLabel imagen = new JLabel();
            imagen.setIcon(new ImageIcon(getClass().getResource("imagenes/Batalla Midway 1943 1.jpg")));
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 50;
            constraints.gridy = 0;
            constraints.anchor = GridBagConstraints.CENTER;

            // Agregar la imagen al JPanel con las restricciones
            descripcion.add(imagen, constraints);      
            
            //descripcion.add(imagen,BorderLayout.CENTER);

            return descripcion;
        }

    }

    private class Fondo extends JPanel {

        private Image imagen;

        public void paint(Graphics g) {

            imagen = new ImageIcon(getClass().getResource("imagenes/fondo.gif")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);

        }
    }

    class JPanelBackgroundSemiOpaco extends JPanel {

        public void paintComponent(Graphics g) {

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.80f));
            g2d.setColor(Color.black);
            g2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 10);

        }
    }

}
