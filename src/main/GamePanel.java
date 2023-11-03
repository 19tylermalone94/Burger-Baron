package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;
import entity.NPCGenerator;
import entity.Player;
import tile.TileManager;
import item.Burger;
import item.FloorItems;
import item.Grill;
import item.Register;
import queue.CustomersOrdered;
import queue.FoodPickup;
import queue.RegisterLine;
import time.Clock;


public class GamePanel extends JPanel  implements Runnable {

    // screen settings
    final int originalTileSize = 16; // 16 x 16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48 x 48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768px
    public final int screenHeight = tileSize * maxScreenRow; // 576px

    int FPS = 60;


    NPCGenerator sprite = new NPCGenerator();
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public TileChecker tChecker = new TileChecker(this);
    public Restaurant restaurant = new Restaurant(this);
    public Player player = new Player(this, keyH);
    public Burger burger = new Burger(this, keyH, player);
    public Grill grill = new Grill(this);
    public Register register = new Register(this, keyH);
    public RegisterLine registerLine = new RegisterLine(this);
    public CustomersOrdered customersOrdered = new CustomersOrdered(this);
    public FoodPickup foodPickup = new FoodPickup(this);
    public Clock clock = new Clock(this);
    public FloorItems floorItems = new FloorItems(this, keyH);
    MenuPanel menuPanel = new MenuPanel(this);
    public MenuManager menuManager = new MenuManager(this, keyH);

    
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // 0.016666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta -=1;
            }

        }
    }

    public void update() {

        clock.update();

        player.update();

        burger.update();

        grill.update();

        register.update();

        customersOrdered.update();

        foodPickup.update();

        floorItems.update();


        menuManager.update();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;        


        tileM.draw(g2);

        floorItems.draw(g2);

        foodPickup.draw(g2);

        grill.draw(g2);

        player.draw(g2);

        burger.draw(g2);

        register.draw(g2);

        customersOrdered.draw(g2);

        clock.draw(g2);

        menuPanel.draw(g2);
        
        g2.dispose();

    }





}