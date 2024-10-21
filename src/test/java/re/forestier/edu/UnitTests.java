package re.forestier.edu;

import org.junit.jupiter.api.*;

import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;

import static org.approvaltests.Approvals.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class UnitTests {

    @Test
    @DisplayName("Sample test")
    void testPlayerName() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    @Test
    @DisplayName("Test adding money to player")
    void testAddMoney() {
        player p = new player("Alice", "Mage", "ADVENTURER", 100, new ArrayList<>());
        p.addMoney(50);
        assertThat(p.money, is(150));
    }
    
    @Test
    @DisplayName("Test removing money to player")
    void testRemoveMoney() {
        player p = new player("Alice", "Mage", "ADVENTURER", 100, new ArrayList<>());
        p.removeMoney(50);
        assertThat(p.money, is(50));
    }

    @Test 
    @DisplayName("Mutation pour remove money: essaie d'enlever toute la somme possédée(le cas du < ou =)")
    void mutationRemoveMoney1(){
        player p = new player("Alice", "Mage", "ADVENTURER", 100, new ArrayList<>());
        p.removeMoney(100);
        assertThat(p.money, is(0));
    }
    


    @Test
    @DisplayName("Test getting XP from player")
    void testGetXp() {
        player p = new player("Diana", "Ranger", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 20);
        assertThat(p.getXp(), is(20));
    }


    @Test
    @DisplayName("Test constructor with valid and invalid avatar class")
    void testConstructor() {
        player p1 = new player("John", "Warrior", "ADVENTURER", 150, new ArrayList<>());
        assertThat(p1.getAvatarClass(), is("ADVENTURER"));
        player p2 = new player("John", "Warrior", "DWARF", 150, new ArrayList<>());
        assertThat(p2.getAvatarClass(), is("DWARF"));
        player p3 = new player("John", "Warrior", "ARCHER", 150, new ArrayList<>());
        assertThat(p3.getAvatarClass(), is("ARCHER"));
        player p4 = new player("John", "Mage", "AUTRE", 200, new ArrayList<>());
        assertNull(p4.getAvatarClass()); 
    }


    @Test
    @DisplayName("Player level should be 1 when xp is less than 10")
    void testLevelOne() {
        player p = new player("Diana", "Ranger", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 5); // xp < 10
        assertThat(p.retrieveLevel(), is(1));
    }

    @Test
    @DisplayName("Player level should be 1 when xp is less than 10")
    void testLevelOne1() {
        player p = new player("Diana", "Ranger", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 10); 
        assertThat(p.retrieveLevel(), is(2));
    }

    @Test
    @DisplayName("Player level should be 2 when xp is between 10 and 26")
    void testLevelTwo() {
        player p = new player("Diana", "Ranger", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 20); // 10 <= xp < 27
        assertThat(p.retrieveLevel(), is(2));
    }
    @Test
    @DisplayName("Mutation pour le level 2")
    void testLevelTwo1() {
        player p = new player("Diana", "Ranger", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 27); // 10 <= xp < 27
        assertThat(p.retrieveLevel(), is(3));
    }

    @Test
    @DisplayName("Player level should be 3 when xp is between 27 and 56")
    void testLevelThree() {
        player p = new player("Diana", "Ranger", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 40); // 27 <= xp < 57
        assertThat(p.retrieveLevel(), is(3));
    }
    @Test
    @DisplayName("Mutation du level 3")
    void testLevelThree1() {
        player p = new player("Diana", "Ranger", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 57); // 27 <= xp < 57
        assertThat(p.retrieveLevel(), is(4));
    }
    

    @Test
    @DisplayName("Player level should be 4 when xp is between 57 and 110")
    void testLevelFour() {
        player p = new player("Diana", "Ranger", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 75); // 57 <= xp < 111
        assertThat(p.retrieveLevel(), is(4));
    }
    @Test
    @DisplayName("Mutation du level 4")
    void testLevelFour1() {
        player p = new player("Diana", "Ranger", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 111); // 57 <= xp < 111
        assertThat(p.retrieveLevel(), is(5));
    }

    @Test
    @DisplayName("Player level should be 5 when xp is 111 or more")
    void testLevelFive() {
        player p = new player("Diana", "Ranger", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 120); // xp >= 111
        assertThat(p.retrieveLevel(), is(5));
    }





    //UPDATEPLAYER

    @Test
    @DisplayName("Test end of turn with zero health points")
    void testEndOfTurnPlayerKO() {
        player p = new player("John", "Warrior", "DWARF", 100, new ArrayList<>());
        p.currenthealthpoints = 0;

        UpdatePlayer.majFinDeTour(p);

        // Pas de changement attendu, le joueur est KO
        assertThat(p.currenthealthpoints, is(0));
    }
    
    @Test
    @DisplayName("Mutation joueur KO (currenthealthpoints == 0)")
    void testJoueurKO() {
        player p = new player("Jean", "Guerrier", "DWARF", 100, new ArrayList<>());
        p.currenthealthpoints = 0;
        
        // Rediriger la sortie standard pour capturer le message affiché
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        UpdatePlayer.majFinDeTour(p);
        
        // Vérifier que le message "Le joueur est KO !" a été affiché
        assertTrue(outContent.toString().contains("Le joueur est KO !"));
        
    }



    @Test
    @DisplayName("Test récupération de points de vie pour DWARF avec Elixir sacré")
    void testRécupérationVieNainAvecElixir() {
        ArrayList<String> inventory=new ArrayList<>();
        // Créer un joueur de classe "DWARF" avec des points de vie inférieurs à la moitié
        player p = new player("Jean", "Guerrier", "DWARF", 40, inventory);
        
        p.currenthealthpoints = 5;  // Moins de la moitié des points de vie
        p.healthpoints = 40;  // Moins de la moitié des points de vie
        p.inventory.add("Holy Elixir");  // Ajouter l'élixir sacré dans l'inventaire
        
        // Appel de la méthode majFinDeTour pour simuler la fin du tour
        UpdatePlayer.majFinDeTour(p);

        assertThat(p.currenthealthpoints, is(7));  // 40 + 1 (DWARF) + 1 (Holy Elixir)
    }

    @Test
    @DisplayName("Test récupération de points de vie pour DWARF avec Elixir sacré")
    void testRécupérationVieNainAvecADV() {
        ArrayList<String> inventory=new ArrayList<>();
        // Créer un joueur de classe "DWARF" avec des points de vie inférieurs à la moitié
        player p = new player("Jean1", "Guerrier", "ADVENTURER", 40, inventory);
        
        p.currenthealthpoints = 5;  // Moins de la moitié des points de vie
        p.healthpoints = 40;  // Moins de la moitié des points de vie
        
        // Appel de la méthode majFinDeTour pour simuler la fin du tour
        UpdatePlayer.majFinDeTour(p);

        assertThat(p.currenthealthpoints, is(6));  // 40 + 1 (DWARF) + 1 (Holy Elixir)
    }

    @Test
    @DisplayName("Test récupération de points de vie pour DWARF avec Elixir sacré")
    void testRécupérationVieNainDWARF() {
        ArrayList<String> inventory=new ArrayList<>();
        // Créer un joueur de classe "DWARF" avec des points de vie inférieurs à la moitié
        player p = new player("Jean1", "Guerrier", "DWARF", 40, inventory);
        
        p.currenthealthpoints = 5;  // Moins de la moitié des points de vie
        p.healthpoints = 40;  // Moins de la moitié des points de vie
        
        // Appel de la méthode majFinDeTour pour simuler la fin du tour
        UpdatePlayer.majFinDeTour(p);
        

        assertThat(p.currenthealthpoints, is(6));  // 40 + 1 (DWARF) + 1 (Holy Elixir)
    }






    @Test
    @DisplayName("Test récupération de points de vie pour ARCHER sans Magic Bow")
    void testRécupérationVieArcherSansArcMagique() {
        // Créer un joueur de classe "ARCHER" sans "Magic Bow"
        player p = new player("Robin", "Archer", "ARCHER", 50, new ArrayList<>());
        
        p.currenthealthpoints = 10;  // Moins de la moitié des points de vie
        p.healthpoints = 50;  // Maximum de points de vie
        
        // Vérifier que la classe du joueur est bien "ARCHER"
        assertThat(p.getAvatarClass(), is("ARCHER"));

        // Appel de la méthode majFinDeTour pour simuler la fin du tour
        UpdatePlayer.majFinDeTour(p);

        // Vérifier que les points de vie ont été augmentés de 1
        assertThat(p.currenthealthpoints, is(11));  // 10 + 1 (pour "ARCHER")
    }

    @Test
    @DisplayName("Test récupération de points de vie pour ARCHER avec Magic Bow")
    void testRécupérationVieArcherAvecArcMagique() {
        // Créer un joueur de classe "ARCHER" avec "Magic Bow"
        ArrayList<String> inventory = new ArrayList<>();
        inventory.add("Magic Bow");
        
        player p = new player("Robin", "Archer", "ARCHER", 50, inventory);
        
        p.currenthealthpoints = 16;  // Moins de la moitié des points de vie
        p.healthpoints = 50;  // Maximum de points de vie
        
        // Vérifier que la classe du joueur est bien "ARCHER"
        assertThat(p.getAvatarClass(), is("ARCHER"));

        // Appel de la méthode majFinDeTour pour simuler la fin du tour
        UpdatePlayer.majFinDeTour(p);

        // Calcul du nombre de points de vie supplémentaires (16/8 - 1 = 1)
        int expectedHealth = 16 + 1 + (16 / 8 - 1);  // 16 + 1 (pour "ARCHER") + 1 (pour "Magic Bow")

        // Vérifier que les points de vie ont été augmentés correctement
        assertThat(p.currenthealthpoints, is(expectedHealth));
    }




    @Test
    @DisplayName("Test pour joueur avec points de vie égaux à la moitié de sa santé maximale")
    void testPointsDeVieEgalMoitié() {
        // Créer un joueur avec des points de vie égaux à la moitié de sa santé maximale
        player p = new player("Jean", "Guerrier", "ADVENTURER", 100, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 50; // 50 est la moitié de 100

        // Appel de la méthode majFinDeTour pour simuler la fin du tour
        UpdatePlayer.majFinDeTour(p);

        // Vérifier que les points de vie restent inchangés
        assertThat(p.currenthealthpoints, is(50));  // Il ne doit pas y avoir de changement
    }

    @Test
    @DisplayName("Test pour joueur avec points de vie supérieurs à la santé maximale")
    void testPointsDeVieSupérieursÀMaximum() {
        // Créer un joueur avec des points de vie supérieurs à sa santé maximale
        player p = new player("Jean", "Guerrier", "ADVENTURER", 100, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 120; // Supérieur à la santé maximale

        // Appel de la méthode majFinDeTour pour simuler la fin du tour
        UpdatePlayer.majFinDeTour(p);

        // Vérifier que les points de vie sont remis à la santé maximale
        assertThat(p.currenthealthpoints, is(100));  // Il doit être remis à 100
    }




//AFFICHAGE

@Test
void testAffichage(){
    player player = new player("Jean", "Guerrier", "ADVENTURER", 100, new ArrayList<>());
    UpdatePlayer.addXp(player, 20);
    player.inventory=new ArrayList<>();
    // verify(Affichage.afficherJoueur(player));
    player.inventory.add("potion de santé");
    player.inventory.add("Epee");
    player.inventory.add("Bouclier");
    UpdatePlayer.addXp(player, 20);
    String result=Affichage.afficherJoueur(player);
    assertThat(result, containsString("potion de santé"));
    assertThat(result, containsString("Epee"));
    assertThat(result, containsString("Bouclier"));

}

}
