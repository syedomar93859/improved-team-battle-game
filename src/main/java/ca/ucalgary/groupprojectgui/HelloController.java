package ca.ucalgary.groupprojectgui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private TextField alertDisplay;
    private List<Character> characterList = new ArrayList<>();
    private Map<String, List<Character>> teams = new HashMap<>();

    @FXML
    private void initialize() {

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
        if (teams != null && file != null && characterList != null) {
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
    private TextField createType;
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
        int def = Integer.parseInt(createDef.getText());
        CharacterType type = CharacterType.valueOf((createType.getText()));
        Character newCharacter;

        switch (type) {
            // create HEALER object
            case HEALER:
                newCharacter = new Healer(name, hp, atk, def);
                characterList.add(newCharacter);
                aboutMember.setText(characterList.toString());
                break;

            // create MARKSMAN object
            case MARKSMAN:
                newCharacter = new Marksman(name, hp, atk, def);
                characterList.add(newCharacter);
                aboutMember.setText(characterList.toString());
                break;

            // create SWORDSMAN object
            case SWORDSMAN:
                newCharacter = new Swordsman(name, hp, atk, def);
                characterList.add(newCharacter);
                aboutMember.setText(characterList.toString());
                break;

            // create SHIELDUSER object
            case SHIELDUSER:
                newCharacter = new ShieldUser(name, hp, atk, def);
                characterList.add(newCharacter);
                aboutMember.setText(characterList.toString());
                break;
        }
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
        String characterName = teamCharacText.getText();
        for (Character character : characterList) {
            if (character.getName().equals(characterName)) {
                team.add(character); // adds character to team list
            }
            Team newTeam = Team.createTeam(teamName, team); // creates a new Team object
            teams.put(newTeam.getName(), newTeam.getMembers()); // adds team list to all teams hashmap
        }
        aboutTeam.setText(teams.toString());
    }

    @FXML
    private TextField editName;
    @FXML
    private TextField editAttribute;
    @FXML
    private TextField editNew;
    @FXML
    private Button editButton;

    @FXML
    private void editCharacterGUI() {
        String name = editName.getText();
        String attribute = editAttribute.getText();
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
}



