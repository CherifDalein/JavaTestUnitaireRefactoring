package kata;

public class GameStatistics {
    String[] gameNames = new String[10];
    int[] scores = new int[10];
    int gameCount = 0;

    public void addGame(String gameName, int score) {
        gameNames[gameCount] = gameName;
        scores[gameCount] = score;
        gameCount++;
    }

    public String highestScoringGame() {
        if (gameCount == 0) {
            return "No games available.";
        }

        int highestScore = scores[0];
        String highestGame = gameNames[0];
        for (int i = 1; i < gameCount; i++) {
            if (scores[i] > highestScore) {
                highestScore = scores[i];
                highestGame = gameNames[i];
            }
        }
        return "Highest Scoring Game: " + highestGame + " with score " + highestScore;
    }

    public double averageScore() {
        if (gameCount == 0) {
            return 0.0;
        }

        int totalScore = 0;
        for (int i = 0; i < gameCount; i++) {
            totalScore += scores[i];
        }
        return (double) totalScore / gameCount;
    }

    public void showAllGames() {
        for (int i = 0; i < gameCount; i++) {
            System.out.println(gameNames[i] + ": " + scores[i]);
        }
    }
}
