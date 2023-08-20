import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ExpelWindow {
    Stage stage;
    Label title;
    Button expelButton;
    Button cancelButton;
    ComboBox<String> comboBox;
    List<Student> studentList;
    public ExpelWindow(List<Student> studentList) {
        this.studentList = studentList;
        stage = new Stage();
        stage.setTitle("Expel a student");
        title = new Label("Choose a student to expel");
        expelButton = new Button("Expel");
        expelButton.setPrefWidth(150);
        cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(150);

        comboBox = new ComboBox<>(FXCollections.observableList(studentList.stream().map(Student::getName).collect(Collectors.toList())));


        cancelButton.setOnAction(actionEvent -> {
            stage.close();
            new  MainWindow();
        });

        expelButton.setOnAction(actionEvent -> {
            String studentName = comboBox.getValue();
            deleteStudent(studentName);
            System.out.println(studentName);
            stage.close();
            new  MainWindow();
        });

        VBox vBox = new VBox(comboBox, expelButton, cancelButton);
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(20));
        vBox.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(vBox, 200, 150));
        stage.show();
    }

    private void deleteStudent(String studentName) {
        for(Student s: studentList){
            if(s.getName().equals(studentName)){
                studentList.remove(s);
                writeStudentListToFile();
                return;
            }
        }
    }

    private void writeStudentListToFile() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("students.txt", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(Student s: studentList) {
            try {
                fileWriter.write(s.toString() + '\n');
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
