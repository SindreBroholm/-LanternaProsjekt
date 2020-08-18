import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal = defaultTerminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        boolean continueReadingInput = true;
        Random random = new Random();


        int x = 1;
        int y = 1;
        final char player = 'X';
        final char zombie = 'Q';
        terminal.setCursorPosition(x, y);
        terminal.putCharacter(player);

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
        char[][] map1 = new char[][]{
                {'x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x'},
                {'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
                {'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
                {'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
                {'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
                {'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
                {'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
                {'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
                {'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
                {'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
                {'x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x'}
        };




        for (int i = 0; i < map1.length; i++) {
            for (int j = 0; j < map1[i].length; j++) {
                terminal.setCursorPosition(i,j);
                terminal.putCharacter(map1[i][j]);
            }
        }
        terminal.flush();

        char[][] bomb = new char[size.getColumns()][size.getRows()];
        int p = random.nextInt(80);
        int l = random.nextInt(24);
        bomb[p][l] = 'O';
        terminal.setCursorPosition(p,l);
        terminal.putCharacter(bomb[p][l]);
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

                int lastX = x;
                int lastY = y;

                switch (type) {
                    case ArrowUp:
                        y--;
                        break;
                    case ArrowDown:
                        y++;
                        break;
                    case ArrowLeft:
                        x--;
                        break;
                    case ArrowRight:
                        x++;
                        break;
                }
                terminal.setCursorPosition(x, y);
                if (x >= 0 && x < map1.length && y>=0 && y<map1[0].length && map1[x][y] == ' ') {
                    terminal.putCharacter(player);
                    terminal.flush();
                } else {
                    terminal.setCursorPosition(lastX, lastY);
                    x = lastX;
                    y = lastY;
                    terminal.putCharacter(player);
                    terminal.flush();
                }
                if(bomb[x][y] == map1[x][y]){
                    int g = x + 1;
                    bomb[x][y] = bomb[g][y];
                } else if(bomb[x][y] == 'O'){
                    System.out.println("BOMB EXPLODED HAHAHAHAHAHHAHA!!!!!");
                    terminal.clearScreen();
                    String die = "you died";
                    char[] died = die.toCharArray();
                    for (int i = 0; i < died.length; i++) {
                        terminal.setCursorPosition(i,20);
                        terminal.putCharacter(died[i]);
                    }
                    terminal.flush();
                    continueReadingInput = false;
                }
            }
        } while (continueReadingInput);
    }
}


