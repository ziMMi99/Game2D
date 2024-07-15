package org.zimmi.tile;

import org.zimmi.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

//A Class that manages all tiles on the screen
public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        tile = new Tile[10];
        //You can create Maps from a 2 dimensional array fx:
        // 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3
        // 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3
        // 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3
        // 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3
        // 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3
        // 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3
        // 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3
        // 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3
        // 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3
        // 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3
        // 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3
        // 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3
        //
        // This is the map i am going to load, but it is just the tile number, this means i can use a while loop to just draw the tile associated with the tile number on the screen
        // The map is 16x12 tiles which are 48x48 pixels.
        mapTileNum = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];

        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage() {
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass00.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water00.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapFilePath) {
        try {
            //Get the map txt files
            InputStream inputStream = getClass().getResourceAsStream(mapFilePath);
            //Read the content of the textfile and store it in a buffered reader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
                //Get the line of numbers from the map.txt (As shown at the top) and store it as an array.
                String line = bufferedReader.readLine();

                while(col < gamePanel.maxScreenCol) {
                    //Split the String line that into and string array instead so each number is seperated
                    String[] numbers = line.split(" ");
                    //And then convert into integers, which can be used as an acutal number
                    int num = Integer.parseInt(numbers[col]);

                    //Start adding the retrieved number from the map.txt to the mapTileNum 2d array, so it can be used to draw the tiles
                    mapTileNum[col][row] = num;
                    //Go to the next tile, so it can be read.
                    col++;
                }
                //If col reaches the max column size of the screen (16 tiles)
                //Go back to col 0, and go 1 row down
                if (col == gamePanel.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2D) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {

            //Extract the tileNum that we are currently on. It gets predifined as col = 0, and row = 0, above.
            int tileNum = mapTileNum[col][row];
            //tileNum is now gonna work as an index for the tile array which stores the images of the tiles, and which index contains what tile image
            g2D.drawImage(tile[tileNum].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            col++;
            x += gamePanel.tileSize;

            if (col == gamePanel.maxScreenCol) {
                col = 0;
                x = 0;
                y += gamePanel.tileSize;
                row++;
            }
        }
    }
}
