/**
 *  Arfa Raja, Nethanya Dhaipule, Syed Omar
 *  April 15, 2024
 *  T12
 */

package ca.ucalgary.groupprojectgui;

import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

public class Test {

    // Test for checking character type existence
    @org.junit.Test
    public void testCheckCharacter() {
        // Given: Populate a list with different types of characters
        List<Character> characterList = new ArrayList<>();
        characterList.add(new Healer("Healer1", 100, 50, 30));
        characterList.add(new Marksman("Marksman1", 120, 60, 40));
        characterList.add(new Swordsman("Swordsman1", 150, 70, 50));
        characterList.add(new ShieldUser("ShieldUser1", 200, 80, 60));

        // When: Check if characters of each type exist
        boolean healerExists = Character.CheckCharacter(1, characterList);
        boolean marksmanExists = Character.CheckCharacter(2, characterList);
        boolean swordsmanExists = Character.CheckCharacter(3, characterList);
        boolean shieldUserExists = Character.CheckCharacter(4, characterList);

        // Then: Ensure that each type of character exists
        assertTrue(healerExists);
        assertTrue(marksmanExists);
        assertTrue(swordsmanExists);
        assertTrue(shieldUserExists);
    }

    // Test for character creation
    @org.junit.Test
    public void testCharacterCreation() {
        // Given: An empty list of characters

        // When: Create a new character and add it to the list
        List<Character> characterList = new ArrayList<>();
        Character newCharacter = new Healer("NewHealer", 100, 50, 30);
        characterList.add(newCharacter);

        // Then: Ensure that the character is created correctly
        assertEquals(1, characterList.size());
        assertEquals("NewHealer", characterList.get(0).getName());
        assertEquals(100, characterList.get(0).getHp());
        assertEquals(50, characterList.get(0).getAtk());
        assertEquals(30, characterList.get(0).getDef());
    }

    // Test for team creation
    @org.junit.Test
    public void testTeamCreation() {
        // Given: A list with two different types of characters
        List<Character> characterList = new ArrayList<>();
        Character healer = new Healer("Healer1", 100, 50, 30);
        Character marksman = new Marksman("Marksman1", 120, 60, 40);
        characterList.add(healer);
        characterList.add(marksman);

        // When: Create a team using the characters
        String teamName = "Team1";
        List<Character> teamMembers = new ArrayList<>();
        teamMembers.add(healer);
        teamMembers.add(marksman);
        Team team = Team.createTeam(teamName, teamMembers);

        // Then: Ensure that the team is created correctly
        assertEquals(teamName, team.getName());
        assertEquals(2, team.getMembers().size());
        assertTrue(team.getMembers().contains(healer));
        assertTrue(team.getMembers().contains(marksman));
    }

    // Test for FileLoader functionality
    @org.junit.Test // says contents are identical...
    public void testFileLoader() {
        // Initialize the characterList and teams
        List<Character> characterList = new ArrayList<>();
        Map<String, List<Character>> teams = new HashMap<>();

        // Provide the file to be loaded
        File file = new File("TeamFile");

        // Call the method to be tested
        FileLoader.load(file, characterList, teams);

        // Provide the expected results
        List<Character> expectedCharacterList = new ArrayList<>();
        expectedCharacterList.add(Character.createFileCharacter("A", 1, 1, 1, CharacterType.HEALER));
        expectedCharacterList.add(Character.createFileCharacter("B", 2, 2, 2, CharacterType.MARKSMAN));
        expectedCharacterList.add(Character.createFileCharacter("C", 3, 3, 3, CharacterType.SWORDSMAN));
        expectedCharacterList.add(Character.createFileCharacter("D", 4, 4, 4, CharacterType.SHIELDUSER));

        Map<String, List<Character>> expectedTeams = new HashMap<>();
        expectedTeams.put("team", new ArrayList<>(expectedCharacterList)); // Assuming team has all characters

        // Check if the characterList and teams match the expected results
        assertEquals(expectedCharacterList, characterList);
        assertEquals(expectedTeams, teams);
    }

