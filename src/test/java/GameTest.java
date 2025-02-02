import game.*;
import org.junit.Test;

public class GameTest {
    GameFactory gameFactory = new GameFactory();

    @Test
    public void timeOutTest() {
        Game game = gameFactory.createGame(3, 120);
        Player x = new Player("X");
        Cell firstCell = Cell.getCell(0,0);
        int ts = 5000;
        game.move(new Move(firstCell, x), ts);
    }

    @Test
    public void timeOutTestPerPlayer() {
        Game game = gameFactory.createGame( 120);
    }
}
