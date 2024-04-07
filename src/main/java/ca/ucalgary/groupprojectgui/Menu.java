/**
 *  Arfa Raja, Nethanya Dhaipule, Syed Omar
 *  March 20, 2024
 *  T09
 */

package ca.ucalgary.groupprojectgui;

import java.io.File;
import java.util.*;

public class Menu {
    private static final Scanner scanner; // scanner to read inputs
    private static final ArrayList<String> options; // arraylist of menu options

    static { // menu options
        scanner = new Scanner(System.in);
        options = new ArrayList();
        options.add("Exit");
        options.add("Create Team");
        options.add("Create Character");
        options.add("Update atk for character");
        options.add("Update def for character");
        options.add("Update hp for character");
        options.add("Update class for character");
        options.add("About Members");
        options.add("Determine boss's atk");
        options.add("Ask for the top 3 members based on atk");
        options.add("Recommend a lineup based on hp and def");
        options.add("Determine character damage value");
        options.add("Save data to file");
        options.add("Load data from file");
    }

    // Below is the intro message that shows up above the menu.
    private static String optMessage = """
            \s
                         
             Store and access details of the options and actions of the members in the party and also details about foe.
             \tMenu Options
             """;

    static {
        StringBuilder sb = new StringBuilder(); // builds menu to string
        sb.append(optMessage);
        for (int i = 0; i < options.size(); i++) {
            sb.append(String.format("\t%d. %s\n", i, options.get(i)));
        }
        optMessage = sb.toString();
    }

