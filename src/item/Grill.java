package item;

import main.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import main.UtilityTool;

import javax.imageio.ImageIO;

public class Grill {

    HashMap<Long, String> grillStack = new HashMap<Long, String>();
    BufferedImage pattyRawGrill, pattyCookedGrill, pattyBurntGrill;
    GamePanel gp;
    int MAX_BURGERS_GRILL = 9;
    private static final int COOK_TIME = 10000; // 10 seconds
    private static final int BURNT_TIME = 20000; // 20 seconds
    int x;
    int y;
    private int xAdjust = 5;
    private int yAdjust = 28;
    

    public Grill(GamePanel gp) {

        this.gp = gp;

        x = gp.restaurant.grillLocation[0] + xAdjust;
        y = gp.restaurant.grillLocation[1] + yAdjust;

        getImages();

    
    }

    public void getImages() {

        pattyRawGrill = setup("/res/objects/grill/rawPatty.png");
        pattyCookedGrill = setup("/res/objects/grill/cookedPatty.png");
        pattyBurntGrill = setup("/res/objects/grill/burntPatty.png");

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
        Set<Long> keys = grillStack.keySet();
        for (Long key : keys) {
            long timeDiff = currentTime - key;
            if (timeDiff > BURNT_TIME && grillStack.get(key) == "pattyCookedGrill") {
                grillStack.put(key, "pattyBurntGrill");
            } 
            else if (timeDiff > COOK_TIME && grillStack.get(key) == "pattyRawGrill") {
                grillStack.put(key, "pattyCookedGrill");
            }
        }
    }

    public void draw(Graphics2D g2) {
        int yOffset = 0;
        int xOffset = 0;
        Set<Long> keys = grillStack.keySet();
        for (Long key : keys) {
            if (xOffset == 36) {
                xOffset = 0;
                yOffset += 12;
            }
            BufferedImage pattyImage = null;
            switch (grillStack.get(key)) {
            case "pattyRawGrill": pattyImage = pattyRawGrill; break; 
            case "pattyCookedGrill": pattyImage = pattyCookedGrill; break; 
            case "pattyBurntGrill": pattyImage = pattyBurntGrill; break; 

            }
            g2.drawImage(pattyImage, x + xOffset, y - yOffset, null);
            xOffset += 12;
        }
    }
}
