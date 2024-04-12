/**
 *  Arfa Raja, Nethanya Dhaipule, Syed Omar
 *  March 20, 2024
 *  T12
 */
package ca.ucalgary.groupprojectgui;


import java.util.*;

public class Battlefield{

    public Battlefield() {

    }

    /**
     * CalculateBossAtk calculates how much damage the boss deals based on a random integer between 11 and 0 and taking
     *into account the character types og all the members
     * @return int with boss's damage
     */
    public static Integer CalculateBossAtk(ArrayList<Character> characterList) {
        int characterAmount = 0;
        int bossAdaptation = 0;
        for (Character member : characterList) {
            CharacterType charType = member.getType();
            if (charType.equals(CharacterType.HEALER)) {
                bossAdaptation++;
            } else if (charType.equals(CharacterType.SWORDSMAN)) {
                bossAdaptation = bossAdaptation + 4;
            } else if (charType.equals(CharacterType.MARKSMAN)) {
                bossAdaptation = bossAdaptation + 2;
            } else if (charType.equals(CharacterType.SHIELDUSER)) {
                bossAdaptation = bossAdaptation + 3;
                characterAmount++;
            }
        }
        Random randMultiplier = new Random();
        int highestDamageMultiplier = 10;
        int randomMultiplier = randMultiplier.nextInt(highestDamageMultiplier);
        int baseDamage = characterAmount * 100;
        if (randomMultiplier == 10) {
            return 5000;
        } else {
            return baseDamage;
        }
    }



    /**
     * AskTopThreeAtk goes through every party member to determine which are 3 highest dealing damage members.
     *
     */
    public static String AskTopThreeAtk(ArrayList<Character> characterList) {
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
        // Finally, TopThreeMembers is used to print the 3 members who have the highest attack power.
        String Top3Details = "The members who deal the most damage are:\n";
        for (String member : TopThreeMembers) {
            if (member != null) {
                Top3Details += member.split(":")[0] + " with damage: " + member.split(":")[1] + "\n";
            }
        }
        return Top3Details;
    }

    /**
     * HPAndDefLineup is a method that recommends a lineup of 4 characters, one from each type, based on the highest HP and DEF:
     */
    public static String HPAndDefLineup(ArrayList<Character> characterList) {
        HashMap<String, Character> bestCharacters = new HashMap<String, Character>();
        // iterates through characterList
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

        return lineupDetails.toString();
    }

    /**
     * The CalculateDamage method calculates the damage dealt by a member, using their class and sometimes a multiplier.
     *
     * @return int damage from member
     */
    public static String CalculateDamage(Character partyMember) {
        String name = partyMember.getName();
        double finalDamage = 0;
        if (partyMember.getType() == CharacterType.HEALER){
            finalDamage = partyMember.getAtk();
        } else if (partyMember.getType() == CharacterType.SWORDSMAN){
            finalDamage = partyMember.getAtk() * 1.5;
        } else if (partyMember.getType() == CharacterType.MARKSMAN){
            finalDamage = partyMember.getAtk() * 1.15;
        } else if (partyMember.getType() == CharacterType.SHIELDUSER){
            finalDamage = partyMember.getAtk();
        }
        return name + " deals " +Math.round(finalDamage);

    }

}
