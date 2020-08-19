import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Player {
    private char player = 'X';
    private int playerX;
    private int playerY;

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public char getPlayerChar(){
        return player;
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
