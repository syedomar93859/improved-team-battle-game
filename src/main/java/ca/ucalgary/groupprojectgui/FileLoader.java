/**
 *  Arfa Raja, Nethanya Dhaipule, Syed Omar
 *  March 20, 2024
 *  T12
 */
package ca.ucalgary.groupprojectgui;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileLoader {

    /**
     * Reads a file with characters and teams
     *
     */
    public static void load(File file, List<Character> characterList, Map<String, List<Character>> teams) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("Characters")) { // reads characters
                    loadCharacters(br, characterList);
                } else if (line.equals("Teams")) { // reads teams
                    loadTeams(br, teams, characterList);
                }
            }
            System.out.println("Data loaded successfully from " + file.getName()); // success message
        } catch (IOException e) {
            System.out.println("An error occurred while loading data from file: " + e.getMessage()); // error message
        }
    }

    /**
     * Loads the characters from the file
     *
     */
    private static void loadCharacters(BufferedReader br, List<Character> characterList) throws IOException {
        String line;
        while ((line = br.readLine()) != null && !line.equals("")) {
            String[] parts = line.split(",");
            String name = parts[0]; // reads the name
            CharacterType type = CharacterType.valueOf(parts[1]); // reads the type
            int hp = Integer.parseInt(parts[2]); // reads the hp
            int atk = Integer.parseInt(parts[3]); // reads the atk
            int def = Integer.parseInt(parts[4]); // reads the def
            Character character = Character.createFileCharacter(name, hp, atk, def, type); // creates character
            characterList.add(character); // adds character to characterList
        }
    }

    /**
     * Loads the teams from the file
     *
     */
    private static void loadTeams(BufferedReader br, Map<String, List<Character>> teams, List<Character> characterList) throws IOException {
        String line;
        while ((line = br.readLine()) != null && !line.equals("")) {
            String[] parts = line.split(",");
            String teamName = parts[0]; // reads the team name
            String[] memberNames = parts[1].split(";"); // reads the member names
            List<Character> teamMembers = new ArrayList<>();
            for (String memberName : memberNames) {
                for (Character character : characterList) {
                    if (character.getName().equals(memberName)) {
                        teamMembers.add(character);
                        break;
                    }
                }
            }
            teams.put(teamName, teamMembers); // adds team to teams hashmap
        }
    }
}
