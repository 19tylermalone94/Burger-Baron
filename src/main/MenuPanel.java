package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;



public class MenuPanel {

    GamePanel gp;
    int x, y, screenHeight, screenWidth;
    Graphics2D g2;
    Font font = new Font ("Bauhaus 93", Font.PLAIN, 12);
    int textX, textY;
    

    public MenuPanel(GamePanel gp) {

        this.gp = gp;
        screenWidth = gp.screenWidth / 2;
        screenHeight = gp.screenHeight;
        x = gp.screenWidth;
        y =  0;
        textX = x + 24;
        textY = y + 200;




    }


    public void draw(Graphics2D g2) {

        g2.setFont(font);
        g2.setColor(Color.white);

        switch(gp.menuManager.menuState) {
            case "main": gp.menuManager.drawMainMenu(g2, textX, textY); break;
            case "sales": gp.menuManager.drawSalesMenu(g2, textX, textY); break;
            case "orders": gp.menuManager.drawOrders(g2, textX, textY); break;
            case "rating": gp.menuManager.drawRatingMenu(g2, textX, textY); break;
        }
        if (gp.menuManager.menuState != "main") {
            g2.setColor(Color.white);
            g2.drawString("m: Main menu", textX, 500);
        }

        gp.menuManager.drawTime(g2);
    }

}
