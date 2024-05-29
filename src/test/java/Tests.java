import org.example.IGamePlayerManager;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class Tests{
    static IGamePlayerManager iGamePlayerManager;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void checkIfRegisterPlayerRegistersAPlayer(){
        //Arrange
        String playerName = "Name";
        String playerId = "1";
        //Act
        iGamePlayerManager.registerPlayer(playerId, playerName);
        String addedPlayer = iGamePlayerManager.getPlayerDetails(playerId);
        //Assert
        Assertions.assertEquals(playerName, addedPlayer);
    }
    @Test
    public void checkIfRegisterPlayerDoesntRegisterSameId(){
        //Arrange
        String playerName = "Name";
        String playerId = "1";
        String klaida = "Toks zaidejo id jau egzistuoja";
        //Act
        iGamePlayerManager.registerPlayer(playerId, playerName);
        iGamePlayerManager.registerPlayer(playerId, playerName);
        String addedPlayer = iGamePlayerManager.getPlayerDetails(playerId);
        //Assert
        Assertions.assertEquals("Toks zaidejo id jau egzistuoja\r\n", outputStreamCaptor.toString());
    }
    @Test
    public void checkIfUpdatePlayerScoreAddsScoreByLevelingUp(){
        //Arrange
        String playerId = "1";
        int playerScore = 12;
        //Act
        iGamePlayerManager.updatePlayerScore(playerId, playerScore);
        //Assert
        Assertions.assertTrue(iGamePlayerManager.checkLevelUp(playerId));
    }
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
