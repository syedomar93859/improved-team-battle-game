/**
 *  Arfa Raja, Nethanya Dhaipule, Syed Omar
 *  March 20, 2024
 *  T12
 */
package ca.ucalgary.groupprojectgui;


import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name; // name of team
    private List<Character> members; // members in team from list

    /**
     * Constructs a Team object
     *
     * @param name name of team
     *
     */
    public Team(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    /**
     * adds member to team
     */
    public void addMember(Character character) {
        this.members.add(character); // adds character to memberlist
    }

    // getters

    /**
     * Retrieves the name of the team.
     *
     * @return the name of the team.
     */
    public String getName() {
        return this.name; // gets name of team
    }

    /**
     * Retrieves the members of the team.
     *
     * @return the members of the team.
     */
    public List<Character> getMembers() {
        return this.members; // gets name of members in team
    }

    /**
     * creates a team
     *
     * @param teamName the name of the team to be created
     * @param teamMembers the list of members to be added
     *
     * return the Team object
     */
    public static Team createTeam(String teamName, List<Character> teamMembers) {
        Team newTeam = new Team(teamName);
        for (Character member : teamMembers) {
            newTeam.addMember(member);
        }
        System.out.println("Team " + teamName + " has been created.");
        return newTeam;
    }
}

