package org.example;

import java.util.HashMap;
import java.util.Map;

public class IGamePlayerManagerImpl implements IGamePlayerManager{
    Map<String, String> playerList = new HashMap<>();
    Map<String, Integer> playerScore = new HashMap<>();
    @Override
    public void registerPlayer(String playerId, String playerName) {
        if(!playerList.containsKey(playerId)){
            playerList.put(playerId, playerName);
            playerScore.put(playerId, 0);
        }else{
            System.out.println("Toks zaidejo id jau egzistuoja");
        }
    }

    @Override
    public String getPlayerDetails(String playerId) {
        if(playerList.containsKey(playerId)){
            return playerList.get(playerId);
        }else{
            System.out.println("Zaidejo su tokiu id nera");
            return null;
        }
    }

    @Override
    public void updatePlayerScore(String playerId, int scoreToAdd) {
        if(playerScore.containsKey(playerId)){
            int newScore = playerScore.get(playerId) + scoreToAdd;
            playerScore.put(playerId, newScore);
        }else{
            System.out.println("Zaidejo su tokiu id nera");
        }
    }

    @Override
    public boolean checkLevelUp(String playerId) {
        if(playerScore.get(playerId) >= 10){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deletePlayer(String playerId) {
        if(playerList.containsKey(playerId)){
            playerList.remove(playerId);
            playerScore.remove(playerId);
            return true;
        }else{
            return false;
        }
    }
}
