import java.util.ArrayList; 
import org.junit.jupiter.api.Test;

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
}
