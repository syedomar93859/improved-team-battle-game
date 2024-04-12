package ca.ucalgary.groupprojectgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;

public class HelloController {

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

    @FXML
    private void initialize() {
        createType.getItems().setAll(CharacterType.values());
        editAttribute.getItems().setAll("Atk", "Def", "Hp", "Type");
    }

    @FXML
    private void handleLoad() {
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

    @FXML
    public void handleSaveFile() {
        if (teams != null && file != null && characterList != null) {
            FileSaver.save(file, characterList, teams);
            alertDisplay.setText("Success! File saved");
            alertDisplay.setStyle("-fx-text-fill: blue;");
        } else {
            alertDisplay.setText("No file loaded to save. Please save new file.");
            alertDisplay.setStyle("-fx-text-fill: red");
        }
    }

    @FXML
    public void handleSaveAsFile() {
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
        } else {
            alertDisplay.setText("Error! No game loaded to save.");
            alertDisplay.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void handleQuit() {
        System.exit(0);
    }


    @FXML
    private void about() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Message");
        alert.setContentText("Author: Nethanya Dhaiphule, Arfa Raja, Syed Omar\nVersion: v1.0\nThis is a ...description");
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
        String name = createName.getText();
        int hp = Integer.parseInt(createHp.getText());
        int atk = Integer.parseInt(createAtk.getText());
        Character newCharacter = getCharacter(name, hp, atk);

        // Check if character with the same name already exists
        for (Character character : characterList) {
            if (character.getName().equals(name)) {
                aboutMember.setText("A character with this name already exists.");
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

    private Character getCharacter(String name, int hp, int atk) {
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

    @FXML
    private void createTeamGUI() {
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

    @FXML
    private void editCharacterGUI() {
        String name = editName.getText();
        String attribute = editAttribute.getValue();
        String newValue = editNew.getText();

        for (Character character : characterList) {
            if (character.getName().equals(name)) {
                switch (attribute) {
                    case "Atk":
                        int newAtk = Integer.parseInt(newValue);
                        character.setAtk(newAtk);
                        aboutMember.setText(characterList.toString());
                        aboutTeam.setText(teams.toString());
                    case "Def":
                        int newDef = Integer.parseInt(newValue);
                        character.setDef(newDef);
                        aboutMember.setText(characterList.toString());
                        aboutTeam.setText(teams.toString());
                    case "Hp":
                        int newHp = Integer.parseInt(newValue);
                        character.setHp(newHp);
                        aboutMember.setText(characterList.toString());
                        aboutTeam.setText(teams.toString());
                    case "Type":
                        character.setType(CharacterType.valueOf(newValue));
                        aboutMember.setText(characterList.toString());
                        aboutTeam.setText(teams.toString());
                }
            }
        }
    }

    @FXML
    private Button Top3Button;
    @FXML
    private TextArea topThreeAtk;

    @FXML
    private void topThree() {
        String topThreeAtkDetails = Battlefield.AskTopThreeAtk((ArrayList<Character>) characterList);
        topThreeAtk.setText(topThreeAtkDetails);
    }

    @FXML
    private Button LineupButton;
    @FXML
    private TextArea lineup;

    @FXML
    private void lineup() {
        String lineupDetails = Battlefield.HPAndDefLineup((ArrayList<Character>) characterList);
        lineup.setText(lineupDetails);
    }

    @FXML
    private Button BossButton;
    @FXML
    private TextArea bossAtk;

    @FXML
    private void boss() {
        int bossAttack = Battlefield.CalculateBossAtk();
        bossAtk.setText("The Boss Atk is " + bossAttack);
    }

    @FXML
    private Button DmgButton;
    @FXML
    private TextArea charDmg;

    @FXML
    private void damage() {
        for (Character character : characterList) {
            int w = Battlefield.CalculateDamage(character);
            charDmg.setText("The Character Damage for is:" + w);
        }
    }

    @FXML
    private void displayMembers() {
        StringBuilder sb = new StringBuilder();
        for (Character character : characterList) {
            sb.append(character.toString()).append("\n");
        }
        aboutMember.setText(sb.toString());
    }

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

}

