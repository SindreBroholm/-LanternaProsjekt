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


/*        terminal.setCursorPosition(1, 1);
        terminal.putCharacter('A');
        terminal.flush();*/

        /*for (int column = 4; column < 10; column++) {
            terminal.setCursorPosition(column, 4);
            terminal.putCharacter('X');
        }
        //row = x posisjon, setCursorPosistion første parameter = y posisjon

        for (int row = 4; row < 10; row++) {
            terminal.setCursorPosition(4, row);
            terminal.putCharacter('X');
        }
*/
//        String str = "This is a String printed out in Lanterna by iterating over the characters";
//        for (int i = 0; i < str.length(); i++) {
//            terminal.setCursorPosition(i, 3);
//            terminal.putCharacter(str.charAt(i));
//        }

//        terminal.flush();

        int x = 10;
        int y = 10;
        final char player = 'X';
        terminal.setCursorPosition(x, y);
        terminal.putCharacter(player);
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
                terminal.putCharacter(player);

                terminal.flush();
            }





        } while (continueReadingInput);
    }
}
