/**
 *  Arfa Raja, Nethanya Dhaipule, Syed Omar
 *  March 20, 2024
 *  T12
 */

package ca.ucalgary.groupprojectgui;



class ShieldUser extends Character {

    /**
     * Constructs a ShieldUser object with the specified attributes.
     *
     * @param name    The unique name of the ShieldUser character.
     * @param hp      The initial hp of the ShieldUser character.
     * @param atk     The initial atk of the ShieldUser character.
     * @param def     The initial def of the ShieldUser character.
     */
    public ShieldUser(String name, int hp, int atk, int def) {

        super(name, hp, atk, def, CharacterType.SHIELDUSER); // Call character constructor

    }
}

