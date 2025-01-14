package kata;

public class PlayerManager {
    String[] playerNames = new String[10];
    int[] ages = new int[10];
    int playerCount = 0;

    public void addPlayer(String name, int age) {
        playerNames[playerCount] = name;
        ages[playerCount] = age;
        playerCount++;
    }
    public String getPlayerName(int index) {
        return playerNames[index];
    }

    public int getPlayerAge(int index) {
        return ages[index];
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public String getPlayerDetails(String name) {
        for (int i = 0; i < playerCount; i++) {
            if (playerNames[i].equals(name)) {
                return name + " is " + ages[i] + " years old.";
            }
        }
        return "Player not found.";
    }

    public void showAllPlayers() {
        for (int i = 0; i < playerCount; i++) {
            System.out.println(playerNames[i] + " (" + ages[i] + " years old)");
        }
    }
}
