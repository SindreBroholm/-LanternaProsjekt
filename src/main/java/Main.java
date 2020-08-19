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
        Zoombie zoombieOne = new Zoombie();
        // Zombie number 1
        int playerX = 1;
        int playerY = 1;
        zoombieOne.setZoombieY(ThreadLocalRandom.current().nextInt(1, map.width()- 1));
        zoombieOne.setZoombieX(ThreadLocalRandom.current().nextInt(1, map.height()- 1));
        map.putObjectOnMap(zoombieOne.getZoombieChar(), zoombieOne.getZoombieX(), zoombieOne.getZoombieY());
        final char player = 'X';
        //final char zombie = '\u2620';
        terminal.setCursorPosition(playerX, playerY);
        terminal.putCharacter(player);
        terminal.flush();
        terminal.setCursorPosition(zoombieOne.getZoombieX(), zoombieOne.getZoombieY());
        terminal.putCharacter(zoombieOne.getZoombieChar());
        // Diamond and it's position
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
                terminal.setCursorPosition(zoombieOne.getZoombieX(), zoombieOne.getZoombieY());
                terminal.putCharacter(removePlayer);


                int lastX = playerX;
                int lastZombieX = zoombieOne.getZoombieX();
                int lastY = playerY;
                int lastZombieY = zoombieOne.getZoombieY();

                // Move X and Zombie around
                switch (type) {
                    case ArrowUp:
                        playerY--;
                        zoombieOne.setZoombieY(zoombieOne.getZoombieY()-1);
                        break;
                    case ArrowDown:
                        playerY++;
                        zoombieOne.setZoombieY(zoombieOne.getZoombieY()+1);
                        break;
                    case ArrowLeft:
                        playerX--;
                        zoombieOne.setZoombieX(zoombieOne.getZoombieX()-1);
                        break;
                    case ArrowRight:
                        playerX++;
                        zoombieOne.setZoombieX(zoombieOne.getZoombieX()+1);
                        break;
                }
                System.out.println("X:" + zoombieOne.getZoombieX() + "Y:"+ zoombieOne.getZoombieY());
                System.out.println("X:" + playerX + "Y:"+ playerY);



                if (map.isLegalMove(zoombieOne.getZoombieX(), zoombieOne.getZoombieY())) {
                    terminal.setCursorPosition(zoombieOne.getZoombieX(), zoombieOne.getZoombieY());
                    terminal.putCharacter(zoombieOne.getZoombieChar());
                    terminal.flush();
                }else {
                    terminal.setCursorPosition(lastZombieX, lastZombieY);
                    zoombieOne.setZoombieX(lastZombieX);
                    zoombieOne.setZoombieY(lastZombieY);
                    terminal.putCharacter(zoombieOne.getZoombieChar());
                    terminal.flush();
                }

                terminal.setCursorPosition(playerX, playerY);
                if (map.isLegalMove(playerX, playerY)) {
                    terminal.putCharacter(player);
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
                    terminal.putCharacter(player);
                    terminal.flush();
                }

                if (playerX == zoombieOne.getZoombieX() && playerY == zoombieOne.getZoombieY()) {
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