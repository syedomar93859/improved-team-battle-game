/**
 *  Arfa Raja, Nethanya Dhaipule, Syed Omar
 *  March 20, 2024
 *  T12
 */
package ca.ucalgary.groupprojectgui;


import java.util.List;

public abstract class Character implements Comparable<Character> {

    // attributes

    protected String name; // name of character
    protected int hp; // health of character
    protected int atk; // attack of character
    protected int def; // defense of character
    protected CharacterType type; // class of character

    /**
     * Constructs a Character object with the specified attributes.
     *
     * @param name name
     * @param hp   health
     * @param atk  attack
     * @param def  defense
     * @param type class
     */
    public Character(String name, int hp, int atk, int def, CharacterType type) {

        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.type = type;
    }

    // Getters and setters

    /**
     * Retrieves the unique name of the character.
     *
     * @return the name of the character
     */
    public String getName() {
        return name; // current name
    }

    /**
     * Retrieves the hp of the character.
     *
     * @return the hp of the character
     */
    public int getHp() {
        return hp; // current hp
    }

    /**
     * Retrieves the atk of the character.
     *
     * @return the atk of the character
     */
    public int getAtk() {
        return atk; // current atk
    }

    /**
     * Retrieves the def of the character.
     *
     * @return the def of the character
     */
    public int getDef() {
        return def; // current def
    }

    /**
     * Retrieves the class of the character.
     *
     * @return the class of the character
     */
    public CharacterType getType() {
        return type; // current type
    }

    /**
     * Sets the hp of the character to the specified value.
     *
     * @param newHp the new hp
     *
     */
    public void setHp(int newHp) {
        this.hp = newHp; // updated hp
    }

    /**
     * Sets the atj of the character to the specified value.
     *
     * @param newAtk the new hp
     *
     */
    public void setAtk(int newAtk) {
        this.atk = newAtk; // updated atk
    }

    /**
     * Sets the def of the character to the specified value.
     *
     * @param newDef the new hp
     *
     */
    public void setDef(int newDef) {
        this.def = newDef; // updated def
    }

    /**
     * Sets the class of the character to the specified value.
     *
     * @param newType the new hp
     *
     */
    public void setType(CharacterType newType) {
        this.type = newType; // updated type
    }

    /**
     * Formats the return of toString when viewing About Members
     *
     * @return the formatted String
     *
     */
    @Override
    public String toString() {
        // formatted string
        return "Name: " + this.name + ", HP: " + this.hp + ", ATK: " + this.atk + ", DEF: " + this.def + ", TYPE: " + this.type;
    }

    /**
     * checks if a character exists
     *
     * @return true if character exits, false otherwise
     *
     */
    public static boolean CheckCharacter(int option, List<Character> characterList) {
        boolean exists = false;
        switch (option) {
            case 1:
                // check if there is a Healer in team
                for (Character character : characterList) {
                    if (character.getType() == CharacterType.HEALER) {
                        exists = true;
                        break;
                    }
                }
            case 2:
                // check if there is a Marksman in team
                for (Character character : characterList) {
                    if (character.getType() == CharacterType.MARKSMAN) {
                        exists = true;
                        break;
                    }
                }

            case 3:
                // check if there is a Swordsman in team
                for (Character character : characterList) {
                    if (character.getType() == CharacterType.SWORDSMAN) {
                        exists = true;
                        break;
                    }
                }

            case 4:
                // check if there is a ShieldUser in team
                for (Character character : characterList) {
                    if (character.getType() == CharacterType.SHIELDUSER) {
                        exists = true;
                        break;
                    }
                }

            default:
                System.out.println("Invalid option."); // if
                break;
        }
        return exists;
    }

    /**
     * Sorts the characters alphabetically when viewing About Members
     *
     * @return the formatted list
     *
     */
    @Override
    public int compareTo(Character other) {
        return this.name.compareTo(other.name);  // sort by name
    }

    /**
     * Creates the characters from the file
     *
     * @return the new Character object
     */
    static Character createFileCharacter(String name, int hp, int atk, int def, CharacterType type) {
        switch (type) {
            case HEALER:
                return new Healer(name, hp, atk, def);
            case MARKSMAN:
                return new Marksman(name, hp, atk, def);
            case SWORDSMAN:
                return new Swordsman(name, hp, atk, def);
            case SHIELDUSER:
                return new ShieldUser(name, hp, atk, def);
            default:
                throw new IllegalArgumentException("Invalid character type: " + type);
        }
    }

}


