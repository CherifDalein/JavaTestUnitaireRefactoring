package kata;

public class TeamManager {
    String[] players = new String[10];
    int[] scores = new int[10];
    int playerCount = 0;

    public void addPlayer(String player, int score) {
        players[playerCount] = player;
        scores[playerCount] = score;
        playerCount++;
    }
    public String[] getPlayers() {
        return players;
    }

    public int[] getScores() {
        return scores;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void removePlayer(String player) {
        for (int i = 0; i < playerCount; i++) {
            if (players[i].equals(player)) {
                for (int j = i; j < playerCount - 1; j++) {
                    players[j] = players[j + 1];
                    scores[j] = scores[j + 1];
                }
                playerCount--;
                break;
            }
        }
    }

    public int calculateTotalScore() {
        int total = 0;
        for (int i = 0; i < playerCount; i++) {
            total += scores[i];
        }
        return total;
    }

    public void showPlayers() {
        for (int i = 0; i < playerCount; i++) {
            System.out.println(players[i] + ": " + scores[i]);
        }
    }
}
