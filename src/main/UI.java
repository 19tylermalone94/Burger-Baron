package main;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class UI {

    MenuPanel mp;
    Font arial_40;
    Graphics2D g2;

    public UI(MenuPanel mp) {
        this.mp = mp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        drawMenu();
        
    }

    public void drawMenu() {
        // window
        int x = 0;
        int y = 0;
        int width = 300;
        int height = 576;

        drawSubWindow(x, y, width, height);
    }

    public void drawSubWindow(int x, int y, int width, int height) {

        System.out.println("Here?");
        g2.setColor(new Color(255, 200, 225, 25));
        g2.fillRoundRect(x, y, width, height, 0, 0);


    }
    
}
