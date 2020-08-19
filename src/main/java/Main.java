import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal = defaultTerminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        boolean continueReadingInput = true;
        Random random = new Random();


        int x = 1;
        int y = 1;
        int zoombieY = ThreadLocalRandom.current().nextInt(1, 10);
        int zoombieX = ThreadLocalRandom.current().nextInt(1, 23);
        final char player = 'X';
        final char zombie = 'F';
        terminal.setCursorPosition(x, y);
        terminal.putCharacter(player);
        terminal.flush();
        terminal.setCursorPosition(zoombieX, zoombieY);
        terminal.putCharacter(zombie);


        TerminalSize size = terminal.getTerminalSize();
/*        char[][] map = new char[size.getColumns()][size.getRows()];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int bound = random.nextInt(5);
                if (bound == 1) {
                    map[i][j] = '\u2588';
                } else {
                    map[i][j] = ' ';
                }
            }
        }*/

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


        for (int i = 0; i < map1.length; i++) {
            for (int j = 0; j < map1[i].length; j++) {
                terminal.setCursorPosition(i, j);
                terminal.putCharacter(map1[i][j]);
            }
        }
        terminal.flush();


        int p = ThreadLocalRandom.current().nextInt(1, map1.length);
        int l = ThreadLocalRandom.current().nextInt(1, map1[1].length);
        char bomb = 'O';
        map1[p][l] = bomb;
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
                terminal.setCursorPosition(zoombieX, zoombieY);
                terminal.putCharacter(removePlayer);


                int lastX = x;
                int lastZoombieX = zoombieX;
                int lastY = y;
                int lastZoombieY = zoombieY;

                switch (type) {
                    case ArrowUp:
                        y--;
                        zoombieY--;
                        break;
                    case ArrowDown:
                        y++;
                        zoombieY++;
                        break;
                    case ArrowLeft:
                        x--;
                        zoombieX--;
                        break;
                    case ArrowRight:
                        x++;
                        zoombieX++;
                        break;
                }
                System.out.println("X" + zoombieX);
                if (zoombieX >= 0 && zoombieX < map1.length && zoombieY >= 0 && zoombieY < map1[0].length && map1[zoombieX][zoombieY] == ' ') {
                    terminal.setCursorPosition(zoombieX, zoombieY);
                    terminal.putCharacter(zombie);
                    terminal.flush();
                }else {
                    terminal.setCursorPosition(lastZoombieX, lastZoombieY);
                    zoombieX = lastZoombieX;
                    zoombieY = lastZoombieY;
                    terminal.putCharacter(zombie);
                    terminal.flush();
                }


                terminal.setCursorPosition(x, y);
                if (x >= 0 && x < map1.length && y >= 0 && y < map1[0].length && map1[x][y] == ' ') {
                    terminal.putCharacter(player);
                    terminal.flush();
                } else if (map1[x][y] == bomb) {
                    System.out.println("You won !");
                    terminal.clearScreen();
                    String die = "WINNER";
                    char[] died = die.toCharArray();
                    for (int i = 0; i < died.length; i++) {
                        terminal.setCursorPosition(i, 20);
                        terminal.putCharacter(died[i]);
                        terminal.flush();
                        continueReadingInput = false;
                    }
                }
                else{
                    terminal.setCursorPosition(lastX, lastY);
                    x = lastX;
                    y = lastY;
                    terminal.putCharacter(player);
                    terminal.flush();
                }

                if (x == zoombieX && y == zoombieY) {
                    System.out.println("BOMB EXPLODED HAHAHAHAHAHHAHA!!!!!");
                    terminal.clearScreen();
                    String die = "you died";
                    char[] died = die.toCharArray();
                    for (int i = 0; i < died.length; i++) {
                        terminal.setCursorPosition(i, 20);
                        terminal.putCharacter(died[i]);
                    }
                    terminal.flush();
                    continueReadingInput = false;
                }
            }


            } while (continueReadingInput) ;
        }
    }


