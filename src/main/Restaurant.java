package main;

import java.util.Arrays;
import java.util.Random;
import dialogue.Reviews;

import java.util.HashMap;

public class Restaurant {

    GamePanel gp;
    TileLocator tileLocator;
    Reviews reviews;

    public HashMap<String, Double> priceMap;
    public double totalRating = 5.00;
    public int[] grillLocation;
    public int[] registerLocation;
    public int[] pickupCounterLocation;
    public int[] entranceLocation;
    public int[] exitLocation;
    public int[] waitingAreaLocation;
    long lastReviewTime;
    long reviewDelay = 30000;
    String latestReview = "For the love of god, do not eat these borgers";
    Random rand = new Random();
    public double totalSales = 0.00;


    public Restaurant(GamePanel gp) {

        priceMap = new HashMap<String, Double>() {
            {
                put("bun", 0.25);
                put("pattyCooked", 2.00);
                put("pattyRaw", 2.00);
                put("pattyBurnt", 2.00);
                put("lettuce", 0.25);
                put("tomato", 0.25);
                put("cheese", 0.35);
                put("trash", 0.0);
            }
        };
        reviews = new Reviews();
        tileLocator = new TileLocator();
        this.gp = gp;

        findPositions();

        System.out.println(Arrays.toString(exitLocation));

    }

    public void findPositions() {
        grillLocation = tileLocator.locateTile(8, gp.tileM.mapTileNum, gp.tileSize);
        registerLocation = tileLocator.locateTile(9, gp.tileM.mapTileNum, gp.tileSize);
        pickupCounterLocation = tileLocator.locateTile(13, gp.tileM.mapTileNum, gp.tileSize);
        entranceLocation = tileLocator.locateTile(2, gp.tileM.mapTileNum, gp.tileSize);
        exitLocation = tileLocator.locateTile(14, gp.tileM.mapTileNum, gp.tileSize);
        waitingAreaLocation = tileLocator.locateTile(15, gp.tileM.mapTileNum, gp.tileSize);

    }

    public void getLatestReview() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastReviewTime > reviewDelay) {
            int randomIndex = rand.nextInt(10);
            if (totalRating < 3) {
                latestReview = reviews.lowReviews[randomIndex];
            }
            else if (totalRating < 6) {
                latestReview = reviews.mediumLowReviews[randomIndex];
            }
            else if (totalRating < 8) {
                latestReview = reviews.mediumHighReviews[randomIndex];
            }
            else {
                latestReview = reviews.highReviews[randomIndex];
            }
            lastReviewTime = currentTime;
        }
    }
    
}
