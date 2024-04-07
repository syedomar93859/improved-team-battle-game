/**
 *  Arfa Raja, Nethanya Dhaipule, Syed Omar
 *  March 20, 2024
 *  T12
 */
package ca.ucalgary.groupprojectgui;


class Healer extends Character {

    /**
     * Constructs a Healer object with the specified attributes.
     *
     * @param name    The unique name of the healer character.
     * @param hp      The initial hp of the healer character.
     * @param atk     The initial atk of the healer character.
     * @param def     The initial def of the healer character.
     */
    public Healer(String name, int hp, int atk, int def) {
        super(name, hp, atk, def, CharacterType.HEALER);  // Call character constructor

    }
}
