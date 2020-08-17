import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal = defaultTerminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        boolean continueReadingInput = true;




        int x = 10;
        int y = 10;
        final char player = 'X';
        terminal.setCursorPosition(x, y);
        terminal.putCharacter(player);

//        terminal.setCursorPosition(12, 12);
//        terminal.putCharacter('O');

        char [][] map = new char[][]{
                {'O', ' ', 'O'},
                {'O', ' ', 'O'},
                {'O', ' ', 'O'}
        };

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                terminal.setCursorPosition(i, j);
                terminal.putCharacter(map[j][i]);
            }
        }


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

                terminal.setCursorPosition(x,y);
                terminal.putCharacter(removePlayer);

                switch (type){
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
                if(terminal.getCursorPosition().equals('O')){
                    continue;
                }else {
                    terminal.putCharacter(player);
                    terminal.flush();
                }
            }





        } while (continueReadingInput);
    }
}