    /**
     *  method that loops menu and asks for inputs
     *
     */
    public static void menuLoop() {

        ArrayList<Character> characterList = new ArrayList<>(); // list of all characters
        Map<String, List<Character>> teams = new HashMap<>(); // list of all teams with characters
        System.out.println(optMessage);
        String choice = scanner.nextLine();

        for (int option = Integer.parseInt(choice); option != 0; option = Integer.parseInt(choice)) {
            if (option > 0 && option < options.size()) {
                System.out.printf("Selected option %d, %s%n", option, options.get(option));
                System.out.println("Press any Enter key to continue");
                scanner.nextLine();
            }

            switch (option) {
                case 1: // creates a team
                    if (characterList.isEmpty()) { // ensures character exists first
                        System.out.println("No characters available. Please create a character first.");
                    } else {
                        System.out.println("Enter the team's name:"); // asks for team name
                        String teamName = scanner.nextLine();
                        List<Character> team = new ArrayList<>();
                        String addMore;
                        do { // asks about adding initial character
                            System.out.println("Enter the name of the character you want to add to the team:");
                            String characterName = scanner.nextLine();
                            for (Character character : characterList) {
                                if (character.getName().equals(characterName)) {
                                    team.add(character); // adds character to team list
                                    break;
                                }
                            } // asks about adding additional characters
                            System.out.println("If you want to add more characters press 'y', else press any key.");
                            addMore = scanner.nextLine();
                        } while (addMore.equals("y"));
                        Team newTeam = Team.createTeam(teamName, team); // creates a new Team object
                        teams.put(newTeam.getName(), newTeam.getMembers()); // adds team list to all teams hashmap
                    }
                    break;


                case 2: // create a character

                    // character type
                    System.out.println("Choose the character's type (HEALER, MARKSMAN, SWORDSMAN, SHIELDUSER):");
                    String type = scanner.nextLine();

                    // character name
                    System.out.println("Enter the new character's name:");
                    String name = scanner.nextLine();

                    // character hp
                    System.out.println("Enter the character's hp:");
                    int hp = scanner.nextInt();

                    // character atk
                    System.out.println("Enter the character's atk:");
                    int atk = scanner.nextInt();

                    // character def
                    System.out.println("Enter the character's def:");
                    int def = scanner.nextInt();

                    // creates character Object
                    Character newCharacter;

                    switch (type) {
                        // create HEALER object
                        case "HEALER":
                            newCharacter = new Healer(name, hp, atk, def);
                            characterList.add(newCharacter);
                            break;

                        // create MARKSMAN object
                        case "MARKSMAN":
                            newCharacter = new Marksman(name, hp, atk, def);
                            characterList.add(newCharacter);
                            break;

                        // create SWORDSMAN object
                        case "SWORDSMAN":
                            newCharacter = new Swordsman(name, hp, atk, def);
                            characterList.add(newCharacter);
                            break;

                        // create SHIELDUSER object
                        case "SHIELDUSER":
                            newCharacter = new ShieldUser(name, hp, atk, def);
                            characterList.add(newCharacter);
                            break;

                        // invalid type
                        default:
                            System.out.println("Invalid character type. Please try again.");
                            return;
                    }
                    System.out.println("Character " + name + " has been created."); // success message

                    break;

                case 3:
                    System.out.println("Enter the character's name:");
                    name = scanner.nextLine();

                    // updates characters atk
                    System.out.println("Enter the new attack value:");
                    int newAtk = scanner.nextInt();
                    for (Character character : characterList) {
                        if (character.getName().equals(name)) {
                            character.setAtk(newAtk);
                            System.out.println("Character " + name + "'s attack has been updated.");
                            break;
                        }
                    }
                    break;

                case 4:
                    System.out.println("Enter the character's name:");
                    name = scanner.nextLine();

                    // updates characters def
                    System.out.println("Enter the new defense value:");
                    int newDef = scanner.nextInt();
                    for (Character character : characterList) {
                        if (character.getName().equals(name)) {
                            character.setDef(newDef);
                            System.out.println("Character " + name + "'s defense has been updated.");
                            break;
                        }
                    }
                    break;

                case 5:
                    System.out.println("Enter the character's name:");
                    name = scanner.nextLine();

                    // updates characters hp
                    System.out.println("Enter the new health value:");
                    int NewHp = scanner.nextInt();
                    for (Character character : characterList) {
                        if (character.getName().equals(name)) {
                            character.setHp(NewHp);
                            System.out.println("Character " + name + "'s health has been updated.");
                            break;
                        }
                    }
                    break;

                case 6:
                    System.out.println("Enter the character's name:");
                    name = scanner.nextLine();

                    // updates characters type
                    System.out.println("Choose the character's type (HEALER, MARKSMAN, SWORDSMAN, SHIELDUSER):");
                    String newType = scanner.nextLine();
                    for (Character character : characterList) {
                        if (character.getName().equals(name)) {
                            character.setType(CharacterType.valueOf(newType));
                            System.out.println("Character " + name + "'s type has been updated.");
                            break;
                        }
                    }
                    break;

                case 7:
                    // allows player to view members or teams with members
                    System.out.println("Would you like to view members (1) or teams? (2)");
                    int t = scanner.nextInt();
                    if (t == 1) {
                        if (characterList.isEmpty()) {
                            // if no characters
                            System.out.println("No characters available. Please create a character first.");
                        } else {
                            Collections.sort(characterList);  // sort by name
                            System.out.println(characterList);
                        }
                    } else if (t == 2) {
                        if (teams.isEmpty()) {
                            // if no teams
                            System.out.println("No teams available. Please create a team first.");
                        } else {
                            System.out.print(teams);
                        }
                    } else {
                        // if invalid entry
                        System.out.println("Invalid entry.");
                    }
                    break;

                case 8: // Calculate Boss Attack
                    int x = Battlefield.CalculateBossAtk();
                    System.out.println("The Boss Atk is " + x);
                    break;

                case 9: // Displays top 3 members with highest attack
                    Battlefield.AskTopThreeAtk(characterList);
                    break;

                case 10: // Recommends a lineup of 4 - (1 from each type) based on the highest HP and DEF
                    Battlefield.HPAndDefLineup(characterList);
                    break;

                case 11: // Determines character dmg value based on atk and class
                    System.out.println("Enter the character's name:");
                    name = scanner.nextLine();

                    for (Character character : characterList) {
                        if (character.getName().equals(name)) {
                            int y = Battlefield.CalculateDamage(character);
                            System.out.println("The Character Damage is" + y);
                            break;
                        }
                    }
                    break;

                case 12:
                    // saves characterList and teams to file
                    save(characterList, teams);
                    break;

                case 13:
                    // loads characterList and teams from file
                    load(characterList, teams);
                    break;

                default:
                    // invalid option
                    System.out.printf("Option %d not recognizable%n", option);
            }

            // loops menu
            System.out.println("\nPress any Enter key to see menu again");
            scanner.nextLine();
            System.out.println(optMessage);
            choice = scanner.nextLine();
        }System.out.println("You have exited the battle.\nFIGHT EXITED");
    }

    /**
     *  method that saves the characterList and teams calling to FileSaver
     */
    private static void save(List<Character> characterList, Map<String, List<Character>> teams) {
        String filename;
        File file;
        do {
            System.out.println("Enter a filename:"); // asks for filename
            filename = scanner.nextLine().trim();
            file = new File(filename);
        } while (file.exists() && !file.canWrite());

        boolean success = FileSaver.save(file, characterList, teams); // calls to FileSaver
        if (success) {
            System.out.println("Data saved successfully to " + filename); // success message
        } else {
            System.out.println("Failed to save data to " + filename); // error message
        }
    }
    /**
     *  method that saves the characterList and teams calling to FileLoader
     */
    private static void load(List<Character> characterList, Map<String, List<Character>> teams) {
        String filename;
        File file;
        do {
            System.out.println("Enter a filename:"); // asks for filename
            filename = scanner.nextLine().trim();
            file = new File(filename);
        } while (!file.exists() || !file.canRead());

        FileLoader.load(file, characterList, teams); // calls to FileLoader
    }
}
