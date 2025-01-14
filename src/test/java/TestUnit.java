import java.util.ArrayList; 
import org.junit.jupiter.api.Test;

import kata.PlayerManager;

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
}
