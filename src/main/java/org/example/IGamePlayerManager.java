package org.example;

public interface IGamePlayerManager {
    // Metodas registruoti naują žaidėją sistemoje
    void registerPlayer(String playerId, String playerName);

    // Metodas grąžinti žaidėjo duomenis pagal ID
    String getPlayerDetails(String playerId);

    // Metodas atnaujinti žaidėjo taškus
    void updatePlayerScore(String playerId, int scoreToAdd);

    // Metodas patikrinti, ar žaidėjas pasiekė naują lygį
    boolean checkLevelUp(String playerId);

    // Metodas ištrinti žaidėją iš sistemos
    boolean deletePlayer(String playerId);
}
