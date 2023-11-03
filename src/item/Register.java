package item;

import main.GamePanel;
import main.KeyHandler;
import entity.Customer;
import java.awt.Graphics2D;
import java.util.Iterator;

public class Register {

    public boolean hasCustomer = false;
    KeyHandler keyH;
    GamePanel gp;
    public boolean[] positionsOccupied = {false, false, false, false, false, false, false};
    public int x;
    public int y;
    int xAdjust;
    int yAdjust = 24;


    public Register(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        x = gp.restaurant.registerLocation[0] + xAdjust;
        y = gp.restaurant.registerLocation[1] + yAdjust;

    }

    public void update() {
        gp.registerLine.addCustomers();

        if (!gp.registerLine.queue.isEmpty()) {
            Iterator<Customer> itr = gp.registerLine.queue.iterator();
            while (itr.hasNext()) {
                Customer customer = itr.next();
                if (gp.clock.gameHour >= 21 || gp.clock.gameHour < 11) {
                    if (customer.readyToLeave == true) {
                        itr.remove();
                    }
                    customer.move(gp.customersOrdered.doorx, gp.customersOrdered.doory);
                    if (customer.x == gp.customersOrdered.doorx && customer.y == gp.customersOrdered.doory) {
                        customer.readyToLeave = true;
                    }
                }
                else {
                    if (positionsOccupied[customer.currPos + 1] == false) {
                        customer.nextPosition();
                    }
                    if (customer.atRegister && gp.player.station == "register" && gp.keyH.gKeyPressed) {
                        gp.customersOrdered.queue.add(customer);
                        gp.registerLine.queue.remove(customer);
                        positionsOccupied[customer.currPos] = false;
                        break;
                    }
                }
            }
        }
    }    

    public void draw(Graphics2D g2) {

        if (!gp.registerLine.queue.isEmpty()) {
            for (Customer customer : gp.registerLine.queue) {
                customer.draw(g2);
            }
        }
    }
    
}
