package main;

public class TileLocator {


    // for use with distinct tiles only, as it returns the first instance
    public int[] locateTile(int tileNum, int[][] numberMap, int tileSize) {

        int[] tilePos = new int[2];

        boolean stopFlag = false;
        for (int i = 0; i < numberMap.length; i++) {
            for (int j = 0; j < numberMap[i].length; j++) {
                if (numberMap[i][j] == tileNum) {
                    tilePos[0] = i * tileSize;
                    tilePos[1] = j * tileSize;
                    stopFlag = true;
                    break; 
                }
                if (stopFlag == true) {
                    break;
                }
            }
        }

        return tilePos;

    }
    
}
