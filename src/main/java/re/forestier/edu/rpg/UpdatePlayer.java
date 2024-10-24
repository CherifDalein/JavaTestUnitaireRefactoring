package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Random;

public class UpdatePlayer {

    private final static String[] objectList = {"Lookout Ring : Prevents surprise attacks","Scroll of Stupidity : INT-2 when applied to an enemy", "Draupnir : Increases XP gained by 100%", "Magic Charm : Magic +10 for 5 rounds", "Rune Staff of Curse : May burn your ennemies... Or yourself. Who knows?", "Combat Edge : Well, that's an edge", "Holy Elixir : Recover your HP"
    };

    public static HashMap<String, HashMap<Integer, HashMap<String, Integer>>> abilitiesPerTypeAndLevel() {
        HashMap<String, HashMap<Integer, HashMap<String, Integer>>> abilitiesPerTypeAndLevel = new HashMap<>();
    
        // Capabilities for Adventurer
        abilitiesPerTypeAndLevel.put("ADVENTURER", createAdventurerAbilities());
    
        // Capabilities for Archer
        abilitiesPerTypeAndLevel.put("ARCHER", createArcherAbilities());
    
        // Capabilities for Dwarf
        abilitiesPerTypeAndLevel.put("DWARF", createDwarfAbilities());

        // Capabilities for Gobelin
        abilitiesPerTypeAndLevel.put("GOBELIN", createGobelinAbilities());
    
        return abilitiesPerTypeAndLevel;
    }
    
    // Helper method to create Adventurer abilities
    private static HashMap<Integer, HashMap<String, Integer>> createAdventurerAbilities() {
        HashMap<Integer, HashMap<String, Integer>> adventurerMap = new HashMap<>();
    
        adventurerMap.put(1, createAbilityMap(new String[]{"INT", "DEF", "ATK", "CHA"}, new int[]{1, 1, 3, 2}));
        adventurerMap.put(2, createAbilityMap(new String[]{"INT", "CHA"}, new int[]{2, 3}));
        adventurerMap.put(3, createAbilityMap(new String[]{"ATK", "ALC"}, new int[]{5, 1}));
        adventurerMap.put(4, createAbilityMap(new String[]{"DEF"}, new int[]{3}));
        adventurerMap.put(5, createAbilityMap(new String[]{"VIS", "DEF"}, new int[]{1, 4}));
    
        return adventurerMap;
    }
    
    // Helper method to create Archer abilities
    private static HashMap<Integer, HashMap<String, Integer>> createArcherAbilities() {
        HashMap<Integer, HashMap<String, Integer>> archerMap = new HashMap<>();
    
        archerMap.put(1, createAbilityMap(new String[]{"INT", "ATK", "CHA", "VIS"}, new int[]{1, 3, 1, 3}));
        archerMap.put(2, createAbilityMap(new String[]{"DEF", "CHA"}, new int[]{1, 2}));
        archerMap.put(3, createAbilityMap(new String[]{"ATK"}, new int[]{3}));
        archerMap.put(4, createAbilityMap(new String[]{"DEF"}, new int[]{2}));
        archerMap.put(5, createAbilityMap(new String[]{"ATK"}, new int[]{4}));
    
        return archerMap;
    }
    
    // Helper method to create Dwarf abilities
    private static HashMap<Integer, HashMap<String, Integer>> createDwarfAbilities() {
        HashMap<Integer, HashMap<String, Integer>> dwarfMap = new HashMap<>();
    
        dwarfMap.put(1, createAbilityMap(new String[]{"ALC", "INT", "ATK"}, new int[]{4, 1, 3}));
        dwarfMap.put(2, createAbilityMap(new String[]{"DEF", "ALC"}, new int[]{1, 5}));
        dwarfMap.put(3, createAbilityMap(new String[]{"ATK"}, new int[]{4}));
        dwarfMap.put(4, createAbilityMap(new String[]{"DEF"}, new int[]{2}));
        dwarfMap.put(5, createAbilityMap(new String[]{"CHA"}, new int[]{1}));
    
        return dwarfMap;
    }

    // Helper method to create Dwarf abilities
    private static HashMap<Integer, HashMap<String, Integer>> createGobelinAbilities() {
        HashMap<Integer, HashMap<String, Integer>> gobelinMap = new HashMap<>();
    
        gobelinMap.put(1, createAbilityMap(new String[]{"INT", "ATK", "ALC"}, new int[]{2, 2, 1}));
        gobelinMap.put(2, createAbilityMap(new String[]{"ATK", "ALC"}, new int[]{3, 4}));
        gobelinMap.put(3, createAbilityMap(new String[]{"VIS"}, new int[]{1}));
        gobelinMap.put(4, createAbilityMap(new String[]{"DEF"}, new int[]{1}));
        gobelinMap.put(5, createAbilityMap(new String[]{"DEF", "ATK"}, new int[]{2, 4}));
    
        return gobelinMap;
    }
    
    // Helper method to create the ability map for a given level
    private static HashMap<String, Integer> createAbilityMap(String[] abilities, int[] values) {
        HashMap<String, Integer> abilityMap = new HashMap<>();
        for (int i = 0; i < abilities.length; i++) {
            abilityMap.put(abilities[i], values[i]);
        }
        return abilityMap;
    }
    

    public static boolean addXp(player player, int xp) {
        int currentLevel = player.retrieveLevel();
        player.xp += xp;
        int newLevel = player.retrieveLevel();

        if (newLevel != currentLevel) {
            // Player leveled-up!
            // Give a random object
            ;
            Random random = new Random();
            player.inventory.add(objectList[random.nextInt(objectList.length - 0) + 0]);

            // Add/upgrade abilities to player
            HashMap<String, Integer> abilities = abilitiesPerTypeAndLevel().get(player.getAvatarClass()).get(newLevel);
            abilities.forEach((ability, level) -> {
                player.abilities.put(ability, abilities.get(ability));
            });
            return true;
        }
        return false;
    }

    // majFinDeTour met à jour les points de vie
    public static void majFinDeTour(player player) {
        if (player.currenthealthpoints == 0) {
            System.out.println("Le joueur est KO !");
            return;
        }
    
        if (player.currenthealthpoints >= player.healthpoints) {
            player.currenthealthpoints = player.healthpoints;
            return;
        }
    
        if (player.currenthealthpoints < player.healthpoints / 2) {
            gérerSoinSelonClasse(player);
        }
    }
    
    private static void gérerSoinSelonClasse(player player) {
        switch (player.getAvatarClass()) {
            case "DWARF":
                soignerNain(player);
                break;

            case "ARCHER":
                soignerArcher(player);
                break;

            case "ADVENTURER":
                soignerAventurier(player);
                break;

            default:
                break;
        }
    }

    private static void soignerNain(player player) {
        player.currenthealthpoints += 1;
        if (player.inventory.contains("Holy Elixir")) {
            player.currenthealthpoints += 1;
        }
    }

    private static void soignerArcher(player player) {
        player.currenthealthpoints += 1;
        if (player.inventory.contains("Magic Bow")) {
            player.currenthealthpoints += player.currenthealthpoints / 8 - 1;
        }
    }

    private static void soignerAventurier(player player) {
        player.currenthealthpoints += 2;
        if (player.retrieveLevel() < 3) {
            player.currenthealthpoints -= 1;
        }
    }
    
}