package entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import javax.imageio.ImageIO;

import java .awt.image.BufferedImage;

public class NPCGenerator {

    private String saveDir = "/home/tyler/Projects/Burger Baron/bin/res/npc/";
    private String spriteTempDir = "/res/spriteTemps/";

    int[][] upSpriteArray;
    int[][] downSpriteArray;
    int[][] sideSpriteArray1;
    int[][] sideSpriteArray2;

    HashMap<Integer, Integer> colorMap;

    int hairColor;
    int skinColor;
    int eyeColor;
    int shirtColor;
    int pantsColor;
    int shoeColor;

    int width = 16;
    int height = 16;

    BufferedImage up1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    BufferedImage up2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    BufferedImage down1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    BufferedImage down2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    BufferedImage left1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    BufferedImage left2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    BufferedImage right1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    BufferedImage right2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    
    File f = null;
    
    
    
    public NPCGenerator() {

        refreshColors();
    
        upSpriteArray = new int[width][height];
        upSpriteArray = loadSpriteTemplate("up");
        downSpriteArray = new int[width][height];
        downSpriteArray = loadSpriteTemplate("down");
        sideSpriteArray1 = new int[width][height];
        sideSpriteArray1 = loadSpriteTemplate("side1");
        sideSpriteArray2 = new int[width][height];
        sideSpriteArray2 = loadSpriteTemplate("side2");

        generateSprites();
        

    }

    public void generateSprites() {

        for (int i = 1; i < 6; i++) {

            refreshColors();

            hairColor = makeRandomColor();
            skinColor = makeRandomColor();
            eyeColor = makeRandomColor();
            shirtColor = makeRandomColor();
            pantsColor = makeRandomColor();
            shoeColor = makeRandomColor();

            colorMap.put(1, hairColor);
            colorMap.put(2, skinColor);
            colorMap.put(3, eyeColor);
            colorMap.put(4, shirtColor);
            colorMap.put(5, pantsColor);
            colorMap.put(6, shoeColor);
            colorMap.put(0, (1<<24) | (0<<16) | (0<<8) | 0);

            drawSprite(upSpriteArray, up1);
            writeFile("rand" + i + "Up1", up1);

            writeFile("rand" + i + "Up2", flip(up1));

            drawSprite(downSpriteArray, down1);
            writeFile("rand" + i + "Down1", down1);

            writeFile("rand" + i + "Down2", flip(down1));

            drawSprite(sideSpriteArray1, left1);
            writeFile("rand" + i + "Left1", left1);

            drawSprite(sideSpriteArray2, left2);
            writeFile("rand" + i + "Left2", left2);

            writeFile("rand" + i + "Right1", flip(left1));

            writeFile("rand" + i + "Right2", flip(left2));

        }

    }

    public int makeRandomColor() {
        int a = 255; //alpha
        int r = (int)(Math.random()*256); //red
        int g = (int)(Math.random()*256); //green
        int b = (int)(Math.random()*256); //blue
        return (a<<24) | (r<<16) | (g<<8) | b; //pixel
    }

    public void refreshColors() {

        hairColor = makeRandomColor();
        skinColor = makeRandomColor();
        eyeColor = makeRandomColor();
        shirtColor = makeRandomColor();
        pantsColor = makeRandomColor();
        shoeColor = makeRandomColor();

        colorMap = new HashMap<Integer, Integer>() {
            {
                put(1, hairColor);
                put(2, skinColor);
                put(3, eyeColor);
                put(4, shirtColor);
                put(5, pantsColor);
                put(6, shoeColor);
                put(0, (1<<24) | (0<<16) | (0<<8) | 0); // transparent
            }
        };

    }

    public int[][] loadSpriteTemplate(String direction) {

        int[][] array = new int[width][height];

        try {

            InputStream is = getClass().getResourceAsStream(spriteTempDir + direction + "SpriteTemplate.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
            
            while (col < width && row < height) {
                String line = br.readLine();
                
                while (col < width) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    array[col][row] = num;
                    col += 1;
                }
                if (col == width) {
                    col = 0;
                    row += 1;
                }
            }
            
            br.close();
        
        } catch (IOException e) {
            e.printStackTrace();
        }

        return array;

    }

    public void drawSprite(int[][] array, BufferedImage img) {

        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[y].length; x++) {
                img.setRGB(y, x, colorMap.get(array[y][x]));
            }
        }
    }


    public void writeFile(String name, BufferedImage img) {
        try{
            f = new File(saveDir, name + ".png");
            ImageIO.write(img, "png", f);
        }
        catch(IOException e){
                System.out.println("Error: " + e);
        }

    }

    public BufferedImage flip(BufferedImage image) {
        for (int i = 0; i < image.getWidth() / 2; i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int tmp = image.getRGB(i, j);
                image.setRGB(i, j, image.getRGB(image.getWidth() - i - 1, j));
                image.setRGB(image.getWidth() - i - 1, j, tmp);
            }
        }
        return image;
    }

}