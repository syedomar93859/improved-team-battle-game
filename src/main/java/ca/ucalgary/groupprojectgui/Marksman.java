/**
 *  Arfa Raja, Nethanya Dhaipule, Syed Omar
 *  March 20, 2024
 *  T12
 */
package ca.ucalgary.groupprojectgui;


class Marksman extends Character {

    /**
     * Constructs a Marksman object with the specified attributes.
     *
     * @param name    The unique name of the Marksman character.
     * @param hp      The initial hp of the Marksman character.
     * @param atk     The initial atk of the Marksman character.
     * @param def     The initial def of the Marksman character.
     */
    public Marksman(String name, int hp, int atk, int def) {

        super(name, hp, atk, def, CharacterType.MARKSMAN); // Call character constructor

    }
}
