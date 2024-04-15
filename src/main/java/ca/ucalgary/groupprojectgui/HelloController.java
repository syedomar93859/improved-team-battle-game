/**
 *  Arfa Raja, Nethanya Dhaipule, Syed Omar
 *  April 12, 2024
 *  T12
 */

package ca.ucalgary.groupprojectgui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.*;


public class HelloController {

    // initialize variables
    private File file;
    private Stage stage;
    @FXML
    private MenuItem load;
    @FXML
    private MenuItem save;
    @FXML
    private MenuItem quit;
    @FXML
    private MenuItem about;
    @FXML
    private Button showBattleButton;
    @FXML
    private Button AboutMembersButton;
    @FXML
    private Button AboutTeamButton;
    @FXML
    private TextField alertDisplay;
    private List<Character> characterList = new ArrayList<>();
    private Map<String, List<Character>> teams = new HashMap<>();

    /**
     * Allows file to load when running from terminal
     *
     * @param testFile the file to be loaded
     */
    @FXML
    public void shellLoad(File testFile) {
        // Call the FileLoader class's load method
        FileLoader.load(testFile, characterList, teams);
        this.file = testFile; // Set
    }

    /**
     * initializes ComboBoxes
     */
    @FXML
    private void initialize() {
        createType.getItems().setAll(CharacterType.values());
        editAttribute.getItems().setAll("Atk", "Def", "Hp", "Type");
    }

