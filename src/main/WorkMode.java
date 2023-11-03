package main;


import entity.Player;
import item.Burger;
import item.FloorItems;
import item.Grill;
import item.Register;
import queue.CustomersOrdered;
import queue.FoodPickup;
import queue.RegisterLine;
import tile.TileManager;
import time.Clock;
import java.awt.Graphics2D;


public class WorkMode {

    public GamePanel gp;
    KeyHandler keyH;
    public final  TileManager tileM;
    public TileChecker tChecker;
    public Restaurant restaurant;
    public Player player;
    public Burger burger;
    public Grill grill;
    public Register register;
    public RegisterLine registerLine;
    public CustomersOrdered customersOrdered;
    public FoodPickup foodPickup;
    public Clock clock;
    public FloorItems floorItems;


    public WorkMode(GamePanel gp) {

        this.gp = gp;
        keyH = gp.keyH;

        tileM = new TileManager(gp);
        tChecker = new TileChecker(gp);
        restaurant = new Restaurant(gp);
        player = new Player(gp, keyH);
        burger = new Burger(gp, keyH, player);
        grill = new Grill(gp);
        register = new Register(gp, keyH);
        registerLine = new RegisterLine(gp);
        customersOrdered = new CustomersOrdered(gp);
        foodPickup = new FoodPickup(gp);
        clock = new Clock(gp);
        floorItems = new FloorItems(gp, keyH);

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
    }

    public void draw(Graphics2D g2) {

        tileM.draw(g2);

        floorItems.draw(g2);

        grill.draw(g2);

        player.draw(g2);

        burger.draw(g2);

        register.draw(g2);

        customersOrdered.draw(g2);

        foodPickup.draw(g2);

        clock.draw(g2);

    }
    
}
