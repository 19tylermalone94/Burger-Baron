package main;

import entity.Entity;

public class TileChecker {

    GamePanel gp;

    public TileChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        // finds the tile number in front of the player
        
        int entitySolidLeft = entity.x + entity.solidArea.x;
        int entitySolidRight = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entitySolidTop = entity.y + entity.solidArea.y;
        int entitySolidBottom = entity.y + entity.solidArea.y + entity.solidArea.height;
        int entityLeftCol = entitySolidLeft / gp.tileSize;
        int entityRightCol = entitySolidRight/ gp.tileSize;
        int entityTopRow = entitySolidTop / gp.tileSize;
        int entityBottomRow = entitySolidBottom / gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
        case "up":
            entityTopRow = (entitySolidTop - entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
            if (gp.tileM.tile[tileNum1].station != "" || gp.tileM.tile[tileNum2].station != "") {
                if (gp.tileM.tile[tileNum1].station != "") {
                    entity.station = gp.tileM.tile[tileNum1].station;
                }
                else if (gp.tileM.tile[tileNum2].station != "") {
                    entity.station = gp.tileM.tile[tileNum2].station;
                }
            }
            break;
        case "down":
            entityBottomRow = (entitySolidBottom + entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
            if (gp.tileM.tile[tileNum1].station != "" || gp.tileM.tile[tileNum2].station != "") {
                if (gp.tileM.tile[tileNum1].station != "") {
                    entity.station = gp.tileM.tile[tileNum1].station;
                }
                else if (gp.tileM.tile[tileNum2].station != "") {
                    entity.station = gp.tileM.tile[tileNum2].station;
                }
            }
            break;
        case "left":
            entityLeftCol = (entitySolidLeft - entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
            if (gp.tileM.tile[tileNum1].station != "" || gp.tileM.tile[tileNum2].station != "") {
                if (gp.tileM.tile[tileNum1].station != "") {
                    entity.station = gp.tileM.tile[tileNum1].station;
                }
                else if (gp.tileM.tile[tileNum2].station != "") {
                    entity.station = gp.tileM.tile[tileNum2].station;
                }
            }
            break;
        case "right":
            entityRightCol = (entitySolidRight + entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
            if (gp.tileM.tile[tileNum1].station != "" || gp.tileM.tile[tileNum2].station != "") {
                if (gp.tileM.tile[tileNum1].station != "") {
                    entity.station = gp.tileM.tile[tileNum1].station;
                }
                else if (gp.tileM.tile[tileNum2].station != "") {
                    entity.station = gp.tileM.tile[tileNum2].station;
                }
            }
            break;
        }


    }    
}
