import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class AddStudentWindow {
    TextField nameField;
    TextField nationalityField;
    TextField ageField;
    TextField gradeField;
    TextField enrollementField;
    TextField feesDue;
    List<Student> students;
    Stage stage;

    public AddStudentWindow(List<Student> studentList) {
        students = studentList;
        this.stage = new Stage();
        this.stage.setTitle("Add a new student");

        GridPane root = new GridPane();
        root.setPadding(new Insets(18));
        root.setAlignment(Pos.CENTER);
        root.setHgap(26);
        root.setVgap(10);

        Label nameLabel = new Label("Name:");
        nameField = new TextField();

        Label nationalityLabel = new Label("Nationality:");
        nationalityField = new TextField();

        Label ageLabel = new Label("Age:");
        ageField = new TextField();

        Label gradeLabel = new Label("Grade:");
        gradeField = new TextField();

        Label feesLabel = new Label("Fees due:");
        feesDue = new TextField();

        Label enrollementLabel = new Label("Enrollement Year:");
        enrollementField = new TextField();

        Button saveButton = new Button("Save");
        saveButton.setPrefWidth(75);
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(75);


        root.addRow(0, nameLabel, nameField);
        root.addRow(1, nationalityLabel, nationalityField);
        root.addRow(2, ageLabel, ageField);
        root.addRow(3, gradeLabel, gradeField);
        root.addRow(4, enrollementLabel, enrollementField);
        root.addRow(5, feesLabel, feesDue);
        root.addRow(6, cancelButton, saveButton);
        root.setHalignment(cancelButton, HPos.LEFT);
        root.setHalignment(saveButton, HPos.RIGHT);

        //on cancel click, close window
        cancelButton.setOnAction(actionEvent1 -> {
            close();
        });
        //on save
        saveButton.setOnAction(actionEvent -> saveStudent());

        this.stage.setScene(new Scene(root));
        stage.setResizable(false);
        this.stage.show();
    }

    private void close() {
        stage.close();
        new MainWindow();
    }

    private void saveStudent() {
        String name = nameField.getText();
        String nationality = nationalityField.getText();
        int age = 0, grade = 0, enrollementyear = 0;
        float fees = 0;
        try {
            age = Integer.parseInt(ageField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            grade = Integer.parseInt(gradeField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            enrollementyear = Integer.parseInt(enrollementField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            fees = Float.parseFloat(feesDue.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Student student = new Student(readIdFromFile(),name, nationality, age, grade, enrollementyear, 0, fees);
        try {
            writeStudentToFile(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
        close();
    }

    private void writeStudentToFile(Student student) throws IOException {
        FileWriter fw = new FileWriter("students.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(student.toString());
        bw.newLine();
        bw.close();
    }

    private int readIdFromFile() {
        File file = new File("studentid.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            int id;
            id = sc.nextInt();
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(String.valueOf(id+1));
            fileWriter.flush();
            fileWriter.close();
            return id;
        } catch (FileNotFoundException e) {
            try {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file, false);
                fileWriter.write("2");
                fileWriter.flush();
                fileWriter.close();
                return 1;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
