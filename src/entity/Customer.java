package entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

import main.GamePanel;
import main.UtilityTool;

public class Customer extends Entity {

    public ArrayList<String> order;
    ArrayList<String> toppings = new ArrayList<String>() {
        {
            add("pattyCooked");
            add("lettuce");
            add("tomato");
            add("cheese");
        }
    };
    double orderPrice;
    public int currPos;
    public boolean atRegister = false;
    Random rand = new Random();
    int maxRandDist = 200;
    private long lastWalkTime = 0;
    private long walkDelay;
    int nextX;
    int nextY;
    boolean isMoving = false;
    public boolean hasFood = false;
    public boolean foodReady = false;
    public boolean readyToLeave = false;
    int spriteSpeed;
    int litterOdds = 1000;
    long lastLitterTime;
    long littlerDelay = 60000;
    public int rating = 10;
    long lastRatingDrop;
    long ratingDropDelay = 5000;
   
    


    public Customer(GamePanel gp) {

        this.gp = gp;
        order = new ArrayList<String>();

        solidArea = new Rectangle();
        solidArea.x = 18;
        solidArea.y = 33;
        solidArea.width = 12;
        solidArea.height = 12;
        x = gp.registerLine.linePositions[0][0];
        y = gp.registerLine.linePositions[0][1];
        speed = rand.nextInt(3) + 1;
        spriteSpeed = (int)(40 / speed);
        direction = "up";
        currPos = 0;
        lastLitterTime = System.currentTimeMillis() + 20000;
        walkDelay = rand.nextLong(10000  - 6000) + 6000;

        int randSprite = rand.nextInt(10) + 1;
        switch (randSprite) {
            case 1: getCustomerImage1(); break;
            case 2: getCustomerImage2(); break;
            case 3: getCustomerImage3(); break;
            case 4: getCustomerImage4(); break;
            case 5: getCustomerImage5(); break;
            case 6: getCustomerImage6(); break;
            case 7: getCustomerImage7(); break;
            case 8: getCustomerImage8(); break;
            case 9: getCustomerImage9(); break;
            case 10: getCustomerImage10(); break;

            
        }
        generateOrder();

    }

    public void getCustomerImage1() {

        up1 = setup("/res/npc/CustomerUp1.png");
        up2 = setup("/res/npc/CustomerUp2.png");
        down1 = setup("/res/npc/CustomerDown1.png");
        down2 = setup("/res/npc/CustomerDown2.png");
        left1 = setup("/res/npc/CustomerLeft1.png");
        left2 = setup("/res/npc/CustomerLeft2.png");
        right1 = setup("/res/npc/CustomerRight1.png");
        right2 = setup("/res/npc/CustomerRight2.png");
        

    }

    public void getCustomerImage2() {

        up1 = setup("/res/npc/karenUp1.png");
        up2 = setup("/res/npc/karenUp2.png");
        down1 = setup("/res/npc/karenDown1.png");
        down2 = setup("/res/npc/karenDown2.png");
        left1 = setup("/res/npc/karenLeft1.png");
        left2 = setup("/res/npc/karenLeft2.png");
        right1 = setup("/res/npc/karenRight1.png");
        right2 = setup("/res/npc/karenRight2.png");
        

    }

    public void getCustomerImage3() {

        up1 = setup("/res/npc/jamalUp1.png");
        up2 = setup("/res/npc/jamalUp2.png");
        down1 = setup("/res/npc/jamalDown1.png");
        down2 = setup("/res/npc/jamalDown2.png");
        left1 = setup("/res/npc/jamalLeft1.png");
        left2 = setup("/res/npc/jamalLeft2.png");
        right1 = setup("/res/npc/jamalRight1.png");
        right2 = setup("/res/npc/jamalRight2.png");
        

    }

    public void getCustomerImage4() {

        up1 = setup("/res/npc/mortUp1.png");
        up2 = setup("/res/npc/mortUp2.png");
        down1 = setup("/res/npc/mortDown1.png");
        down2 = setup("/res/npc/mortDown2.png");
        left1 = setup("/res/npc/mortLeft1.png");
        left2 = setup("/res/npc/mortLeft2.png");
        right1 = setup("/res/npc/mortRight1.png");
        right2 = setup("/res/npc/mortRight2.png");
        

    }
    public void getCustomerImage5() {

        up1 = setup("/res/npc/alienUp1.png");
        up2 = setup("/res/npc/alienUp2.png");
        down1 = setup("/res/npc/alienDown1.png");
        down2 = setup("/res/npc/alienDown2.png");
        left1 = setup("/res/npc/alienLeft1.png");
        left2 = setup("/res/npc/alienLeft2.png");
        right1 = setup("/res/npc/alienRight1.png");
        right2 = setup("/res/npc/alienRight2.png");
        

    }

