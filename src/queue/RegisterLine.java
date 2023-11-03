package queue;

import entity.Customer;
import java.util.ArrayList;
import main.GamePanel;

public class RegisterLine {

    GamePanel gp;
    public ArrayList<Customer> queue;
    private long lastCustomerIn = 0;
    private long customerDelay = 12000; // ten second delay

    public int[][] linePositions = {{384, 480}, {484, 480}, {576, 480}, {576, 400}, {576, 300}, {0, 0}, {300, 216}};

    public RegisterLine(GamePanel gp) {

        this.gp = gp;
        queue = new ArrayList<Customer>();

        linePositions[5][0] = gp.register.x;
        linePositions[5][1] = gp.register.y;

    }

    public void addCustomers() {
        long currentTime = System.currentTimeMillis();

        if (gp.customersOrdered.queue.size() < 5 && queue.size() < 5 && currentTime - lastCustomerIn > customerDelay && (gp.clock.gameHour < 21 && gp.clock.gameHour >= 11)) {
            Customer customer = new Customer(gp);
            queue.add(customer);
            customer.nextPosition();

            lastCustomerIn = currentTime;
        }

    }
    
}