    /**
     * Allows file to load in GUI
     */
    @FXML
    private void handleLoad() {
        alertDisplay.clear();
        // Create a file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            // Call the FileLoader class's load method
            FileLoader.load(selectedFile, characterList, teams);
            alertDisplay.setText("File loaded successfully!");
            alertDisplay.setStyle("-fx-text-fill: blue;");
            this.file = selectedFile; // Set the file field
        } else {
            // Show an error message if no file was selected
            alertDisplay.setText("Failed to load file or invalid file format.");
            alertDisplay.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Allows file to save in GUI
     */
    @FXML
    public void handleSaveFile() {
        alertDisplay.clear();
        if (teams != null && file != null && characterList != null) {
            FileSaver.save(file, characterList, teams);
            alertDisplay.setText("Success! File saved");
            alertDisplay.setStyle("-fx-text-fill: blue;");

        // error checking
        } else {
            alertDisplay.setText("No file loaded to save. Please save new file.");
            alertDisplay.setStyle("-fx-text-fill: red");
        }
    }

    /**
     * Allows file to save as new file in GUI
     */
    @FXML
    public void handleSaveAsFile() {
        alertDisplay.clear();
        if (teams != null && characterList != null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            File selectedFile = fileChooser.showSaveDialog(stage);
            if (selectedFile != null) {
                if (FileSaver.save(file, characterList, teams)) {
                    alertDisplay.setText("Success! File Saved");
                    alertDisplay.setStyle("-fx-text-fill: blue;");
                    this.file = selectedFile;
                }
            }

        // error checking
        } else {
            alertDisplay.setText("Error! No game loaded to save.");
            alertDisplay.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Allows file to quit in GUI
     */
    @FXML
    private void handleQuit() {
        System.exit(0);
    }

    /**
     * Displays about authors and program in GUI
     */
    @FXML
    private void about() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Message");
        alert.setContentText("Authors: Nethanya Dhaiphule, Arfa Raja, Syed Omar\nVersion: v1.0\nThis is a program to track information about party\ncharacters and teams.");
        alert.showAndWait();
    }

    // character attributes
    @FXML
    private TextField createName;
    @FXML
    private TextField createHp;
    @FXML
    private TextField createAtk;
    @FXML
    private TextField createDef;
    @FXML
    private ComboBox<CharacterType> createType;
    @FXML
    private Button createCharac;
    @FXML
    private TextArea aboutMember;

    /**
     * Create A Character
     */
    @FXML
    private void createCharacter() {
        alertDisplay.clear();
        String name = createName.getText();
        String hpStr = createHp.getText();
        String atkStr = createAtk.getText();
        String defStr = createDef.getText();

        // Validate if the numeric fields contain only numbers
        if (!isValidInteger(hpStr) || !isValidInteger(atkStr) || !isValidInteger(defStr)) {
            alertDisplay.setText("Error! hp, atk, and def only take in numeric values");
            alertDisplay.setStyle("-fx-text-fill: red");
            return;
        }

        int hp = Integer.parseInt(createHp.getText());
        int atk = Integer.parseInt(createAtk.getText());
        Character newCharacter = getCharacter(name, hp, atk);

        // Check if character with the same name already exists
        for (Character character : characterList) {
            if (character.getName().equals(name)) {
                alertDisplay.setText("A character with this name already exists.");
                alertDisplay.setStyle("-fx-text-fill: red");
                return;
            }
        }

        characterList.add(newCharacter);
        StringBuilder sb = new StringBuilder();
        for (Character character : characterList) {
            sb.append(character.toString());
            sb.append("\n"); // for newline
        }
    }

    /**
     * Used in createCharacter to get a character
     *
     * @param name String name of character
     * @param atk int atk of character
     * @param hp int hp of character
     *
     * @return Character object
     */
    private Character getCharacter(String name, int hp, int atk) {
        alertDisplay.clear();
        int def = Integer.parseInt(createDef.getText());
        CharacterType type = createType.getValue();
        return switch (type) {
            // create HEALER object
            case HEALER -> new Healer(name, hp, atk, def);

            // create MARKSMAN object
            case MARKSMAN -> new Marksman(name, hp, atk, def);

            // create SWORDSMAN object
            case SWORDSMAN -> new Swordsman(name, hp, atk, def);

            // create SHIELDUSER object
            case SHIELDUSER -> new ShieldUser(name, hp, atk, def);
        };
    }

    @FXML
    private TextField teamNameText;
    @FXML
    private TextField teamCharacText;
    @FXML
    private Button createTeamButton;
    @FXML
    private TextArea aboutTeam;

    /**
     * Used to create a team in the GUI
     */
    @FXML
    private void createTeamGUI() {
        alertDisplay.clear();
        String teamName = teamNameText.getText();
        List<Character> team = new ArrayList<>();
        String[] characterNames = teamCharacText.getText().split(","); // split by comma
        for (String characterName : characterNames) {
            characterName = characterName.trim(); // remove leading and trailing spaces
            for (Character character : characterList) {
                if (character.getName().equals(characterName)) {
                    team.add(character); // adds character to team list
                }
            }
        }
        Team newTeam = Team.createTeam(teamName, team); // creates a new Team object
        teams.put(newTeam.getName(), newTeam.getMembers()); // adds team list to all teams hashmap

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<Character>> entry : teams.entrySet()) {
            sb.append("Team Name: ").append(entry.getKey()).append("\n");
            sb.append("Members: \n");
            for (Character character : entry.getValue()) {
                sb.append(character.toString()).append("\n");
            }
            sb.append("\n");
        }
    }

    @FXML
    private TextField editName;
    @FXML
    private ComboBox<String> editAttribute;
    @FXML
    private TextField editNew;
    @FXML
    private Button editButton;

    /**
     * Used to edit a character in the GUI
     */
    @FXML
    private void editCharacterGUI() {

        // attributes
        alertDisplay.clear();
        String name = editName.getText();
        String attribute = editAttribute.getValue();
        String newValue = editNew.getText();

        // iterate through list
        for (Character character : characterList) {
            if (character.getName().equals(name)) {
                switch (attribute) {
                    case "Atk":
                        try {
                            int newAtk = Integer.parseInt(newValue);
                            character.setAtk(newAtk);
                        // error checking
                        }
                        catch (NumberFormatException e) {
                            alertDisplay.setText("Error atk must be valid integer");
                            alertDisplay.setStyle("-fx-text-fill: red");
                        }
                        break;
                    case "Def":
                        try {
                            int newDef = Integer.parseInt(newValue);
                            character.setDef(newDef);
                        }
                        // error checking
                        catch (NumberFormatException e) {
                            alertDisplay.setText("Error atk must be valid integer");
                            alertDisplay.setStyle("-fx-text-fill: red");
                        }
                        break;
                    case "Hp":
                        try {
                        int newHp = Integer.parseInt(newValue);
                        character.setHp(newHp);
                        }
                        // error checking
                        catch (NumberFormatException e) {
                            alertDisplay.setText("Error atk must be valid integer");
                            alertDisplay.setStyle("-fx-text-fill: red");
                        }
                        break;
                    case "Type":
                        character.setType(CharacterType.valueOf(newValue));
                        break;
                }
            }
        }
    }

    @FXML
    private Button Top3Button;
    @FXML
    private TextArea topThreeAtk;

    /**
     * Used to run special method AskTopThreeAtk in GUI
     */
    @FXML
    private void topThree() {
        String topThreeAtkDetails = Battlefield.AskTopThreeAtk((ArrayList<Character>) characterList);
        topThreeAtk.setText(topThreeAtkDetails);
    }

    @FXML
    private Button LineupButton;
    @FXML
    private TextArea lineup;

    /**
     * Used to run special method HPAndDefLineup in GUI
     */
    @FXML
    private void lineup() {
        alertDisplay.clear();
        String lineupDetails = Battlefield.HPAndDefLineup((ArrayList<Character>) characterList);

        lineup.setText(lineupDetails);
    }

    @FXML
    private Button BossButton;
    @FXML
    private TextArea bossAtk;

    /**
     * Used to run special method CalculateBossAtk in GUI
     */
    @FXML
    private void boss() {
        int bossAttack = Battlefield.CalculateBossAtk((ArrayList<Character>) characterList);
        bossAtk.setText("The Boss Atk is " + bossAttack);
    }

    @FXML
    private Button DmgButton;
    @FXML
    private TextArea charDmg;

    /**
     * Used to run special method CalculateDamage in GUI
     */
    @FXML
    private void damage() {
        for (Character character : characterList) {
            String w = Battlefield.CalculateDamage(character, (ArrayList<Character>) characterList);
            charDmg.setText(w);
        }
    }

    /**
     * Used to display all members in GUI
     */
    @FXML
    private void displayMembers() {
        StringBuilder sb = new StringBuilder();
        for (Character character : characterList) {
            sb.append(character.toString()).append("\n");
        }
        aboutMember.setText(sb.toString());
    }

    /**
     * Used to display all teams in GUI
     */
    @FXML
    private void displayTeams() {
        aboutTeam.clear();// Clear the text area

        // Iterate through the teams map and append information about each team to the text area
        for (Map.Entry<String, List<Character>> entry : teams.entrySet()) {
            aboutTeam.appendText("Team Name: " + entry.getKey() + "\n");
            aboutTeam.appendText("Members: \n");
            for (Character character : entry.getValue()) {
                aboutTeam.appendText(character.toString() + "\n");
            }
            aboutTeam.appendText("\n");
        }
    }

    /**
     * Used to validate integers
     *
     * @param input String value of input
     *
     * @return True if a valid integer
     */
    public boolean isValidInteger(String input) { //check if input values are numeric values
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
