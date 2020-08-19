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

        Zoombie zoombieOne = new Zoombie(ThreadLocalRandom.current().nextInt(1, map.height()- 1), ThreadLocalRandom.current().nextInt(1, map.width()- 1));
        map.putObjectOnMap(zoombieOne.getZoombieChar(), zoombieOne.getZoombieX(), zoombieOne.getZoombieY());

        Zoombie zoombieTwo = new Zoombie(ThreadLocalRandom.current().nextInt(1, map.height()- 1), ThreadLocalRandom.current().nextInt(1, map.width()- 1));
        map.putObjectOnMap(zoombieTwo.getZoombieChar(), zoombieTwo.getZoombieX(), zoombieTwo.getZoombieY());



        Player playerOne = new Player(1, 1);
        int p = ThreadLocalRandom.current().nextInt(1, map.height() - 1);
        int l = ThreadLocalRandom.current().nextInt(1, map.width() - 1);
        map.putDiamondOnMap(p, l);

        map.printMap(terminal);
        terminal.setCursorPosition(zoombieOne.getZoombieX(), zoombieOne.getZoombieY());
        terminal.putCharacter(zoombieOne.getZoombieChar());
        terminal.setCursorPosition(zoombieTwo.getZoombieX(), zoombieTwo.getZoombieY());
        terminal.putCharacter(zoombieTwo.getZoombieChar());
        playerOne.updatePlayerPosision(terminal, 1, 1);
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

                Position nextZoombiePos = zoombieOne.nextPosition(playerOne);
                Position nextZoombieTwoPos = zoombieTwo.nextPosition(playerOne);
                Position nextPlayerPos = playerOne.nextPosition(type);

                int nextX = nextPlayerPos.x;
                int nextY = nextPlayerPos.y;
                int nextZoombieX = nextZoombiePos.x;
                int nextZombieY = nextZoombiePos.y;
                int nextZoombieTwoX = nextZoombieTwoPos.x;
                int nextZoombieTwoY = nextZoombieTwoPos.y;

                if (map.isDiamond(nextX, nextY)) {
                    System.out.println("You won!");
                    terminal.clearScreen();
                    String win = "WINNER";
                    char[] won = win.toCharArray();
                    for (int i = 0; i < won.length; i++) {
                        terminal.setCursorPosition(i + 13, 10);
                        terminal.putCharacter(won[i]);
                    }
                    terminal.flush();
                    continueReadingInput = false;
                }else if(map.isLegalMove(nextX, nextY)){
                    playerOne.updatePlayerPosision(terminal, nextX,nextY);
                    terminal.flush();
                }
                if (map.isLegalMove(nextZoombieX, nextZombieY)) {
                    zoombieOne.updateZoombiePosision(terminal, nextZoombieX, nextZombieY);
                    terminal.flush();
                }
                if (map.isLegalMove(nextZoombieTwoX, nextZoombieTwoY)) {
                    zoombieTwo.updateZoombiePosision(terminal, nextZoombieTwoX, nextZoombieTwoY);
                    terminal.flush();
                }

                if (playerOne.getPlayerX() == zoombieOne.getZoombieX() && playerOne.getPlayerY() == zoombieOne.getZoombieY()
                        || playerOne.getPlayerX() == zoombieTwo.getZoombieX() && playerOne.getPlayerY() == zoombieTwo.getZoombieY()) {
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