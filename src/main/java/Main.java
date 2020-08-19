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

        //oppretter en classe som overskriver terminalen. Og som innheolder metoder.
        Map map = new Map();

        //Lager en zombie som prøver å ta spilleren.
        Zombie zombieOne = new Zombie(ThreadLocalRandom.current().nextInt(1, map.height() - 1), ThreadLocalRandom.current().nextInt(1, map.width() - 1));
        map.putObjectOnMap(zombieOne.getZombieChar(), zombieOne.getZombieX(), zombieOne.getZombieY());

        Zombie zombieTwo = new Zombie(ThreadLocalRandom.current().nextInt(1, map.height() - 1), ThreadLocalRandom.current().nextInt(1, map.width() - 1));
        map.putObjectOnMap(zombieTwo.getZombieChar(), zombieTwo.getZombieX(), zombieTwo.getZombieY());

        //Oppretter en spiller som skal få tak i en diamant.
        Player playerOne = new Player(1, 1);

        //setter vi en diamant(win condition).
        map.putDiamondOnMap(ThreadLocalRandom.current().nextInt(1, map.height() - 1), ThreadLocalRandom.current().nextInt(1, map.width() - 1));

        //Skriver map + zombie1 + zombie2 + player til terminal
        map.printMap(terminal);
        terminal.setCursorPosition(zombieOne.getZombieX(), zombieOne.getZombieY());
        terminal.putCharacter(zombieOne.getZombieChar());
        terminal.setCursorPosition(zombieTwo.getZombieX(), zombieTwo.getZombieY());
        terminal.putCharacter(zombieTwo.getZombieChar());
        playerOne.updatePlayerPosition(terminal, 1, 1);
        terminal.flush();

        do {
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
                //oppdaterer posisjon på alle brikkene.
                Position nextPlayerPos = playerOne.nextPosition(type);
                Position nextZombiePos = zombieOne.nextPosition(playerOne);
                Position nextZombieTwoPos = zombieTwo.nextPosition(playerOne);

                int nextX = nextPlayerPos.x;
                int nextY = nextPlayerPos.y;
                int nextZombieX = nextZombiePos.x;
                int nextZombieY = nextZombiePos.y;
                int nextZombieTwoX = nextZombieTwoPos.x;
                int nextZombieTwoY = nextZombieTwoPos.y;

                //Sjekker om spiller når diamanten og vinner eller om spiller gjør et lovlig trekk.
                if (map.isDiamond(nextPlayerPos.x, nextPlayerPos.y)) {
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
                } else if (map.isLegalMove(nextPlayerPos.x, nextPlayerPos.y)) {
                    playerOne.updatePlayerPosition(terminal, nextX, nextY);
                    terminal.flush();
                }

                //Sjekker om zoombie'en kan flytte posisjon.
                if (map.isLegalMove(nextZombiePos.x, nextZombiePos.y)) {
                    zombieOne.updateZombiePosition(terminal, nextZombieX, nextZombieY);
                    terminal.flush();
                }
                if (map.isLegalMove(nextZombieTwoPos.x, nextZombieTwoPos.y)) {
                    zombieTwo.updateZombiePosition(terminal, nextZombieTwoX, nextZombieTwoY);
                    terminal.flush();
                }

                //Ser om zoombie fanger spiller !
                if (playerOne.getPlayerX() == zombieOne.getZombieX() && playerOne.getPlayerY() == zombieOne.getZombieY()
                        || playerOne.getPlayerX() == zombieTwo.getZombieX() && playerOne.getPlayerY() == zombieTwo.getZombieY()) {
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
        } while (continueReadingInput);
    }
}