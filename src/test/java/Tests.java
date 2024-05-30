import org.example.IGamePlayerManager;
import org.example.IGamePlayerManagerImpl;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Tests{
    static IGamePlayerManager iGamePlayerManagerImpl;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void checkIfRegisterPlayerRegistersAPlayer(){
        //Arrange
        String playerName = "Name";
        String playerId = "1";
        //Act
        iGamePlayerManagerImpl.registerPlayer(playerId, playerName);
        String addedPlayer = iGamePlayerManagerImpl.getPlayerDetails(playerId);
        //Assert
        assertEquals(playerName, addedPlayer);
    }
    @Test
    public void checkIfRegisterPlayerDoesntRegisterSameId(){
        //Arrange
        String playerName = "Name";
        String playerId = "1";
        //Act
        iGamePlayerManagerImpl.registerPlayer(playerId, playerName);
        iGamePlayerManagerImpl.registerPlayer(playerId, playerName);
        //Assert
        assertEquals("Toks zaidejo id jau egzistuoja\r\n", outputStreamCaptor.toString());
    }
    @Test
    public void checkIfGetPlayerDetailsReturnsCorrectNameById(){
        //Arrange
        String playerId = "1";
        iGamePlayerManagerImpl.registerPlayer(playerId, "Name");
        iGamePlayerManagerImpl.registerPlayer("2", "Name1");
        //Act
        String playerName = iGamePlayerManagerImpl.getPlayerDetails(playerId);
        //Assert
        assertEquals("Name", playerName);
    }
    @Test
    public void checkIfGetPlayerDetailsReturnsPrintOutWhenIdNotFound(){
        //Arrange
        String playerId = "9999999LBM";
        //Act
        iGamePlayerManagerImpl.getPlayerDetails(playerId);
        //Assert
        assertEquals("Zaidejo su tokiu id nera\r\n", outputStreamCaptor.toString());
    }
    @Test
    public void checkIfUpdatePlayerScoreAddsScoreWhenByLevelingUp(){
        //Arrange
        iGamePlayerManagerImpl.registerPlayer("1", "Name");
        iGamePlayerManagerImpl.registerPlayer("2", "Name1");
        iGamePlayerManagerImpl.updatePlayerScore("1", 9);
        iGamePlayerManagerImpl.updatePlayerScore("2", 10);
        //Act
        boolean player1 = iGamePlayerManagerImpl.checkLevelUp("1");
        boolean player2 = iGamePlayerManagerImpl.checkLevelUp("2");
        //Assert
        assertFalse(player1);
        assertTrue(player2);
    }
    @Test
    public void checkIfUpdatePlayerScorePrintsOutWhenIdIsNotFound(){
        //Arrange
        String playerId = "7aer1";
        //Act
        iGamePlayerManagerImpl.updatePlayerScore(playerId, 15);
        //Assert
        assertEquals("Zaidejo su tokiu id nera\r\n", outputStreamCaptor.toString());
    }

    @Test
    public void checkIfCheckLevelUpReturnsTrueWhen10Points(){
        //Arrange
        String playerId = "1";
        int playerScore = 10;
        iGamePlayerManagerImpl.registerPlayer(playerId, "Name");
        //Act
        iGamePlayerManagerImpl.updatePlayerScore(playerId, playerScore);
        //Assert
        assertTrue(iGamePlayerManagerImpl.checkLevelUp(playerId));
    }
    @Test
    public void checkIfCheckLevelUpReturnsFalseWhenBelow10Points(){
        //Arrange
        String playerId = "1";
        int playerScore = 9;
        iGamePlayerManagerImpl.registerPlayer(playerId, "Name");
        //Act
        iGamePlayerManagerImpl.updatePlayerScore(playerId, playerScore);
        //Assert
        assertFalse(iGamePlayerManagerImpl.checkLevelUp(playerId));
    }
    @Test
    public void checkIfDeletePlayerReturnsTrueAndcheckIfRemoved(){
        //Arrange
        iGamePlayerManagerImpl.registerPlayer("1", "Name");
        //Act
        boolean deleted = iGamePlayerManagerImpl.deletePlayer("1");
        iGamePlayerManagerImpl.getPlayerDetails("1");
        //Assert
        assertTrue(deleted);
        assertEquals("Zaidejo su tokiu id nera\r\n", outputStreamCaptor.toString());

    }
    @Test
    public void checkIfDeletePlayerReturnsFalse(){
        //Act
        boolean deleted = iGamePlayerManagerImpl.deletePlayer("d4412");
        //Assert
        assertFalse(deleted);
    }

    @BeforeEach
    public void setUp() {
        iGamePlayerManagerImpl = new IGamePlayerManagerImpl();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
