/**
 *  Arfa Raja, Nethanya Dhaipule, Syed Omar
 *  March 20, 2024
 *  T12
 */
package ca.ucalgary.groupprojectgui;



class Swordsman extends Character {

    /**
     * Constructs a Swordsman object with the specified attributes.
     *
     * @param name    The unique name of the Swordsman character.
     * @param hp      The initial hp of the Swordsman character.
     * @param atk     The initial atk of the Swordsman character.
     * @param def     The initial def of the Swordsman character.
     */
    public Swordsman(String name, int hp, int atk, int def) {

        super(name, hp, atk, def, CharacterType.SWORDSMAN); // Call character constructor

    }
}

