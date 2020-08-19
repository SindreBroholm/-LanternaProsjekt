import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Zoombie{
    private char zoombie = 'F';
    private int zoombieX;
    private int zoombieY;


    public int getZoombieX() {
        return zoombieX;
    }

    public void setZoombieX(int zoombieX) {
        this.zoombieX = zoombieX;
    }

    public int getZoombieY() {
        return zoombieY;
    }

    public void setZoombieY(int zoombieY) {
        this.zoombieY = zoombieY;
    }

    public char getZoombieChar(){
        return zoombie;
    }

    public Position nextPosition(KeyType keyType) {
        int nextZoombieX = zoombieX;
        int nextZoombieY = zoombieY;

        // Move X and Zombie around
        switch (keyType) {
            case ArrowUp:
                nextZoombieY--;
                break;
            case ArrowDown:
                nextZoombieY++;
                break;
            case ArrowLeft:
                nextZoombieX--;
                break;
            case ArrowRight:
                nextZoombieX++;
                break;
        }
        return new Position(nextZoombieX, nextZoombieY);
    }

    public void updateZoombiePosision(Terminal terminal, int nextZoombieX, int nextZombieY) throws IOException {
        terminal.setCursorPosition(zoombieX, zoombieY);
        terminal.putCharacter(' ');
        zoombieX = nextZoombieX;
        zoombieY = nextZombieY;
        terminal.setCursorPosition(zoombieX, zoombieY);
        terminal.putCharacter(zoombie);
    }
}
