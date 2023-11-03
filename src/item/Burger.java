package item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.imageio.ImageIO;
import entity.Player;
import java.awt.Graphics2D;
import java.io.IOException;
import main.KeyHandler;
import main.GamePanel;
import java.awt.image.BufferedImage;
import main.UtilityTool;


public class Burger {

    public ArrayList<String> burgerList;
    public HashMap<String, BufferedImage> burgerMap;
    int MAX_BURGERS_GRILL = 9;
    GamePanel gp;
    KeyHandler keyH;
    Player player;
    BufferedImage bun, pattyCooked, pattyRaw, pattyBurnt, lettuce, tomato, cheese; // toppings
    BufferedImage trash;
    int x, y;
    private long lastPickupTime = 0;
    private long pickupDelay = 500; // half a second delay
    int grillx = 342;
    int grilly = 75;
    

    public Burger(GamePanel gp, KeyHandler keyH, Player player) {

        this.gp = gp;
        this.keyH = keyH;
        this.player = player;

        burgerList = new ArrayList<String>();
        getImages();

        burgerMap = new HashMap<String, BufferedImage>();
        burgerMap.put("bun", bun);
        burgerMap.put("pattyRaw", pattyRaw);
        burgerMap.put("pattyCooked", pattyCooked);
        burgerMap.put("pattyBurnt", pattyBurnt);
        burgerMap.put("lettuce", lettuce);
        burgerMap.put("tomato", tomato);
        burgerMap.put("cheese", cheese);
        burgerMap.put("trash", trash);

    }

    public void getImages() {

        bun = setup("/res/objects/burger/bottomBun.png");
        pattyRaw = setup("/res/objects/burger/pattyRaw.png");
        pattyCooked = setup("/res/objects/burger/pattyCooked.png");
        pattyBurnt = setup("/res/objects/burger/pattyBurnt.png");
        lettuce = setup("/res/objects/burger/lettuce.png");
        tomato = setup("/res/objects/burger/tomato.png");
        cheese = setup("/res/objects/burger/cheese.png");
        trash = setup("/res/npc/trash.png");

    }

    public BufferedImage setup(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    

    public void update() {
        
        long currentTime = System.currentTimeMillis();
    
        if (keyH.gKeyPressed == true && currentTime - lastPickupTime > pickupDelay && player.station != null) {

            switch (player.station) {
            case "pattyRaw":
                burgerList.add("pattyRaw");
                player.station = "";
                break; 
           case "bun": 
                burgerList.add("bun");
                player.station = "";
                break; 
           case "lettuce": 
                burgerList.add("lettuce");
                player.station = "";
                break; 
           case "tomato": 
                burgerList.add("tomato");
                player.station = "";
                break; 
            case "cheese": 
                burgerList.add("cheese");
                player.station = "";
                break; 
           case "garbage": 
                for (String topping : burgerList) {
                    gp.restaurant.totalSales -= gp.restaurant.priceMap.get(topping);
                }
                burgerList.clear();
                player.station = "";
                break; 
           case "grill": 
                if (burgerList.contains("pattyRaw") && gp.grill.grillStack.size() < MAX_BURGERS_GRILL) {
                    burgerList.remove("pattyRaw");
                    gp.grill.grillStack.put(currentTime, "pattyRawGrill");
                }
                else if (gp.grill.grillStack.size() > 0) {
                    long minKey = Collections.min(gp.grill.grillStack.keySet());
                    if (gp.grill.grillStack.get(minKey) == "pattyBurntGrill") {
                        gp.grill.grillStack.remove(minKey);
                        burgerList.add("pattyBurnt");
                    }
                    else if (gp.grill.grillStack.get(minKey) == "pattyCookedGrill") {
                        gp.grill.grillStack.remove(minKey);
                        burgerList.add("pattyCooked");
                    }
                    else if (gp.grill.grillStack.get(minKey) == "pattyRawGrill") {
                        gp.grill.grillStack.remove(minKey);
                        burgerList.add("pattyRaw");
                    }
                }
                break; 
            }
            lastPickupTime = currentTime;
        }
    }

    public void draw(Graphics2D g2) {
        y = -30;
        for (String topping : burgerList) {
            switch (player.direction) {
            case "up": 
                break;
            case "down":
                g2.drawImage(burgerMap.get(topping), player.x + 18, player.y - y, null);
                break;
            case "left": 
                g2.drawImage(burgerMap.get(topping), player.x + 4, player.y - y, null);
                break;
            case "right": 
                g2.drawImage(burgerMap.get(topping), player.x + 32, player.y - y, null);
                break;
            }
            y += 2;
        }

    }
    
}
