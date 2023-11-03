package queue;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import main.GamePanel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics2D;



public class FoodPickup {

    GamePanel gp;
    public ArrayList<ArrayList<String>> queue;
    public BufferedImage sack;
    public int pickupx;
    public int pickupy;
    private int pickupxAdjust = 24; // change these to adjust item placement on tile
    private int pickupyAdjust = 12;
    long lastPutdownTime = 0;
    long putDownDelay = 500; // half seconf delay


    public FoodPickup(GamePanel gp) {
        this.gp = gp;
        queue = new ArrayList<ArrayList<String>>();    
        
        pickupx = gp.restaurant.pickupCounterLocation[0] + pickupxAdjust;
        pickupy = gp.restaurant.pickupCounterLocation[1] + pickupyAdjust;

        getImages();

    }

    public void getImages() {
        try {
            sack = ImageIO.read(getClass().getResourceAsStream("/res/objects/burger/sack.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (gp.player.station == "pickupCounter" && gp.keyH.gKeyPressed == true && currentTime - lastPutdownTime > putDownDelay) {
            if (gp.burger.burgerList.isEmpty() && !queue.isEmpty()) {
                ArrayList<String> newBurger = new ArrayList<>(queue.get(0));
                gp.burger.burgerList = new ArrayList<>(newBurger);
                queue.remove(0);
            }
            else if (!gp.burger.burgerList.isEmpty()) {
                ArrayList<String> newBurger = new ArrayList<>(gp.burger.burgerList);
                queue.add(newBurger);
                gp.burger.burgerList.clear();
            }

            lastPutdownTime = currentTime;

        }
    }

    public void draw(Graphics2D g2) {
        int xOffset = 0;
        for (int i = 0; i < queue.size(); i++) {
            g2.drawImage(sack, pickupx + xOffset, pickupy, gp.tileSize, gp.tileSize, null);
            xOffset += 24;
        }
    }
}
