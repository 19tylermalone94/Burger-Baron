package item;

import java.util.HashMap;
import java.util.Set;
import java.awt.Graphics2D;
import main.GamePanel;
import main.KeyHandler;

public class FloorItems {

    public HashMap<int[], String> floorItemList;
    GamePanel gp;
    KeyHandler keyH;
    long lastPickupTime;
    long pickupDelay = 500; // half second

    public FloorItems(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        floorItemList = new HashMap<int[], String>();
    }

    public void update() {

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastPickupTime > pickupDelay && keyH.gKeyPressed == true) {
            Set<int[]> points = floorItemList.keySet();
                for (int[] point : points) {
                    int xpos = point[0];
                    int ypos = point[1];
                    if (Math.abs((gp.player.x + 24) - xpos) < 24 && Math.abs((gp.player.y + 48) - ypos) < 24) {
                        gp.burger.burgerList.add(floorItemList.get(point));
                        floorItemList.remove(point);
                        break;
                    }
                }
            lastPickupTime = currentTime;
        }
        else if (currentTime - lastPickupTime > pickupDelay && keyH.xKeyPressed == true && !gp.burger.burgerList.isEmpty()) {
            int[] points = new int[2];
            switch (gp.player.direction) {
                case "up": points[0] = gp.player.x + 24; break;
                case "down": points[0] = gp.player.x + 24; break;
                case "left": points[0] = gp.player.x; break;
                case "right": points[0] = gp.player.x + 36; break;
            }
            points[1] = gp.player.y + 48;
            floorItemList.put(points, gp.burger.burgerList.get(gp.burger.burgerList.size() - 1));
            gp.burger.burgerList.remove(gp.burger.burgerList.size() - 1);
            lastPickupTime = currentTime;
        }

    }

    public void draw(Graphics2D g2) {
        // System.out.println(floorItemList.size());
        if (!floorItemList.isEmpty()) {
            Set<int[]> points = floorItemList.keySet();
            for (int[] point : points) {
                g2.drawImage(gp.burger.burgerMap.get(floorItemList.get(point)), point[0], point[1], null);
            }
        }
    }



    
}
