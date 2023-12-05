package Juego;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import javax.swing.*;


public class Configuracion extends JFrame implements ActionListener {

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    String music_list[] = { "Original", "Otra" };
    String personajes[] = { "P38Avion", "P38Rojo"};
    JTabbedPane pestañas = new JTabbedPane();
    JDialog dialogo = new JDialog();
    JTextField efectos, musica, pausa, left, right, up, down, disparo, enter;

    JButton guardar = new JButton("Guardar");
    JButton pordefecto = new JButton("Reset");

    JRadioButton btnFullScreen = new JRadioButton("Pantalla completa");
    JRadioButton BtnWindowed = new JRadioButton("Ventana", true);
    JRadioButton BtnMusicON = new JRadioButton("Activado", true);
    JRadioButton BtnMusicOFF = new JRadioButton("Desactivado");
    

    
    JComboBox<String> pistas = new JComboBox<String>(music_list);
    JComboBox<String> personaje = new JComboBox<String>(personajes);

    JLabel msj = new JLabel();
    GamePropieties cfg;

    public Configuracion() {

        super("Configuracion juego");
        teclas();
        dialogo();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout(0, 0));
        this.getContentPane().setBackground(Color.BLACK);
        
        pestañas.add("Configuración", configuracion_general());
        pestañas.add("Controles", controles());
        pestañas.setBackground(Color.BLACK);
        pestañas.setForeground(Color.WHITE);
        
        this.getContentPane().add(pestañas);
        this.getContentPane().add(botonera(), BorderLayout.SOUTH);

        this.setSize(640, 480);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    public static void main(String args[]) {
        Configuracion juego = new Configuracion();
    }

