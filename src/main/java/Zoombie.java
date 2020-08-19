import java.time.ZonedDateTime;
import java.util.concurrent.ThreadLocalRandom;

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

}
