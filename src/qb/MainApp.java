package qb;

import java.io.IOException;

import qb.login.view.LogInWinController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Test");

		initRootLayout();
		showTestWindow();
		showLoginDialog();
	}
	
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("testwindow/view/RootLayout.fxml"));
			rootLayout = loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showTestWindow() {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("testwindow/view/testWindow.fxml"));
			AnchorPane testWindow = loader.load();
			
			rootLayout.setCenter(testWindow);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean showLoginDialog(){
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("login/view/LogInWin.fxml"));
			AnchorPane page = loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Login");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			LogInWinController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	
	}

	public static void main(String[] args) {
		launch(args);
	}
}
