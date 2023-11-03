package main;

import javax.swing.JFrame;
import java.awt.Dimension;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setPreferredSize(new Dimension(1068, 576));

        GamePanel gamePanel = new GamePanel();
        
        window.add(gamePanel);
    
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

    }
    
}
