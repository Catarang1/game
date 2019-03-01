package commons;

import editor.*;
import game.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.*;


public class SplashGraphController implements Initializable {

	@FXML private Pane play;
	@FXML private Pane editor;
	@FXML private Pane close;
	@FXML private StackPane root;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		root.setBackground(/*new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))*/Background.EMPTY);
		close.setOnMouseClicked(e -> {
			closeThisStage();
		});
		
		editor.setOnMouseClicked(e -> {
			new EditorWindow();
			closeThisStage();
		});
		
		play.setOnMouseClicked(e -> {
			Engine.window.show();
			closeThisStage();
		});
	}
	
	private void closeThisStage() {
		Stage base = (Stage) close.getScene().getWindow();
		base.close();
	}
	
}
