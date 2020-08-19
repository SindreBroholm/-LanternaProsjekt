import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Player {
    private final char player = 'X';
    private int playerPosX = 1;
    private int playerPosY = 1;
    private int nextPlayerPosX;
    private int nextPlayerPosY;



    public void initPlayer(Terminal terminal) throws IOException{
        terminal.setCursorPosition(playerPosX, playerPosY);
        terminal.putCharacter(player);
    }

    public void playerMove(Terminal terminal) throws IOException {
        terminal.setCursorPosition(playerPosX, playerPosY);
        terminal.putCharacter(player);
    }

    public void playerMoveUp(Terminal terminal) throws IOException {
        terminal.setCursorPosition(playerPosX, playerPosY + 1);
//        terminal.putCharacter(player);
    }

    public void playerMoveDown(Terminal terminal) throws IOException {
        terminal.setCursorPosition(playerPosX, playerPosY - 1);
//        terminal.putCharacter(player);
    }

    public void playerMoveLeft(Terminal terminal) throws IOException {
        terminal.setCursorPosition(playerPosX - 1, playerPosY);
//        terminal.putCharacter(player);
    }

    public void playerMoveRight(Terminal terminal) throws IOException {
        terminal.setCursorPosition(playerPosX + 1, playerPosY);
//        terminal.putCharacter(player);
    }

}
