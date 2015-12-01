package AntiTD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * @author dv13trm
 */
public class Menu extends JMenu {
    //startmenu
    private JMenuItem newGame,restartGame, exitGame, pauseGame, mute;
    private JMenuBar startMenuBar = new JMenuBar();
    private JFrame frame;
    private GUI gui;
    private boolean pause = true;
    private boolean mutesound = true;
    //helpframe
    private JTextArea helpText;
    private JFrame helpFrame = new JFrame();
    private JScrollPane helpScroll;
    //statmenu
    private JMenu statmenu = new JMenu("Help");
    private JMenuItem  nameChange, about, help;
    private JMenuBar statMenuBar = new JMenuBar();





    public Menu(JFrame frame, GUI gui) {
        super("Start");
        this.gui = gui;
        this.frame = frame;
    }





    public void startMenu(){
        frame.setJMenuBar(startMenuBar);
        startMenuBar.add(this);
        //lägga till menyitems
        newGame = this.add("New Game");
        restartGame = this.add("Restart");
        pauseGame = this.add("Pause");
        mute = this.add("Mute");
        exitGame = this.add("Quit");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gui.startGame();
            }

        });
        restartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gui.restartGame();
            }
        });
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        pauseGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(pause){
                    //sätta nån loop variabel till falsk?
                    pauseGame.setText("Resume");
                    pause=false;
                }
                else{
                    //sätta nån loop variabel till sann?
                    pauseGame.setText("Pause");
                    pause=true;
                }
            }
        });
        mute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(mutesound){
                    //muta ljudet
                    mute.setText("Unmute");
                    mutesound=false;
                }
                else {
                    //unmuta ljudet
                    mute.setText("Mute");
                    mutesound = true;
                }
            }
        });


        startMenuBar.add(this);
    }

    public void statMenu(){
        frame.setJMenuBar(statMenuBar);
        statMenuBar.add(this);

        //lägga till menyitems
        help = statmenu.add("Help");
        about = statmenu.add("About");
        nameChange = statmenu.add("Change name");

        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                callHelpFrame();
            }
        });
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Öppnar en ruta med en text om vem som skapat
            }
        });
        nameChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        statMenuBar.add(statmenu);
    }

    private void callHelpFrame(){
        helpText = new JTextArea(15,15);
        helpText.setEditable(false);
        helpText.setWrapStyleWord(true);
        helpText.setLineWrap(true);
        helpText.setBackground(Color.yellow);
        helpText.append("Här ska det stå hjälp texten");
        helpFrame.add(helpText);
        helpScroll = new JScrollPane(helpText);
        helpFrame.add(helpScroll, BorderLayout.SOUTH);
        helpFrame.add(new JLabel(new ImageIcon("tobiashej.jpg")));
        helpFrame.getContentPane().setBackground(Color.yellow);
        helpFrame.setVisible(true);
        helpFrame.pack();
    }

}

