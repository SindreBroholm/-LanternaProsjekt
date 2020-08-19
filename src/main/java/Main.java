import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal = defaultTerminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        boolean continueReadingInput = true;

        Map map = new Map();
        Player player = new Player();


        int playerX = 1;
        int playerY = 1;
        int zombieY = ThreadLocalRandom.current().nextInt(1, map.width()- 1);
        int zombieX = ThreadLocalRandom.current().nextInt(1,  map.height()- 1);


        final char zombie = '\u2620';
        terminal.setCursorPosition(playerX, playerY);
        player.printPlayer(terminal);
        terminal.flush();
        terminal.setCursorPosition(zombieX, zombieY);
        terminal.putCharacter(zombie);


        int p = ThreadLocalRandom.current().nextInt(1, map.height() - 1);
        int l = ThreadLocalRandom.current().nextInt(1, map.width() - 1);

        map.putDiamondOnMap(p, l);

        map.printMap(terminal);
        terminal.flush();

        do {
            final char removePlayer = ' ';
            KeyStroke keyStroke = null;
            do {
                Thread.sleep(5); // might throw InterruptedException
                keyStroke = terminal.pollInput();
            } while (keyStroke == null);


            // Quit game
            KeyType type = keyStroke.getKeyType();
            Character c = keyStroke.getCharacter(); // used Character, not char because it might be null
            if (c == Character.valueOf('q')) {
                continueReadingInput = false;
                System.out.println("quit");
                terminal.close();
            } else {
                System.out.println(c);

                terminal.setCursorPosition(playerX, playerY);
                terminal.putCharacter(removePlayer);
                terminal.setCursorPosition(zombieX, zombieY);
                terminal.putCharacter(removePlayer);


                int lastX = playerX;
                int lastZombieX = zombieX;
                int lastY = playerY;
                int lastZombieY = zombieY;

                // Move X and Zombie around
                switch (type) {
                    case ArrowUp:
                        playerY--;
                        zombieY--;
                        break;
                    case ArrowDown:
                        playerY++;
                        zombieY++;
                        break;
                    case ArrowLeft:
                        playerX--;
                        zombieX--;
                        break;
                    case ArrowRight:
                        playerX++;
                        zombieX++;
                        break;
                }
                System.out.println("X" + zombieX);


                if (map.isLegalMove(zombieX, zombieY)) {
                    terminal.setCursorPosition(zombieX, zombieY);
                    terminal.putCharacter(zombie);
                    terminal.flush();
                }else {
                    terminal.setCursorPosition(lastZombieX, lastZombieY);
                    zombieX = lastZombieX;
                    zombieY = lastZombieY;
                    terminal.putCharacter(zombie);
                    terminal.flush();
                }

                terminal.setCursorPosition(playerX, playerY);
                if (map.isLegalMove(playerX, playerY)) {
                    player.printPlayer(terminal);
                    terminal.flush();
                } else if (map.isDiamond(playerX, playerY)) {
                    System.out.println("You won!");
                    terminal.clearScreen();
                    String win = "WINNER";
                    char[] won = win.toCharArray();
                    for (int i = 0; i < won.length; i++) {
                        terminal.setCursorPosition(i + 13, 10);
                        terminal.putCharacter(won[i]);
                        terminal.flush();
                        continueReadingInput = false;
                    }
                }else {
                    terminal.setCursorPosition(lastX, lastY);
                    playerX = lastX;
                    playerY = lastY;
                    player.printPlayer(terminal);
                    terminal.flush();
                }

                if (playerX == zombieX && playerY == zombieY) {
                    System.out.println("ZOMBIE EAT YOUR BRAIN!!");
                    terminal.clearScreen();
                    String die = "you died";
                    char[] died = die.toCharArray();
                    for (int i = 0; i < died.length; i++) {
                        terminal.setCursorPosition(i + 13, 10);
                        terminal.putCharacter(died[i]);
                    }
                    terminal.flush();
                    continueReadingInput = false;
                }
            }


            } while (continueReadingInput) ;
        }
    }