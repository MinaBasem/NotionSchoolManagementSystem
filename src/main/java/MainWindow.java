import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javafx.scene.layout.BorderPane;

public class MainWindow {
    List<Student> studentList;
    VBox studentsTable;
    VBox studentInfo;
    Label name;
    Label age;
    Label nationality;
    Label registrationdate;
    Label pendingFees;
    Label grade;
    Label date;
    Label time;

    public MainWindow() {
        studentList = new ArrayList<>();

        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();
        borderPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.FULL)));
        //borderPane.setStyle("-fx-border-color: black");

        Label welcomeText = new Label("Good Morning, admin");
        welcomeText.setFont(Font.font("Arial", 12.75));
        welcomeText.setPadding(new Insets(5));                      // WELCOME TEXT PADDING

        Label placeholderText = new Label(" Placeholder Text");
        placeholderText.setFont(Font.font("Arial", 12.75));
        placeholderText.setPadding(new Insets(5));

        Button addStudentButton = new Button("Add Student");
        addStudentButton.setAlignment(Pos.CENTER);
        addStudentButton.setPrefWidth(200);
        Button expelStudentButton = new Button("Expel Student");
        expelStudentButton.setAlignment(Pos.CENTER);
        expelStudentButton.setPrefWidth(200);
        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(200);
        exitButton.setAlignment(Pos.BASELINE_CENTER);

        VBox leftVbox = new VBox(addStudentButton, expelStudentButton, exitButton);
        leftVbox.setFillWidth(true);
        leftVbox.getChildren().add(placeholderText);
        leftVbox.setMinHeight(50);
        //leftVbox.setMaxWidth(150);
        leftVbox.setSpacing(20);
        leftVbox.setPadding(new Insets(40,20,20,20));
        //leftVbox.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));


        Label searchLabel = new Label("Search: ");
        TextField searchField = new TextField();
        searchField.setPrefWidth(310);
        searchField.setAlignment(Pos.CENTER);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateStudentList(studentList.stream().filter(student -> student.getName().toLowerCase().contains(newValue.toLowerCase())).toList());
        });

        HBox searchHbox = new HBox(searchLabel, searchField);
        //searchHbox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        searchHbox.setSpacing(5);
        searchHbox.setAlignment(Pos.CENTER);

        VBox centerVbox = new VBox(searchHbox);
        centerVbox.setFillWidth(true);
        centerVbox.setSpacing(50);
        //centerVbox.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        readStudentsFromFile();
        //sort students list
        studentList.sort(Comparator.comparing(Student::getName));

        studentsTable = new VBox();
        studentsTable.setSpacing(20);
        updateStudentList(studentList);

        ScrollPane scrollPane = new ScrollPane(studentsTable);
        //scrollPane.set;
        centerVbox.getChildren().add(scrollPane);
        centerVbox.getChildren().add(placeholderText);
        centerVbox.setSpacing(10);
        placeholderText.setAlignment(Pos.TOP_LEFT);
        placeholderText.setPadding(new Insets(1.5));
        scrollPane.setMinHeight(495);   //450

        Button logoutButton = new Button("Logout");
        logoutButton.setPrefWidth(170);
        logoutButton.setAlignment(Pos.BOTTOM_CENTER);
        logoutButton.setOnAction(actionEvent -> {new LoginWindow(); stage.close();});

        VBox rightVbox = new VBox();
        rightVbox.setAlignment(Pos.BOTTOM_CENTER);
        rightVbox.setPadding(new Insets(10));
        rightVbox.setSpacing(10);
        rightVbox.setPrefWidth(200);

        date = new Label();
        date.setPadding(new Insets(0,7,0,0));
        time = new Label();
        time.setFont(Font.font("Arial", 38));
        time.setPadding(new Insets(0,7,0,0));
        rightVbox.getChildren().add(time);
        time.setAlignment(Pos.TOP_CENTER);

        rightVbox.getChildren().add(date);
        Label line = new Label("________________________");
        rightVbox.getChildren().add(line);


        //student info right table
        studentInfo = new VBox();
        name = new Label();
        age = new Label();
        nationality = new Label();
        registrationdate = new Label();
        pendingFees = new Label();
        grade = new Label();
        studentInfo.setPadding(new Insets(25,0,250,0));
        studentInfo.getChildren().addAll(name, age, nationality, registrationdate, pendingFees, grade);

        rightVbox.getChildren().add(studentInfo);
        rightVbox.getChildren().add(logoutButton);

        borderPane.setTop(welcomeText);
        borderPane.setLeft(leftVbox);
        borderPane.setCenter(centerVbox);
        borderPane.setRight(rightVbox);

        //Handle buttons
        addStudentButton.setOnAction(actionEvent -> {
            new AddStudentWindow(studentList);
            stage.close();
        });
        exitButton.setOnAction(actionEvent -> stage.close());
        expelStudentButton.setOnAction(actionEvent -> {
            new ExpelWindow(studentList);
            stage.close();
        });

        //date time
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                date.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                time.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
        };
        timer.start();

        Scene scene = new Scene(borderPane, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Administrator Window");
        stage.show();
    }

    private void updateStudentList(List<Student> toList) {
        studentsTable.getChildren().clear();
        for(Student s: toList) {
            Label e = new Label("  " + s.getName());
            e.setOnMouseClicked(evt -> updateSideInfo(s));
            studentsTable.getChildren().add(e);
        }
    }

    private void updateSideInfo(Student student) {
        name.setText("Name: " + student.getName());
        name.setFont(Font.font("Arial", 12.75));
        age.setText("Age: " + student.getAge());
        age.setFont(Font.font("Arial", 12.75));
        nationality.setText("Nationality: " + student.getNationality());
        nationality.setFont(Font.font("Arial", 12.75));
        registrationdate.setText("Reg Date: " + student.getRegistrationDate());
        registrationdate.setFont(Font.font("Arial", 12.75));
        pendingFees.setText("Fees: " + student.getFees());
        pendingFees.setFont(Font.font("Arial", 12.75));
        grade.setText("Grade: " + student.getGrade());
        grade.setFont(Font.font("Arial", 12.75));
    }

    private void readStudentsFromFile() {
        File file = new File("students.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);

            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] data = line.split(";");
                studentList.add(new Student(Integer.parseInt(data[0]), data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]), data[7], Float.parseFloat(data[8])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
