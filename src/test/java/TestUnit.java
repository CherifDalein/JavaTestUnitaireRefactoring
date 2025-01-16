import java.util.ArrayList; 
import org.junit.jupiter.api.Test;

import kata.GameStatistics;
import kata.PlayerManager;
import kata.TeamManager;

import org.junit.jupiter.api.DisplayName; 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue; 

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestUnit {

    @Test
    @DisplayName("Add player")
    void testAddPlayer() {
        PlayerManager playermanager = new PlayerManager();
        playermanager.addPlayer("Bruno", 30);
        assertEquals("Bruno", playermanager.getPlayerName(0) );
        assertEquals(30, playermanager.getPlayerAge(0) );
        assertEquals(1, playermanager.getPlayerCount());
        
    }

    @Test
    @DisplayName("get Player Details avec au moins un joueur")
    void TestGetplayerdetails(){
        PlayerManager p = new PlayerManager();
        p.addPlayer("Gakpo", 25);
        assertEquals(p.getPlayerDetails("Gakpo"), "Gakpo is 25 years old.");
    }
    @Test
    @DisplayName("get Player Details sans joueur")
    void TestGetplayerdetailsVIDE(){
        PlayerManager p = new PlayerManager();
        p.addPlayer("Gakpo", 25);
        assertEquals(p.getPlayerDetails("Rashford"), "Player not found.");
    }

    @Test
    @DisplayName("Test de showAllPlayers")
    void testShowAllPlayers() {
        // Capturer la sortie standard
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // Sauvegarder la sortie standard originale
        System.setOut(new PrintStream(outContent)); // Rediriger la sortie vers outContent

        PlayerManager p = new PlayerManager();
        p.addPlayer("Gakpo", 25);

        // Appeler la méthode qui imprime sur la console
        p.showAllPlayers();

        // Comparer la sortie capturée avec la sortie attendue
        assertTrue(outContent.toString().contains("Gakpo (25 years old)"));

        // Restauration de la sortie standard originale
        System.setOut(originalOut);
    }
    //----------------------------------------------------------------------------------

    @Test
    @DisplayName("Test de AddPlayer")
    void TestAddplayerTeam(){
        TeamManager t = new TeamManager();
        t.addPlayer("Foden", 89);

        assertEquals(t.getScores()[0], 89);
        assertEquals(t.getPlayers()[0], "Foden");
        assertEquals(t.getPlayerCount(), 1);   
    }

    @Test
    @DisplayName("Test de Remove Player")
    void TestRemovePlayer(){
        TeamManager t = new TeamManager();
        t.addPlayer("Foden", 89);
        t.addPlayer("CRJunior", 80);
        t.removePlayer("Foden");

        assertEquals(t.getPlayerCount(), 1);
        assertEquals(t.getScores()[0], 80);
        assertEquals(t.getPlayers()[0], "CRJunior");           
    }

    @Test
    @DisplayName("Test de calculate total score")
    void TestCalculateTotalScore(){
        TeamManager t = new TeamManager();
        t.addPlayer("Foden", 89);
        t.addPlayer("CRJunior", 80);
        t.calculateTotalScore();

        assertEquals(t.calculateTotalScore(), 169);
    }

    @Test
    @DisplayName("Test de Show Players")
    void TestShowPlayers(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // Sauvegarder la sortie standard originale
        System.setOut(new PrintStream(outContent)); // Rediriger la sortie vers outConten
        TeamManager t = new TeamManager();
        t.addPlayer("Foden", 89);
        t.showPlayers();

        assertTrue(outContent.toString().contains("Foden: 89")); 
        System.setOut(originalOut);
    }
    //----------------------------------------------------------------------------------

    @Test
    @DisplayName("Test de AddGame")
    void TestAddGame(){
        GameStatistics g = new GameStatistics();
        g.addGame("Match 1", 2);

        assertEquals(g.getScores()[0], 2);
        assertEquals(g.getGameNames()[0], "Match 1");
        assertEquals(g.getGameCount(), 1);   
    }
    @Test
    @DisplayName("Test de TesthighestScoringGame sans match")
    void testNoGame() {
        GameStatistics teamManager = new GameStatistics();
        assertEquals("No games available.", teamManager.highestScoringGame());
    }

    @Test
    @DisplayName("Test de TesthighestScoringGame Avec 2 match de score different")
    void TesthighestScoringGame0(){
        GameStatistics g = new GameStatistics();
        g.addGame("Match 1", 2);
        g.addGame("Match 2", 3);

        assertEquals(g.highestScoringGame(), "Highest Scoring Game: Match 2 with score 3");
    }
    @Test
    @DisplayName("Test de highestScoringGame avec des matchs de meme score")
    void testMultipleGamesSameScore() {
        GameStatistics teamManager = new GameStatistics();
        teamManager.addGame("Game B", 75);
        teamManager.addGame("Game C", 75);
        assertEquals("Highest Scoring Game: Game B with score 75", teamManager.highestScoringGame());
    }

    @Test
    @DisplayName("Test de averageScore sans match")
    void averageScore0(){
        GameStatistics g = new GameStatistics();

        assertEquals(0.0, g.averageScore());
 
    }
    @Test
    @DisplayName("Test de averageScore avec au moins 2 match")
    void averageScore(){
        GameStatistics g = new GameStatistics();
        g.addGame("Match 1", 2);
        g.addGame("Match 2", 3);

        assertEquals(g.averageScore(), 2.5);
 
    }

    @Test
    @DisplayName("Test de ShowAllGames")
    void TestShowAllGames(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // Sauvegarder la sortie standard originale
        System.setOut(new PrintStream(outContent)); // Rediriger la sortie vers outConten
        GameStatistics t = new GameStatistics();
        t.addGame("Match 1", 5);
        t.showAllGames();

        assertTrue(outContent.toString().contains("Match 1: 5")); 
        System.setOut(originalOut);
    }
}
