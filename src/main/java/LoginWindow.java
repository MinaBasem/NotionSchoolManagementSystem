import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LoginWindow {
    Stage stage;
    TextField loginTF;
    PasswordField passwordField;
    Label error;
    public LoginWindow() {
        stage = new Stage();
        stage.setTitle("Login");

        GridPane grid = new GridPane();
        grid.setVgap(8);
        grid.setHgap(7);
        grid.setPadding(new Insets(10));

        //Text
        Text welcomeText = new Text(20, 20, " Notion School System");
        welcomeText.setFont(Font.font("Impact", 29));
        welcomeText.setTextAlignment(TextAlignment.CENTER);

        Label usernameLabel = new Label("USERNAME:");
        usernameLabel.setFont(Font.font("Arial", 12));
        loginTF = new TextField();

        Label passwordLabel = new Label("PASSWORD:");
        passwordLabel.setFont(Font.font("Arial", 12));
        passwordField = new PasswordField();

        error = new Label("Incorrect credentials.");
        error.setFont(Font.font("Arial", 10.75));
        error.setTextFill(Color.RED);
        error.setVisible(false);

        Button loginButton = new Button("Sign In");
        loginButton.setAlignment(Pos.CENTER);
        loginButton.setPadding(new Insets(5));
        loginButton.setPrefWidth(75);
        loginButton.setOnAction(actionEvent -> {login();} );

        grid.getColumnConstraints().add(new ColumnConstraints(100));
        grid.addRow(0, welcomeText);
        grid.addRow(1, usernameLabel, loginTF);
        grid.addRow(2, passwordLabel, passwordField);
        grid.addRow(3, error, loginButton);
        //grid.setHalignment(usernameLabel, HPos.CENTER);
        //grid.setHalignment(passwordLabel, HPos.CENTER);
        grid.setHalignment(loginButton, HPos.RIGHT);

        stage.setScene(new Scene(grid));
        stage.setResizable(false);
        stage.show();
    }

    private void login() {
        if (verifyLogin()) {
            new MainWindow();
            stage.close();
        } else {
            loginTF.clear();
            passwordField.clear();
            error.setVisible(true);
        }
    }

    private boolean verifyLogin() {
        if(loginTF.getText().equals("admin") && passwordField.getText().equals("password"))
            return true;
        return false;
    }
}
