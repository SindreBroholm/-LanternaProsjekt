import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.ArrowUp;

public class Player {
    private char player = 'X';
    private int playerX;
    private int playerY;

    public Player(int playerX, int playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
    }

    public int getPlayerX() {
        return playerX;
    }


    public int getPlayerY() {
        return playerY;
    }


    public Position nextPosition(KeyType keyType) {
        int nextX = playerX;
        int nextY = playerY;

        // Move X and Zombie around
        switch (keyType) {
            case ArrowUp:
                nextY--;
                break;
            case ArrowDown:
                nextY++;
                break;
            case ArrowLeft:
                nextX--;
                break;
            case ArrowRight:
                nextX++;
                break;
        }
        return new Position(nextX, nextY);
    }


    public void updatePlayerPosision(Terminal terminal, int nextX, int nextY) throws IOException {
        terminal.setCursorPosition(playerX, playerY);
        terminal.putCharacter(' ');
        playerX = nextX;
        playerY = nextY;
        terminal.setCursorPosition(playerX, playerY);
        terminal.putCharacter(player);
    }
}
