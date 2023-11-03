package main;

import java.util.HashMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.Formatter;
import entity.Customer;

public class MenuManager {
    
    HashMap<String, String[]> menuMap;
    HashMap<String, String> orderScreenMap;
    HashMap<String, Color> toppingColorMap;
    GamePanel gp;
    KeyHandler keyH;
    public String[] mainMenu = {"s: Sales", "o: orders", "r: rating"};
    public int burgersSold = 0;
    public String[] salesMenu = {"burgers sold: " + burgersSold, "nice"};
    public String menuState = "main";
    int textX, textY;
    Formatter formatter = new Formatter();
    DecimalFormat df = new DecimalFormat("0.00");



    public MenuManager(GamePanel gp, KeyHandler keyH) {

        this.keyH = keyH;
        this.gp = gp;

        menuMap = new HashMap<String, String[]>();
        menuMap.put(menuState, mainMenu);
        menuMap.put("sales", salesMenu);

        orderScreenMap = new HashMap<String, String>() {
            {
                put("bun", "bun");
                put("pattyCooked", "meat");
                put("lettuce", "letc");
                put("tomato", "tmto");
                put("cheese", "ches");

            }
        };

        toppingColorMap = new HashMap<String, Color>() {
            {
                put("bun", new Color(250, 203, 110));
                put("meat", new Color(90, 61, 4));
                put("letc", new Color(77, 236, 66));
                put("tmto", new Color(252, 17, 40));
                put("ches", new Color(252, 252, 17));
            }
        };


    }

    public void update() {
        if (keyH.sKeyPressed == true) {
            menuState = "sales";
        }
        if (keyH.mKeyPressed == true) {
            menuState = "main";
        }
        if (keyH.oKeyPressed == true) {
            menuState = "orders";
        }
        if (keyH.rKeyPressed == true) {
            menuState = "rating";
        }

    }

    public void drawTime(Graphics2D g2) {
        g2.setColor(Color.white);
        if (gp.clock.gameHour < 10) {
            gp.clock.gameHourString = "0" + gp.clock.gameHour;
        }
        else {
            gp.clock.gameHourString = Integer.toString(gp.clock.gameHour);
        }

        if (gp.clock.gameMinute < 10) {
            gp.clock.gameMinuteString = "0" + gp.clock.gameMinute;
        }
        else {
            gp.clock.gameMinuteString = Integer.toString(gp.clock.gameMinute);
        }
        g2.drawString(gp.clock.gameHourString + ":" + gp.clock.gameMinuteString, 780, 50);
    
    }

    public void drawMainMenu(Graphics2D g2, int x, int y) {

        g2.setColor(Color.white);
        int yOffset = 0;

        for (String string : mainMenu) {
            g2.drawString(string, x, y + yOffset);
            yOffset += 24;
        }

    }

    public void drawSalesMenu(Graphics2D g2, int x, int y) {

        g2.setColor(Color.white);

        g2.drawString("burgers sold: " + burgersSold, x, y);

        y += 24;
        
        g2.drawString("Total Sales: $" + df.format(gp.restaurant.totalSales), x, y);

    }

    public void drawRatingMenu(Graphics2D g2, int x, int y) {

        //formatter.format("%.1f", gp.restaurant.totalRating);
        g2.drawString("Restaurant Rating: " + df.format(gp.restaurant.totalRating) + " / 10", x, y);
        y += 48;
        g2.drawString("Latest Reviews:  ", x, y);

        gp.restaurant.getLatestReview();
        y += 48;
        int yOffset = 0;
        String line = "'";
        for (String word : gp.restaurant.latestReview.split(" ")) {
            line += word + " ";
            if (line.length() > 20) {
                g2.drawString(line, x, y + yOffset);
                line = "";
                yOffset += 24;
            }
        }
        g2.drawString(line + "'", x, y + yOffset);

    }

    public void drawOrders(Graphics2D g2, int x, int y) {
        int yOffset = 0;
        for (Customer customer : gp.customersOrdered.queue) {
            
            int xOffset = 0;
            for (String topping : customer.order) {
                String nextTopping = orderScreenMap.get(topping);
                if (customer.foodReady == false) {
                    g2.setColor(toppingColorMap.get(nextTopping));
                    g2.drawString(nextTopping, x + xOffset, y + yOffset);
                    xOffset += 35;
                }
            }
            yOffset += 24;
        }
    }
    
}
