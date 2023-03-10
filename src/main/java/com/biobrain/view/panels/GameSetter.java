package com.biobrain.view.panels;

/*
 * GameSetter | Class
 * creates JFrame window and sets configurations
 * adds game logic as GamePanel to the JFrame window
 * displays JFrame window and pop-up instructions to user
 * begins game
 */

import com.biobrain.util.FileLoader;
import com.biobrain.util.WindowInterface;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameSetter extends JFrame implements WindowInterface {
    private static JFrame window;
    private static GamePanel gamePanel;
    private static InventoryPanel inventoryPanel;
    private JLabel inventoryImg;
    private static final BufferedImage icon = FileLoader.loadBuffered("images/player/player_down_1.png");
    // create a window of the game logic and begins play

    public static void setGame() {
        window = new JFrame();

        // create new JFrame window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // define what happens upon closing window
        window.setResizable(false);                              // set ability to resize display window
        window.setTitle("BioBrain");                            // set title of window to game title
        window.setIconImage(icon);

        gamePanel = new GamePanel();               // new instance of GamePanel (contains game logic)
//        inventoryPanel = new InventoryPanel(this, gamePanel); // new panel for inventory
//        inventoryImg = new JLabel(new ImageIcon(image));            // label to add inventory img
//        inventoryPanel.add(inventoryImg);

        window.add(gamePanel);                                  // add Game Panel as window display
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);                                // make window display visible

        gamePanel.setupGame();
        gamePanel.gameState = gamePanel.titleState;             //sets the state to the title screen

        gamePanel.startGameThread();                            // begin the game thread to start game loop

        //WindowInterface.displayPopUpWindow(gamePanel, FileLoader.loadTextFile("Instructions/Instructions.txt")); // call a pop-up window
    }

    public static void restartGame() {
        gamePanel.stopMusic();
        gamePanel = null;
        window.dispose(); // dispose of the old window

        // create a new window and gamePanel
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("BioBrain");
        window.setIconImage(icon);
        gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.gameState = gamePanel.titleState;
        gamePanel.startGameThread();
    }
}