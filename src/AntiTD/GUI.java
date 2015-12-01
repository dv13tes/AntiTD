package AntiTD;

import javax.swing.*;
import javax.swing.border.Border;

import AntiTD.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * @author dv13trm
 */
public class GUI  {
    private Menu menu;
    private Thread gameThread;
    private Environment env;
    private JFrame frame;
    private JPanel buyPanel;
    private JButton buyButton;
    private JButton buyTeleport;
    //startscreen
    private String PlayerName;
    private JTextArea player;
    private JButton enterName;
    private JPanel startPanel;
    private JScrollPane playerScroll;

    public GUI () {

        frame = new JFrame("AntiTTD");
        env = new Environment(frame);
        env.start();
        startScreen();
        //menu = new Menu(frame);
        menu = new Menu(frame, this);
        menu.startMenu();
        menu.statMenu();

        frame.setVisible(true);


        frame.pack();

    }

    public void startGame() {
        env.startGame();
        env.repaint();
        frame.setVisible(true);
        frame.pack();
    }
    public void restartGame(){
        //restart
    }

    private void buildBuyPanel(){
        buyPanel = new JPanel();
        buyPanel.setBorder(BorderFactory.createLineBorder(Color.green));
        buyPanel.setBackground(Color.magenta);
        //basictropp button
        buyButton = new JButton("Basic troops");
        buyButton.setBackground(Color.white);
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("ELIASHEJ");
            }
        });
        //teleport troop button
        buyTeleport = new JButton("Teleport Troop");
        buyTeleport.setBackground(Color.white);
        buyTeleport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("TELEPORTELIAS");
            }
        });

        buyPanel.add(buyTeleport);
        buyPanel.add(buyButton, FlowLayout.LEFT);
        frame.add(buyPanel, BorderLayout.SOUTH);
    }

    private void startScreen(){
        player = new JTextArea(10,20);
        player.setEditable(true);

        player.setBorder(BorderFactory.createLineBorder(Color.black));

        startPanel = new JPanel();
        startPanel.setBackground(Color.white);
        startPanel.add(player, BorderLayout.CENTER);
        enterName = new JButton("Submit name");
        enterName.setBackground(Color.pink);
        startPanel.add(enterName, FlowLayout.LEFT);
        frame.add(startPanel);
        enterName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.remove(startPanel);
                frame.setSize(800, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(env, BorderLayout.CENTER);
                startGame();
                buildBuyPanel();
            }
        });

    }

}
