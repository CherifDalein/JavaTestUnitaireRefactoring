package kata;

public class Main {
    public static void main(String[] args) {
        // Instanciation des classes
        TeamManager teamManager = new TeamManager();
        PlayerManager playerManager = new PlayerManager();
        GameStatistics gameStats = new GameStatistics();

        // Gestion des joueurs
        System.out.println("=== Gestion des joueurs ===");
        teamManager.addPlayer("Lionel Messi", 92);
        teamManager.addPlayer("Cristiano Ronaldo", 95);
        teamManager.addPlayer("Kylian Mbappé", 89);

        System.out.println("\nListe des joueurs et leurs scores :");
        teamManager.showPlayers();

        System.out.println("\nSuppression de Cristiano Ronaldo...");
        teamManager.removePlayer("Cristiano Ronaldo");

        System.out.println("Liste des joueurs après suppression :");
        teamManager.showPlayers();

        System.out.println("\nScore total de l'équipe : " + teamManager.calculateTotalScore());

        // Gestion des informations des joueurs
        System.out.println("\n=== Informations sur les joueurs ===");
        playerManager.addPlayer("Lionel Messi", 36);
        playerManager.addPlayer("Kylian Mbappé", 24);

        System.out.println("\nDétails des joueurs :");
        playerManager.showAllPlayers();

        System.out.println("\nDétails de Lionel Messi :");
        System.out.println(playerManager.getPlayerDetails("Lionel Messi"));

        // Gestion des statistiques de jeu
        System.out.println("\n=== Statistiques de jeu ===");
        gameStats.addGame("Match 1", 3);
        gameStats.addGame("Match 2", 5);
        gameStats.addGame("Match 3", 2);

        System.out.println("\nListe des matchs et leurs scores :");
        gameStats.showAllGames();

        System.out.println("\nLe match avec le meilleur score est :");
        System.out.println(gameStats.highestScoringGame());

        System.out.println("\nScore moyen de tous les matchs : " + gameStats.averageScore());
    }
}
