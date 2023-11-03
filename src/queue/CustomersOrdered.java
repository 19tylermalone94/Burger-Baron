package queue;

import main.GamePanel;
import entity.Customer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.Graphics2D;

public class CustomersOrdered {

    GamePanel gp;
    public ArrayList<Customer> queue;
    Random rand;
    int litterOdds = 1000;
    long lastLitterTime = 1000000000;
    long littlerDelay = 60000;
    public int doorx;
    public int doory;
    

    public CustomersOrdered(GamePanel gp) {
        this.gp = gp;
        queue = new ArrayList<Customer>();
        rand = new Random();
        doorx = gp.restaurant.exitLocation[0];
        doory = gp.restaurant.exitLocation[1] - gp.tileSize;


    }

    public void update() {
        Iterator<Customer> itr = queue.iterator();
            while (itr.hasNext()) {
                
                Customer customer = itr.next();
                if (customer.readyToLeave == true) {
                    itr.remove();
                }
                else if (customer.hasFood == false) {
                    customer.foodReady = false;
                    for (ArrayList<String> hotFood : gp.foodPickup.queue) {
                        if (hotFood.equals(customer.order)) {
                            customer.foodReady = true;
                            customer.pickUpOrder();
                            break;
                        }
                    }
                    if (customer.foodReady == false) {
                        customer.walkRandomly();
                    }
                }
                else {
                    customer.move(doorx, doory);
                    if (customer.x == doorx && customer.y == doory) {
                        customer.readyToLeave = true;
                    }
                }
            }
        }

    public void draw(Graphics2D g2) {
        if (!queue.isEmpty()) {
            for (Customer customer : queue) {
                if (customer.hasFood && customer.direction == "right") {
                    g2.drawImage(gp.foodPickup.sack, customer.x + 16, customer.y + 12, gp.tileSize, gp.tileSize, null);
                }
                customer.draw(g2);
                if (customer.hasFood && customer.direction == "down") {
                    g2.drawImage(gp.foodPickup.sack, customer.x + 24, customer.y + 16, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }
}
