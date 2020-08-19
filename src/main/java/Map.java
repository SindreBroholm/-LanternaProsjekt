import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Map {
    char b = '\u2588';
    char diamond = '\u2606';

    public char[][] map1 = new char[][]{
            {b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b},
            {b, ' ', ' ', b, ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
            {b, ' ', ' ', b, ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
            {b, ' ', ' ', b, ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', b, b, b, b, ' ', ' ', ' ', ' ', ' ', b},
            {b, ' ', ' ', b, ' ', ' ', ' ', b, b, ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
            {b, ' ', ' ', b, ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', b, ' ', b},
            {b, ' ', b, b, b, b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', b, ' ', b},
            {b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', b},
            {b, ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, b, b},
            {b, ' ', ' ', ' ', b, ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
            {b, ' ', b, b, b, ' ', ' ', b, b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b},
            {b, ' ', ' ', ' ', b, ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', b, ' ', ' ', ' ', ' ', ' ', b},
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

    public int width(){
        return map1[1].length;
    }

    public int height(){
        return map1.length;
    }

    public void printMap(Terminal terminal) throws IOException {
        for (int i = 0; i < map1.length; i++) {
            for (int j = 0; j < map1[i].length; j++) {
                terminal.setCursorPosition(i, j);
                terminal.putCharacter(map1[i][j]);
            }
        }
    }

    public void putDiamondOnMap(int x, int y) {
        map1[x][y] = diamond;
    }
    public void putObjectOnMap(char object, int x, int y) {
        map1[x][y] = object;
    }


    public boolean isLegalMove(int x, int y) {
        return x >= 0 && x < map1.length && y >= 0 && y < map1[0].length && map1[x][y] == ' ';
    }

    public boolean isDiamond(int x, int y) {
        return map1[x][y] == diamond;
    }
}