    // Test for FileSaver functionality
    @org.junit.Test
    public void testFileSaver() throws IOException {
        // Initialize the characterList and teams
        List<Character> characterList = new ArrayList<>();
        characterList.add(Character.createFileCharacter("A", 1, 1, 1, CharacterType.HEALER));
        characterList.add(Character.createFileCharacter("B", 2, 2, 2, CharacterType.MARKSMAN));
        characterList.add(Character.createFileCharacter("C", 3, 3, 3, CharacterType.SWORDSMAN));
        characterList.add(Character.createFileCharacter("D", 4, 4, 4, CharacterType.SHIELDUSER));

        Map<String, List<Character>> teams = new HashMap<>();
        teams.put("team", new ArrayList<>(characterList)); // Assuming team has all characters

        // Create a temporary file
        File tempFile = File.createTempFile("test", ".txt");

        // Call the method to be tested
        boolean result = FileSaver.save(tempFile, characterList, teams);

        // Check if the method returned true
        assertTrue(result);

        // Now read the file and check if the contents are as expected
        List<String> lines = Files.readAllLines(tempFile.toPath());
        assertEquals("Characters", lines.get(0));
        assertEquals("A,HEALER,1,1,1", lines.get(1));
        assertEquals("B,MARKSMAN,2,2,2", lines.get(2));
        assertEquals("C,SWORDSMAN,3,3,3", lines.get(3));
        assertEquals("D,SHIELDUSER,4,4,4", lines.get(4));
        assertEquals("", lines.get(5));
        assertEquals("Teams", lines.get(6));
        assertEquals("team,A;B;C;D;", lines.get(7));
    }
    @org.junit.Test
    public void CalculateBossAtk(){
        //The atk of the boss is calculated using a number from 0-10.
        Random rand = new Random();
        int highestDamageMultiplier = 11;
        int randomMultiplier = rand.nextInt(highestDamageMultiplier);
        //fulldamage is the full damage dealt by the boss
        int fulldamage = randomMultiplier * 1000;
        //The 3 lines below check to see if the full damage has been calculated and is expected.
        Assert.assertTrue(fulldamage >= 0);
        Assert.assertTrue(fulldamage < 10001);
        Assert.assertTrue(fulldamage % 1000 == 0);
    }
    @org.junit.Test
    public void AskTopThreeAtk(){
        ArrayList<Character> characterList = new ArrayList<Character>();
        //The four lines below fill the characterlist with unique members
        characterList.add(Character.createFileCharacter("Bob", 2, 10, 2, CharacterType.HEALER));
        characterList.add(Character.createFileCharacter("John", 3, 2, 1, CharacterType.MARKSMAN));
        characterList.add(Character.createFileCharacter("Amy", 5, 4, 7, CharacterType.SWORDSMAN));
        characterList.add(Character.createFileCharacter("Amber", 6, 9, 0, CharacterType.SHIELDUSER));
        HashMap<String, Integer> partyDamage = new HashMap<String, Integer>();
        // It goes through an arraylist filled with all the members of party, and gets each member's type and attack.
        for (Character member : characterList) {
            //  The type and attack of each member are stored as pairs in the partyDamage hashmap.
            partyDamage.put(member.getType().toString(), member.getAtk());
        }
        // Then all the details in partyDamage are used to fill and alter an array called TopThreeMembers
        // with the 3 members that deal the most amount of damage.
        String[] TopThreeMembers = new String[3];
        ArrayList<String> allMembers = new ArrayList<String>(partyDamage.keySet());
        ArrayList<Integer> allMemberDamage = new ArrayList<Integer>(partyDamage.values());

        for (int memberNum = 0; memberNum < allMembers.size(); memberNum++) {
            String currentMember = allMembers.get(memberNum);
            int damage = allMemberDamage.get(memberNum);
            String memberDetails = currentMember + ":" + damage;

            for (int i = 0; i < TopThreeMembers.length; i++) {
                if (TopThreeMembers[i] == null || Integer.parseInt(TopThreeMembers[i].split(":")[1]) < damage) {
                    for (int j = TopThreeMembers.length - 1; j > i; j--) {
                        TopThreeMembers[j] = TopThreeMembers[j - 1];
                    }
                    TopThreeMembers[i] = memberDetails;
                    break;
                }
            }
        }
        //The six lines below check to makes sure that all 3 highest damaging members are in the right
        //positions in TopThreeMembers. The item at index 0 in TopThreeMembers should hold information
        // about the member that does the most damage. The item at index 0 in TopThreeMembers should hold
        // information about the member that does the 2nd most damage. The item at index 0 in
        // TopThreeMembers should hold information about the member that does the 3rd most damage.
        String[] mostDamagingMember = TopThreeMembers[0].split(":");
        String[] middleDamagingMember = TopThreeMembers[1].split(":");
        String[] lowDamagingMember = TopThreeMembers[2].split(":");
        assertTrue(10 == Integer.parseInt(mostDamagingMember[1]));
        assertTrue(9 == Integer.parseInt(middleDamagingMember[1]));
        assertTrue(4 == Integer.parseInt(lowDamagingMember[1]));
    }
    @org.junit.Test
    public void HpAndDefLineup(){
        ArrayList<Character> characterList = new ArrayList<Character>();
        //The four lines below fill the characterlist with unique members
        characterList.add(Character.createFileCharacter("Tom", 8, 15, 8, CharacterType.HEALER));
        characterList.add(Character.createFileCharacter("Jerry", 2, 2, 2, CharacterType.MARKSMAN));
        characterList.add(Character.createFileCharacter("Spike", 4, 0, 6, CharacterType.SWORDSMAN));
        characterList.add(Character.createFileCharacter("Guy", 4, 8, 19, CharacterType.SHIELDUSER));
        HashMap<String, Character> bestCharacters = new HashMap<String, Character>();
        for (Character member : characterList) {
            String type = member.getType().toString();
            // iterates through each type
            if (!bestCharacters.containsKey(type) ||
                    // replaces member from same type if one has higher hp/def
                    (member.getHp() + member.getDef()) > (bestCharacters.get(type).getHp() + bestCharacters.get(type).getDef())) {
                bestCharacters.put(type, member);
            }
        }
        // prints the recommended lineup
        StringBuilder lineupDetails = new StringBuilder("The recommended lineup is:\n");
        for (Character member : bestCharacters.values()) {
            lineupDetails.append(member.getType() + " named " + member.getName() + " with " + member.getHp() + " HP and " + member.getDef() + " DEF.\n");
        }
        StringBuilder expectedLineup = new StringBuilder("The recommended lineup is:\n");
        //The for loop below goes through all the members in bestCharacters and adds the corresponding line
        //to expectedLineup.
        for (Character member : bestCharacters.values()) {
            if (member.getType() == CharacterType.HEALER){
                expectedLineup.append("HEALER" + " named " + "Tom" + " with " + 8 + " HP and " + 8 + " DEF.\n");
            }else if(member.getType() == CharacterType.MARKSMAN){
                expectedLineup.append( "MARKSMAN" + " named " + "Jerry" + " with " + 2 + " HP and " + 2 + " DEF.\n");
            }else if(member.getType() == CharacterType.SWORDSMAN){
                expectedLineup.append("SWORDSMAN" + " named " + "Spike" + " with " + 4 + " HP and " + 6 + " DEF.\n");
            }else if(member.getType() == CharacterType.SHIELDUSER){
                expectedLineup.append("SHIELDUSER" + " named " + "Guy" + " with " + 4 + " HP and " + 19 + " DEF.\n");
            }
        }
        //The line below checks if the lineup is being properly printed, by checking if the expected lineup
        //is equal to the actual lineup.
        assertEquals(expectedLineup.toString(),lineupDetails.toString());
    }
    @org.junit.Test
    public void CalculateDamage(){
        int finalDamage = 0;
        //The block of code below takes an example member, and calculates the damage they deal
        Character partyMemberType = Character.createFileCharacter("Jerry", 2, 2, 2, CharacterType.MARKSMAN);
        if (partyMemberType.getType() == CharacterType.HEALER){
            finalDamage = partyMemberType.getAtk();
        } else if (partyMemberType.getType() == CharacterType.SWORDSMAN){
            finalDamage = (int) (partyMemberType.getAtk() * 1.5);
        } else if (partyMemberType.getType() == CharacterType.MARKSMAN){
            finalDamage = (int) (partyMemberType.getAtk() * 1.15);
        } else if (partyMemberType.getType() == CharacterType.SHIELDUSER){
            finalDamage = partyMemberType.getAtk();
        }
        finalDamage = Math.round(finalDamage);
        //The damage dealt by the example member should be 2.
        assertTrue(2 == finalDamage);
    }
}
