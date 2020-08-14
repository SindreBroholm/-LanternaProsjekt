import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal = defaultTerminalFactory.createTerminal();

        terminal.setCursorPosition(1, 1);
        terminal.putCharacter('A');
        terminal.flush();

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
        String str = "This is a String printed out in Lanterna by iterating over the characters";
        for (int i = 0; i < str.length(); i++) {
            terminal.setCursorPosition(i, 3);
            terminal.putCharacter(str.charAt(i));
        }

        terminal.flush();

    }
}
