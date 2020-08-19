import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Zombie {
    private char zombie = 'F';
    private int zombieX;
    private int zombieY;

    public Zombie(int zoombieX, int zoombieY) {
        this.zombieX = zoombieX;
        this.zombieY = zoombieY;
    }

    public int getZombieX() {
        return zombieX;
    }

    public int getZombieY() {
        return zombieY;
    }

    public char getZombieChar(){
        return zombie;
    }

    public Position nextPosition( Player player) {
        int nextZombieX = zombieX;
        int nextZombieY = zombieY;

        if(player.getPlayerX() < zombieX){
            nextZombieX = zombieX - 1;
        }else if (player.getPlayerX() > zombieX){
            nextZombieX = zombieX + 1;
        } else {
            nextZombieX = zombieX;
        }

        if(player.getPlayerY() < zombieY){
            nextZombieY = zombieY - 1;
        } else if (player.getPlayerY() > zombieY){
            nextZombieY = zombieY + 1;
        }else {
            nextZombieY = zombieY;
        }
        return new Position(nextZombieX, nextZombieY);
    }

    public void updateZombiePosition(Terminal terminal, int nextZombieX, int nextZombieY) throws IOException {
        terminal.setCursorPosition(zombieX, zombieY);
        terminal.putCharacter(' ');
        zombieX = nextZombieX;
        zombieY = nextZombieY;
        terminal.setCursorPosition(zombieX, zombieY);
        terminal.putCharacter(zombie);
    }
}
