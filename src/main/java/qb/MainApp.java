package qb;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import qb.login.controller.LogInWinController;
import qb.login.controller.RegistryWinController;

public class MainApp extends Application {

	private static Stage primaryStage;
	private BorderPane rootLayout;
	private static boolean isDialogClosed = false;

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
			loader.setLocation(MainApp.class
					.getResource("testwindow/view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

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
			loader.setLocation(MainApp.class
					.getResource("testwindow/view/TestWindow.fxml"));
			AnchorPane testWindow = (AnchorPane) loader.load();

			rootLayout.setCenter(testWindow);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean showLoginDialog() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("login/view/LogInView.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Войти");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			LogInWinController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			EventHandler<WindowEvent> closingEvent = new EventHandler<WindowEvent>() {

				public void handle(WindowEvent event) {
					isDialogClosed = true;
				}
			};

			dialogStage.setOnCloseRequest(closingEvent);
			dialogStage.showAndWait();

			if (isDialogClosed)
				primaryStage.close();

			return controller.isEnterClicked();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean showRegisterDialog() {

		try {

			FXMLLoader loader = new FXMLLoader();
			Stage dialogStage = new Stage();

			loader.setLocation(MainApp.class
					.getResource("login/view/RegistryView.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Scene scene = new Scene(page);

			dialogStage.setTitle("Регистрация");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setScene(scene);

			// TODO создать контроллер

			RegistryWinController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			dialogStage.showAndWait();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
