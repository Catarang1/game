package editor;

import editor.fxml.*;
import java.io.*;
import java.util.logging.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

public class DocumentationWindow {

	private static Stage stage;
	private static Scene scene;
	private static Parent root;
	private static FXMLLoader loader;
	private static Controller_DocView controller;

	static {
		loader = new FXMLLoader(Controller_DocView.class.getResource("editor_docView.fxml"));
		try {
			root = loader.load();
		} catch (IOException ex) {
			Logger.getLogger(DocumentationWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
		controller = loader.getController();
		scene = new Scene(root);
		stage = new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Editor Documentation");
	}

	public static void show() {
		stage.show();
	}

}