    private JPanel configuracion_general() {

        JPanel config_gral = new JPanel(gbl);
        config_gral.setBackground(Color.BLACK);
        
        ButtonGroup resolucion = new ButtonGroup();
        ButtonGroup musica = new ButtonGroup();
 
        
        JLabel titulo = new JLabel("Configuración");
        titulo.setForeground(Color.WHITE);


        musica.add(BtnMusicON);
        musica.add(BtnMusicOFF);
        
        resolucion.add(btnFullScreen);
        resolucion.add(BtnWindowed);

        titulo.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 20));
        // Posicion en mi JPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Cuantas columnas quiero que ocupe mi obj
        gbc.gridheight = 1; // Cuantas filas quiero que ocupe mi obj
        // gbc.weightx=1.0; // 1.0 Para estirar la columna hasta el final del JPanel
        gbc.anchor = GridBagConstraints.WEST; // Posicion que ocupa dentro de la columna/fila
        gbc.fill = GridBagConstraints.HORIZONTAL; // Completa la celda en horizontal
        config_gral.add(titulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel labelSonido = new JLabel("Sonido");
        labelSonido.setForeground(Color.WHITE);
        config_gral.add(labelSonido, gbc);
        

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        BtnMusicON.setBackground(Color.BLACK);
        BtnMusicON.setForeground(Color.WHITE);
        config_gral.add(BtnMusicON, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        BtnMusicOFF.setBackground(Color.BLACK);
        BtnMusicOFF.setForeground(Color.WHITE);
        config_gral.add(BtnMusicOFF, gbc);

        // Etiqueta resolucion con sus opciones

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel labelResolucion = new JLabel("Resolucion");
        labelResolucion.setForeground(Color.WHITE);
        config_gral.add(labelResolucion, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        btnFullScreen.setBackground(Color.BLACK);
        btnFullScreen.setForeground(Color.WHITE);
        config_gral.add(btnFullScreen, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        BtnWindowed.setBackground(Color.BLACK);
        BtnWindowed.setForeground(Color.WHITE);
        config_gral.add(BtnWindowed, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        JLabel labelPersonaje = new JLabel("Avión");
        labelPersonaje.setForeground(Color.WHITE);
        config_gral.add(labelPersonaje, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        config_gral.add(personaje, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        JLabel labelPista = new JLabel("Pista");
        labelPista.setForeground(Color.WHITE);
        config_gral.add(labelPista, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        config_gral.add(pistas, gbc);

        return config_gral;
    }

    private JPanel controles() {

        JPanel controles = new JPanel(gbl);
        controles.setBackground(Color.BLACK);
        
        JLabel titulo = new JLabel("Configurar teclas");
        titulo.setForeground(Color.WHITE);

        titulo.setFont(new Font("Times-Roman", Font.BOLD, 25));

        gbc.gridx = 0;
        gbc.gridy = 0;

        controles.add(titulo, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 1;
        JLabel eSonido = new JLabel("Activar/Desactivar efectos de sonidos: ");
        eSonido.setForeground(Color.WHITE);
        controles.add(eSonido, gbc);

        gbc.gridy = 2;
        JLabel eMusica = new JLabel("Activar/desactivar música de fondo: ");
        eMusica.setForeground(Color.WHITE);
        controles.add(eMusica, gbc);

        gbc.gridy = 3;
        JLabel pausarJ = new JLabel("Pausar/Reanudar juego: ");
        pausarJ.setForeground(Color.WHITE);
        controles.add(pausarJ, gbc);

        gbc.gridy = 4;
        JLabel disp = new JLabel("Disparar: ");
        disp.setForeground(Color.WHITE);
        controles.add(disp, gbc);

        gbc.gridy = 5;
        JLabel inic = new JLabel("Iniciar juego: ");
        inic.setForeground(Color.WHITE);
        controles.add(inic, gbc);

        gbc.gridy = 6;
        JLabel izq = new JLabel("Izquierda: ");
        izq.setForeground(Color.WHITE);
        controles.add(izq, gbc);

        gbc.gridy = 7;
        JLabel der = new JLabel("Derecha: ");
        der.setForeground(Color.WHITE);
        controles.add(der, gbc);

        gbc.gridy = 8;
        JLabel arr = new JLabel("Arriba: ");
        arr.setForeground(Color.WHITE);
        controles.add(arr, gbc);

        gbc.gridy = 9;
        JLabel abj = new JLabel("Abajo: ");
        abj.setForeground(Color.WHITE);
        controles.add(abj, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;

        gbc.gridx = 2;
        gbc.gridy = 1;
        controles.add(efectos, gbc);

        gbc.gridy = 2;
        controles.add(musica, gbc);

        gbc.gridy = 3;
        controles.add(pausa, gbc);

        gbc.gridy = 4;
        controles.add(disparo, gbc);

        gbc.gridy = 5;
        controles.add(enter, gbc);

        gbc.gridy = 6;
        controles.add(left, gbc);

        gbc.gridy = 7;
        controles.add(right, gbc);

        gbc.gridy = 8;
        controles.add(up, gbc);

        gbc.gridy = 9;
        controles.add(down, gbc);

        return controles;
    }

    private JPanel botonera() {

        JPanel botonera = new JPanel();
        botonera.setBackground(Color.BLACK);

        guardar.addActionListener(this);
        pordefecto.addActionListener(this);
        
        guardar.setBackground(Color.WHITE);
        guardar.setForeground(Color.BLACK);
        pordefecto.setBackground(Color.WHITE);
        pordefecto.setForeground(Color.BLACK);
        
        botonera.add(guardar);
        botonera.add(pordefecto);

        return botonera;

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == guardar.getActionCommand()) {
            msj.setText("Configuracion guardada");
            dialogo.setVisible(true);
            dialogo.setSize(200, 150);
            dialogo.setLocationRelativeTo(null);
            JTextField[] campo = { efectos, musica, pausa, left, right, up, down, disparo, enter };
            for(JTextField campos: campo){
             cfg.guardar(campos.getName(),campos.getText());
            }
            if(btnFullScreen.isSelected()){
                cfg.guardar("fullScreen","true");
            }
            else{
                cfg.guardar("fullScreen","false");
            }
            
            if(BtnMusicON.isSelected()){
                cfg.guardar("sonidoGeneral","true");
            }
            else{
                cfg.guardar("sonidoGeneral","false");
            }

            cfg.guardar("personaje", personaje.getSelectedItem().toString().toLowerCase());
            cfg.guardar("pista", pistas.getSelectedItem().toString().toLowerCase());
            

        } else if (e.getActionCommand() == pordefecto.getActionCommand()) {
            pordefecto();
        }

    }

    private void dialogo() {

        msj.setFont(new Font("Times-Roman", Font.BOLD, 15));
        msj.setHorizontalAlignment(SwingConstants.CENTER);
        dialogo.setLayout(new GridLayout());
        dialogo.setTitle("Configuracion");
        dialogo.add(msj);
        dialogo.setResizable(false);
        dialogo.setLocationRelativeTo(null);
        dialogo.getContentPane().setBackground(Color.WHITE);

    }

    private void pordefecto() {

        msj.setText("Configuración por defecto");
        dialogo.setVisible(true);
        dialogo.setSize(300, 150);
        dialogo.setLocationRelativeTo(null);

        BtnWindowed.setSelected(true);
        BtnMusicON.setSelected(true);
        pistas.setSelectedIndex(0);
        personaje.setSelectedIndex(0);

        efectos.setText(cfg.defaultProps.getProperty("efectos"));
        musica.setText(cfg.defaultProps.getProperty("musica"));
        pausa.setText(cfg.defaultProps.getProperty("pausa"));
        left.setText(cfg.defaultProps.getProperty("izquierda"));
        right.setText(cfg.defaultProps.getProperty("derecha"));
        up.setText(cfg.defaultProps.getProperty("arriba"));
        down.setText(cfg.defaultProps.getProperty("abajo"));
        disparo.setText(cfg.defaultProps.getProperty("disparo"));
        enter.setText(cfg.defaultProps.getProperty("enter"));



    }

    private void teclas() {

        cfg= new GamePropieties();
    
        efectos = new JTextField(cfg.defaultProps.getProperty("efectos"));
        efectos.setName("efectos");
        musica = new JTextField(cfg.defaultProps.getProperty("musica"));
        musica.setName("musica");
        pausa = new JTextField(cfg.defaultProps.getProperty("pausa"));
        pausa.setName("pausa");
        left = new JTextField(cfg.defaultProps.getProperty("izquierda"));
        left.setName("izquierda");
        right = new JTextField(cfg.defaultProps.getProperty("derecha"));
        right.setName("derecha");
        up = new JTextField(cfg.defaultProps.getProperty("arriba"));
        up.setName("arriba");
        down = new JTextField(cfg.defaultProps.getProperty("abajo"));
        down.setName("abajo");
        disparo = new JTextField(cfg.defaultProps.getProperty("disparo"));
        disparo.setName("disparo");
        enter = new JTextField(cfg.defaultProps.getProperty("enter"));
        enter.setName("enter");

        
        JTextField[] campo = { efectos, musica, pausa, left, right, up, down, disparo, enter };

        for (JTextField campos : campo) {
            campos.setColumns(5);
            campos.setEditable(false);
            campos.setHorizontalAlignment(JTextField.CENTER);
            campos.setBackground(Color.white);
            campos.setForeground(Color.black);
            campos.addKeyListener(new KeyAdapter() {

                public void keyPressed(KeyEvent e) {
                    campos.setText(KeyEvent.getKeyText(e.getKeyCode()).toUpperCase());
                }
            });
        }
    }

    public class GamePropieties{
        String jgamePropsFile="jgame.properties";
	Properties gameProps=new Properties();
        Properties defaultProps=new Properties();

        public GamePropieties(){
            try{
            
                FileInputStream in = new FileInputStream("default.properties");
				defaultProps.load(in);
				in.close();

                gameProps=defaultProps;

				FileOutputStream output = new FileOutputStream(jgamePropsFile); 

				gameProps.store(output,null);
				output.close();

            }
            catch(Exception e){

            }
        }
        public void guardar(String key, String value){
            System.out.println(key + " - "+value);
            try{
                gameProps.setProperty(key,value);
                FileOutputStream out=new FileOutputStream("jgame.properties");
                gameProps.store(out,null);
                out.close();
            }catch(Exception e){
    
            }
        }

    }
}



