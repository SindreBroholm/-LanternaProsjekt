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
        zoombieOne.setZoombieY(ThreadLocalRandom.current().nextInt(1, map.width()- 1));
        zoombieOne.setZoombieX(ThreadLocalRandom.current().nextInt(1, map.height()- 1));
        map.putObjectOnMap(zoombieOne.getZoombieChar(), zoombieOne.getZoombieX(), zoombieOne.getZoombieY());

        Player playerOne = new Player();
        playerOne.setPlayerY(1);
        playerOne.setPlayerX(1);



        // Diamond and it's position
        int p = ThreadLocalRandom.current().nextInt(1, map.height() - 1);
        int l = ThreadLocalRandom.current().nextInt(1, map.width() - 1);
        map.putDiamondOnMap(p, l);

        map.printMap(terminal);
        terminal.setCursorPosition(zoombieOne.getZoombieX(), zoombieOne.getZoombieY());
        terminal.putCharacter(zoombieOne.getZoombieChar());
        terminal.setCursorPosition(playerOne.getPlayerX(), playerOne.getPlayerY());
        terminal.putCharacter(playerOne.getPlayerChar());
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

                int nextX = playerOne.getPlayerX();
                int nextZoombieX = zoombieOne.getZoombieX();
                int nextY = playerOne.getPlayerY();
                int nextZombieY = zoombieOne.getZoombieY();

                // Move X and Zombie around
                switch (type) {
                    case ArrowUp:
                        nextY--;
                        nextZombieY--;
                        break;
                    case ArrowDown:
                        nextY++;
                        nextZombieY++;
                        break;
                    case ArrowLeft:
                        nextX--;
                        nextZoombieX--;
                        break;
                    case ArrowRight:
                        nextX++;
                        nextZoombieX++;
                        break;
                }

                if (map.isDiamond(nextX, nextY)) {
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
                }else if(map.isLegalMove(nextX, nextY)){
                    terminal.setCursorPosition(playerOne.getPlayerX(), playerOne.getPlayerY());
                    terminal.putCharacter(removePlayer);
                    playerOne.setPlayerX(nextX);
                    playerOne.setPlayerY(nextY);
                    terminal.setCursorPosition(playerOne.getPlayerX(), playerOne.getPlayerY());
                    terminal.putCharacter(playerOne.getPlayerChar());
                    terminal.flush();
                }

                if (map.isLegalMove(nextZoombieX, nextZombieY)) {
                    terminal.setCursorPosition(zoombieOne.getZoombieX(), zoombieOne.getZoombieY());
                    terminal.putCharacter(removePlayer);
                    zoombieOne.setZoombieX(nextZoombieX);
                    zoombieOne.setZoombieY(nextZombieY);
                    terminal.setCursorPosition(zoombieOne.getZoombieX(), zoombieOne.getZoombieY());
                    terminal.putCharacter(zoombieOne.getZoombieChar());
                    terminal.flush();
                }

                if (playerOne.getPlayerX() == zoombieOne.getZoombieX() && playerOne.getPlayerY() == zoombieOne.getZoombieY()) {
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