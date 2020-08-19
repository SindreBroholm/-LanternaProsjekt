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
        char b = '\u2588';

        char[][] map1 = new char[][]{
                {b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b},
                {b, ' ', ' ', b, ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', b, ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', b, ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', b, b, b, b, ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', b, ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', b, ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', b, ' ', b},
                {b, ' ', ' ', b, b, b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', b, ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, b, b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', b, b, b, b, ' ', ' ', b, b, b, b, b, b, b, ' ', ' ', b, b, ' ', ' ', b, b, b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, b, b, b, b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', b, b, b, b, ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, b, b, b, b, b, b, b, ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
                {b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b},
        };

        // Zombie number 1
        int x = 1;
        int y = 1;
        int zombieY = ThreadLocalRandom.current().nextInt(1, map1[1].length - 1);
        int zombieX = ThreadLocalRandom.current().nextInt(1, map1.length - 1);
        final char player = 'X';
        final char zombie = '\u2620';
        terminal.setCursorPosition(x, y);
        terminal.putCharacter(player);
        terminal.flush();
        terminal.setCursorPosition(zombieX, zombieY);
        terminal.putCharacter(zombie);

        for (int i = 0; i < map1.length; i++) {
            for (int j = 0; j < map1[i].length; j++) {
                terminal.setCursorPosition(i, j);
                terminal.putCharacter(map1[i][j]);
            }
        }
        terminal.flush();


        // Diamond and it's position
        int p = ThreadLocalRandom.current().nextInt(1, map1.length - 1);
        int l = ThreadLocalRandom.current().nextInt(1, map1[1].length - 1);
        char diamond = '\u2606';
        map1[p][l] = diamond;
        terminal.setCursorPosition(p, l);
        terminal.putCharacter(map1[p][l]);
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

                terminal.setCursorPosition(x, y);
                terminal.putCharacter(removePlayer);
                terminal.setCursorPosition(zombieX, zombieY);
                terminal.putCharacter(removePlayer);


                int lastX = x;
                int lastZombieX = zombieX;
                int lastY = y;
                int lastZombieY = zombieY;

                // Move X and Zombie around
                switch (type) {
                    case ArrowUp:
                        y--;
                        zombieY--;
                        break;
                    case ArrowDown:
                        y++;
                        zombieY++;
                        break;
                    case ArrowLeft:
                        x--;
                        zombieX--;
                        break;
                    case ArrowRight:
                        x++;
                        zombieX++;
                        break;
                }
                System.out.println("X" + zombieX);


                if (zombieX >= 0 && zombieX < map1.length && zombieY >= 0 && zombieY < map1[0].length && map1[zombieX][zombieY] == ' ') {
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

                terminal.setCursorPosition(x, y);
                if (x >= 0 && x < map1.length && y >= 0 && y < map1[0].length && map1[x][y] == ' ') {
                    terminal.putCharacter(player);
                    terminal.flush();
                } else if (map1[x][y] == diamond) {
                    System.out.println("You won!");
                    terminal.clearScreen();
                    String win = "WINNER";
                    char[] won = win.toCharArray();
                    for (int i = 0; i < won.length; i++) {
                        terminal.setCursorPosition(i + 30, 10);
                        terminal.putCharacter(won[i]);
                        terminal.flush();
                        continueReadingInput = false;
                    }
                }else{
                    terminal.setCursorPosition(lastX, lastY);
                    x = lastX;
                    y = lastY;
                    terminal.putCharacter(player);
                    terminal.flush();
                }

                if (x == zombieX && y == zombieY) {
                    System.out.println("ZOMBIE EAT YOUR BRAIN!!");
                    terminal.clearScreen();
                    String die = "you died";
                    char[] died = die.toCharArray();
                    for (int i = 0; i < died.length; i++) {
                        terminal.setCursorPosition(i + 30, 10);
                        terminal.putCharacter(died[i]);
                    }
                    terminal.flush();
                    continueReadingInput = false;
                }
            }


            } while (continueReadingInput) ;
        }
    }