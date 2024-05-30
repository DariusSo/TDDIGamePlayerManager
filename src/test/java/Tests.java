import org.example.IGamePlayerManager;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(playerName, addedPlayer);
    }
    @Test
    public void checkIfRegisterPlayerDoesntRegisterSameId(){
        //Arrange
        String playerName = "Name";
        String playerId = "1";
        //Act
        iGamePlayerManager.registerPlayer(playerId, playerName);
        iGamePlayerManager.registerPlayer(playerId, playerName);
        //Assert
        assertEquals("Toks zaidejo id jau egzistuoja\r\n", outputStreamCaptor.toString());
    }
    @Test
    public void checkIfGetPlayerDetailsReturnsCorrectNameById(){
        //Arrange
        String playerId = "1";
        iGamePlayerManager.registerPlayer(playerId, "Name");
        iGamePlayerManager.registerPlayer("2", "Name1");
        //Act
        String playerName = iGamePlayerManager.getPlayerDetails(playerId);
        //Assert
        assertEquals("Name", playerName);
    }
    @Test
    public void checkIfGetPlayerDetailsReturnsPrintOutWhenIdNotFound(){
        //Arrange
        String playerId = "9999999LBM";
        //Act
        iGamePlayerManager.registerPlayer(playerId, "Name");
        iGamePlayerManager.getPlayerDetails(playerId);
        //Assert
        assertEquals("Zaidejo su tokiu id nera\r\n", outputStreamCaptor.toString());
    }
    @Test
    public void checkIfUpdatePlayerScoreAddsScoreWhenByLevelingUp(){
        //Arrange
        iGamePlayerManager.registerPlayer("1", "Name");
        iGamePlayerManager.registerPlayer("2", "Name1");
        iGamePlayerManager.updatePlayerScore("1", 9);
        iGamePlayerManager.updatePlayerScore("2", 10);
        //Act
        boolean player1 = iGamePlayerManager.checkLevelUp("1");
        boolean player2 = iGamePlayerManager.checkLevelUp("2");
        //Assert
        assertFalse(player1);
        assertTrue(player2);
    }
    @Test
    public void checkIfUpdatePlayerScorePrintsOutWhenIdIsNotFound(){
        //Arrange
        String playerId = "7aer1";
        //Act
        iGamePlayerManager.updatePlayerScore(playerId, 15);
        //Assert
        assertEquals("Zaidejo su tokiu id nera\r\n", outputStreamCaptor.toString());
    }

    @Test
    public void checkIfCheckLevelUpReturnsTrueWhen10Points(){
        //Arrange
        String playerId = "1";
        int playerScore = 10;
        iGamePlayerManager.registerPlayer(playerId, "Name");
        //Act
        iGamePlayerManager.updatePlayerScore(playerId, playerScore);
        //Assert
        assertTrue(iGamePlayerManager.checkLevelUp(playerId));
    }
    @Test
    public void checkIfCheckLevelUpReturnsFalseWhenBelow10Points(){
        //Arrange
        String playerId = "1";
        int playerScore = 9;
        iGamePlayerManager.registerPlayer(playerId, "Name");
        //Act
        iGamePlayerManager.updatePlayerScore(playerId, playerScore);
        //Assert
        assertFalse(iGamePlayerManager.checkLevelUp(playerId));
    }
    @Test
    public void checkIfDeletePlayerReturnsTrueAndcheckIfRemoved(){
        //Arrange
        iGamePlayerManager.registerPlayer("1", "Name");
        //Act
        boolean deleted = iGamePlayerManager.deletePlayer("1");
        iGamePlayerManager.getPlayerDetails("1");
        //Assert
        assertTrue(deleted);
        assertEquals("Zaidejo su tokiu id nera\r\n", outputStreamCaptor.toString());

    }
    @Test
    public void checkIfDeletePlayerReturnsFalse(){
        //Act
        boolean deleted = iGamePlayerManager.deletePlayer("d4412");
        //Assert
        assertFalse(deleted);
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
