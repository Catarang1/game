package commons.eventScript;

import commons.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * FXML Controller class
 *
 * @author Jan
 */
public class Controller_RemoveFlag implements Initializable {

	@FXML
	private VBox root;
	@FXML
	private ComboBox<Flag> flagSelection;
	@FXML
	private HBox buttonsRow;
	@FXML
	private Button cancel;
	@FXML
	private Button add;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	
	
}