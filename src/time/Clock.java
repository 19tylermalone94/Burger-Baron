package time;

import main.GamePanel;
import main.UtilityTool;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;


public class Clock {

    GamePanel gp;

    BufferedImage twlelve, one, two, three, four, five, six, seven, eight, nine, ten, eleven;

    int clockHour; // 1-12
    public int gameHour; // 0-23
    public int gameMinute;
    int timescale;
    long seconds = 0;
    long lastTime;
    HashMap<Integer, BufferedImage> clockFaces;
    int x = 550;
    int y = 0;
    public String gameHourString;
    public String gameMinuteString;


    public Clock(GamePanel gp) {
        this.gp = gp;
        gameHour = 19;
        gameMinute = 50;
        gameHourString = "10";
        gameMinuteString = "50";
        clockHour = 7;
        timescale = 1000; // 2 seconds per minute
        clockFaces = new HashMap<Integer, BufferedImage>();
        getImages();

    }

    public void getImages() {

        twlelve = setup("/res/objects/clock/12oclock.png");
        clockFaces.put(12, twlelve);
        one = setup("/res/objects/clock/1oclock.png");
        clockFaces.put(1, one);
        two = setup("/res/objects/clock/2oclock.png");
        clockFaces.put(2, two);
        three = setup("/res/objects/clock/3oclock.png");
        clockFaces.put(3, three);
        four = setup("/res/objects/clock/4oclock.png");
        clockFaces.put(4, four);
        five = setup("/res/objects/clock/5oclock.png");
        clockFaces.put(5, five);
        six = setup("/res/objects/clock/6oclock.png");
        clockFaces.put(6, six);
        seven = setup("/res/objects/clock/7oclock.png");
        clockFaces.put(7, seven);
        eight = setup("/res/objects/clock/8oclock.png");
        clockFaces.put(8, eight);
        nine = setup("/res/objects/clock/9oclock.png");
        clockFaces.put(9, nine);
        ten = setup("/res/objects/clock/10oclock.png");
        clockFaces.put(10, ten);
        eleven = setup("/res/objects/clock/11oclock.png");
        clockFaces.put(11, eleven);


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

        if (currentTime - lastTime > timescale) {
            if (gameMinute == 59) {
                gameMinute = -1;
                clockHour += 1;
            }
            if (clockHour == 13) {
                clockHour = 1;
            }
            if (gameHour == 24) {
                gameHour = 0;
            }
            gameMinute += 1;
           
            lastTime = currentTime;
        }

    }

    public void draw(Graphics2D g2) {
        g2.drawImage(clockFaces.get(clockHour), x, y, null);
    }
    
}
