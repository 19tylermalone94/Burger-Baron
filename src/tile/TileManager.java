package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        tile = new Tile[20];


        getTileImage();
        loadMap();

    }

    public void getTileImage() {
        
        setup(0, "/res/tiles/floor.png", false, "");
        setup(1, "/res/tiles/wall.png", true, "");
        setup(2, "/res/tiles/doorEnter.png", true, "");
        setup(3, "/res/tiles/counter.png", true, "");
        setup(4, "/res/tiles/counterBun.png", true, "bun");
        setup(5, "/res/tiles/counterLettuce.png", true, "lettuce");
        setup(6, "/res/tiles/counterTomato.png", true, "tomato");
        setup(7, "/res/tiles/counterPatty.png", true, "pattyRaw");
        setup(8, "/res/tiles/Grill.png", true, "grill");
        setup(9, "/res/tiles/counterRegister.png", true, "register");
        setup(10, "/res/tiles/garbage.png", true, "garbage");
        setup(11, "/res/tiles/window.png", true, "");
        setup(12, "/res/tiles/counterCheese.png", true, "cheese");
        setup(13, "/res/tiles/pickupCounter.png", true, "pickupCounter");
        setup(14, "/res/tiles/doorExit.png", true, "");
        setup(15, "/res/tiles/floorLobbyCenter.png", false, "");

    }

    public void setup(int index, String imagePath, boolean collision, String station) {

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream((imagePath)));
            tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
            tile[index].station = station;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {

        try {

            InputStream is = getClass().getResourceAsStream("/res/maps/gameMap.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;

                    col += 1;
                }

                if (col == gp.maxScreenCol) {
                    col = 0;
                    row += 1;
                }
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, null);
            col += 1;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row += 1;
                y += gp.tileSize;
            }

        }

    }
    
}