    public void getCustomerImage6() {

        up1 = setup("/res/npc/rand1Up1.png");
        up2 = setup("/res/npc/rand1Up2.png");
        down1 = setup("/res/npc/rand1Down1.png");
        down2 = setup("/res/npc/rand1Down2.png");
        left1 = setup("/res/npc/rand1Left1.png");
        left2 = setup("/res/npc/rand1Left2.png");
        right1 = setup("/res/npc/rand1Right1.png");
        right2 = setup("/res/npc/rand1Right2.png");
        

    }
    public void getCustomerImage7() {

        up1 = setup("/res/npc/rand2Up1.png");
        up2 = setup("/res/npc/rand2Up2.png");
        down1 = setup("/res/npc/rand2Down1.png");
        down2 = setup("/res/npc/rand2Down2.png");
        left1 = setup("/res/npc/rand2Left1.png");
        left2 = setup("/res/npc/rand2Left2.png");
        right1 = setup("/res/npc/rand2Right1.png");
        right2 = setup("/res/npc/rand2Right2.png");
        
        

    }
    public void getCustomerImage8() {

        up1 = setup("/res/npc/rand3Up1.png");
        up2 = setup("/res/npc/rand3Up2.png");
        down1 = setup("/res/npc/rand3Down1.png");
        down2 = setup("/res/npc/rand3Down2.png");
        left1 = setup("/res/npc/rand3Left1.png");
        left2 = setup("/res/npc/rand3Left2.png");
        right1 = setup("/res/npc/rand3Right1.png");
        right2 = setup("/res/npc/rand3Right2.png");
        
        

    }
    public void getCustomerImage9() {

        up1 = setup("/res/npc/rand4Up1.png");
        up2 = setup("/res/npc/rand4Up2.png");
        down1 = setup("/res/npc/rand4Down1.png");
        down2 = setup("/res/npc/rand4Down2.png");
        left1 = setup("/res/npc/rand4Left1.png");
        left2 = setup("/res/npc/rand4Left2.png");
        right1 = setup("/res/npc/rand4Right1.png");
        right2 = setup("/res/npc/rand4Right2.png");
        
        

    }
    public void getCustomerImage10() {

        up1 = setup("/res/npc/rand5Up1.png");
        up2 = setup("/res/npc/rand5Up2.png");
        down1 = setup("/res/npc/rand5Down1.png");
        down2 = setup("/res/npc/rand5Down2.png");
        left1 = setup("/res/npc/rand5Left1.png");
        left2 = setup("/res/npc/rand5Left2.png");
        right1 = setup("/res/npc/rand5Right1.png");
        right2 = setup("/res/npc/rand5Right2.png");
        
        

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

    public ArrayList<String> generateOrder() {
    
        order.add("bun");
        orderPrice += gp.restaurant.priceMap.get("bun");

        int numToppings = rand.nextInt(toppings.size()) + 1;
        ArrayList<String> temptoppings = new ArrayList<>(toppings);
        Collections.shuffle(temptoppings);
        for (int i = 0; i < numToppings; i++) {
            order.add(temptoppings.get(i));
            orderPrice += gp.restaurant.priceMap.get(temptoppings.get(i));

        }
        order.add("bun"); // Every order should start and end with a bun
        orderPrice += gp.restaurant.priceMap.get("bun");
        return order;
    }

    public void move(int xpos, int ypos) {

        // ensures destination is reachable
        xpos-= xpos % speed;
        ypos-= ypos % speed;
        
        if (x < xpos) {
            direction = "right";
        }
        else if (x > xpos) {
            direction = "left";        
        }
        else if (y < ypos) {
            direction = "down";        
        }
        else if (y > ypos) {
            direction = "up";
        }

        collisionOn = false;
        gp.tChecker.checkTile(this);

        if (collisionOn == false) {
        switch (direction) {
            case "right": x += speed; spriteCounter += 1; break;
            case "up": y -= speed; spriteCounter += 1; break;
            case "left": x -= speed; spriteCounter += 1; break;
            case "down": y += speed; spriteCounter += 1; break;
            }
        }

        if ( spriteCounter > spriteSpeed) {
            if (spriteNum == 1) {
                spriteNum = 2;
            }
            else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void pickUpOrder() {
        gp.tChecker.checkTile(this);
        if (station == "pickupCounter") {
            gp.menuManager.burgersSold += 1;
            gp.restaurant.totalSales += orderPrice;
            gp.restaurant.totalRating = ((gp.restaurant.totalRating * (gp.menuManager.burgersSold - 1)) + rating) / gp.menuManager.burgersSold;
            if (gp.restaurant.totalRating < 0) {
                gp.restaurant.totalRating = 0;
            }
            if (gp.restaurant.totalRating > 10) {
                gp.restaurant.totalRating = 10;
            }
            gp.foodPickup.queue.remove(order);
            hasFood = true;
        }
        move(gp.foodPickup.pickupx, gp.foodPickup.pickupy);
    }

    public void nextPosition() {

        int divisibleregx = gp.register.x - (gp.register.x % speed);
        int divisibleregy = gp.register.y - (gp.register.y % speed);
        if (x == divisibleregx && y == divisibleregy) {
            atRegister = true;
        }

        int nextPos = currPos + 1;
        if (nextPos < gp.registerLine.linePositions.length - 1) {

            int divisblexpos = gp.registerLine.linePositions[nextPos][0] - (gp.registerLine.linePositions[nextPos][0] % speed);
            int divisbleypos = gp.registerLine.linePositions[nextPos][1] - (gp.registerLine.linePositions[nextPos][1] % speed);

            move(gp.registerLine.linePositions[nextPos][0], gp.registerLine.linePositions[nextPos][1]);

            if (x == divisblexpos && y == divisbleypos) {
                gp.register.positionsOccupied[currPos] = false;
                currPos += 1;
                gp.register.positionsOccupied[currPos] = true;
            }
        }
    }

    public void walkRandomly() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastWalkTime > walkDelay) {
            int distVariance = rand.nextInt(48);
            int plusorminus = rand.nextInt(2 + 1) - 1;
            nextX = 200 +(plusorminus * distVariance); // center point of waiting area
            nextY = 300 + (plusorminus * distVariance);
            int randDist = rand.nextInt(maxRandDist);
            int directionPicker = rand.nextInt(2)+1;
            int axisPicker = rand.nextInt(2)+1;
            if (axisPicker == 1) {
                if (directionPicker == 1) {
                    nextX += randDist;
                }
                else {
                    nextX -= randDist;
                }
            }
            else {
                if (directionPicker == 1) {
                    nextY += randDist;
                }
                 else {
                    nextY -= randDist;
                 }
            }
            nextX -= nextX % speed; // ensures customer reaches destination
            nextY -= nextY % speed; //
            lastWalkTime = currentTime;
            isMoving = true;
        }

        if (x == nextX && y == nextY) {
            isMoving = false;
        }
        if (isMoving == true) {
            move(nextX, nextY);
        }
        int randomNum = rand.nextInt(litterOdds);
        long currentTime2 = System.currentTimeMillis();
        if (currentTime2 - lastLitterTime - randomNum > littlerDelay) {
            int xpos = x + 24;
            int ypos = y + 48;
            int[] points = {xpos, ypos};
            gp.floorItems.floorItemList.put(points, "trash");
            lastLitterTime = currentTime2;
        }
        long currentTime3 = System.currentTimeMillis();
        if (currentTime3 - lastRatingDrop > ratingDropDelay) {
            rating -= 1;
            lastRatingDrop = currentTime3;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
        case "up":
            if (spriteNum == 1) {
                image = up1;
            }
            if (spriteNum == 2) {
                image = up2;
            }
            break;
        case "down":
            if (spriteNum == 1) {
                image = down1;
            }
            if (spriteNum == 2) {
                image = down2;
            }
            break;
        case "left":
            if (spriteNum == 1) {
                image = left1;
            }
            if (spriteNum == 2) {
                image = left2;
            }
            break;
        case "right":
            if (spriteNum == 1) {
                image = right1;
            }
            if (spriteNum == 2) {
                image = right2;
            }
            break;
        }

        g2.drawImage(image, x, y, null);

    }

}
