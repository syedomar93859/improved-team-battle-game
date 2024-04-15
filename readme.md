# CPSC 233 W24 A3
# Authors: Arfa Raja, Nethanya Dhaipule, Syed Omar

# Party Creation and Battle Analyzer

The code and scenebuilder GUI work together to create a party from the details 
stored in a file, or details entered by the player in the GUI. Characters make up 
a team, and each character has a name, hp, atk, def and type. These attributes of
the character can also be changed. After creating your team, you can generate 
details about it such as:

- Display the details of each member
- Show all the characters for all teams created so far
- The three highest dealing members in the team
- Create a lineup based on atk and def
- Calculate the damage of each character in this team and display their attack damages from highest to lowest.
- Calculate the boss's atk


# How to run:

From Shell:

 - open in: GroupProjectGUI\target\classes
 - run: java --module-path  "C:\Program Files\Java\javafx-sdk-21.0.2\lib" --add-modules javafx.controls,javafx.fxml ca.ucalgary.groupprojectgui.Shell testFile


Jar file:
 - open in: GroupProjectGUI
 - run: java --module-path  "C:\Program Files\Java\javafx-sdk-21.0.2\lib" --add-modules javafx.controls,javafx.fxml -jar GroupProjectGUI.jar


