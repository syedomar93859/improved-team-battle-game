/**
 *  Arfa Raja, Nethanya Dhaipule, Syed Omar
 *  March 20, 2024
 *  T12
 */
package ca.ucalgary.groupprojectgui;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileSaver {

    /**
     * Method to save character data and team data to a CSV file
     *
     * @return true if save successful, false if not
     */
    public static boolean save(File file, List<java.lang.Character> characterList, Map<String, List<java.lang.Character>> teams) {
        try (FileWriter fw = new FileWriter(file)) {
            // Save characters
            fw.write("Characters\n"); // Write a header to distinguish character data
            for (java.lang.Character character : characterList) {
                // Write each character's details in CSV format: name,type,hp,atk,def
                fw.write(String.format("%s,%s,%d,%d,%d\n",
                        character.getName(),
                        character.getType().toString(),
                        character.getHp(),
                        character.getAtk(),
                        character.getDef()));
            }

            // Save teams
            fw.write("\nTeams\n"); // Write a header to distinguish team data
            for (Map.Entry<String, List<java.lang.Character>> entry : teams.entrySet()) {
                // Iterate over each team entry in the teams map
                fw.write(entry.getKey() + ","); // Write the team name followed by a comma
                List<java.lang.Character> teamMembers = entry.getValue(); // Get the list of team members
                for (java.lang.Character member : teamMembers) {
                    fw.write(member.getName() + ";");
                }
                fw.write("\n");
            }

            return true;
        } catch (IOException ioe) {
            return false;
        }
    }
}
