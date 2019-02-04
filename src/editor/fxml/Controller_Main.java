package editor.fxml;

import editor.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.logging.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

/**
 * FXML Controller class
 *
 * @author Jan
 */
public class Controller_Main implements Initializable {

	@FXML private MenuBar menuBar;
	@FXML private Menu gameBoardMenu;
	@FXML private MenuItem newOption;
	@FXML private Menu newFromTemplateMenu;
	@FXML private MenuItem loadOption;
	@FXML private MenuItem saveOption;
	@FXML private MenuItem quitOption;
	@FXML private Menu viewMenu;
	@FXML private CheckMenuItem showCollisionsOption;
	@FXML private CheckMenuItem showLightsOption;
	@FXML private CheckMenuItem showGridOption;
	@FXML private Menu selectMenu;
	@FXML public MenuItem selectBackground;
	@FXML public MenuItem selectGround1;
	@FXML public MenuItem selectGround2;
	@FXML public MenuItem selectActor;
	@FXML public MenuItem selectObject1;
	@FXML public MenuItem selectObject2;
	@FXML public MenuItem selectCollision;
	@FXML public MenuItem selectLight;
	@FXML private Menu eventsMenu;
	@FXML private MenuItem editEventsOption;
	@FXML private MenuItem deleteAllOption;
	@FXML private Menu helpMenu;
	@FXML private MenuItem showDocOption;
	@FXML private Text activeChar;
	@FXML private ImageView activeImage;
	@FXML private ComboBox<EditorLayer> activeLayerComboBox;
	@FXML private ScrollPane activeTilesWrapper;
	@FXML private TilePane activeTilesBox;
	@FXML private Canvas backgroundLayer;
	@FXML private Canvas ground0Layer;
	@FXML private Canvas ground1Layer1;
	@FXML private Canvas actorLayer;
	@FXML private Canvas object0Layer1;
	@FXML private Canvas object1Layer;
	@FXML private Canvas collisionLayer;
	@FXML private Pane lightLayer;
	@FXML private Pane interactiveLayer;
	@FXML private Canvas gridLayer;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		setupLayerSelection();
		setupShowFXs();
		setupGridLayer();
		setupSelectMenu();
		
		showDocOption.setOnAction((event) -> {
			try {
				new DocumentationWindow();
			} catch (IOException ex) {
				Logger.getLogger(Controller_Main.class.getName()).log(Level.SEVERE, null, ex);
			}
		});

	}
	
	protected void setupSelectMenu() {
		selectBackground.setOnAction(e -> activeLayerComboBox.getSelectionModel().select(EditorLayer.Background));
		selectGround1.setOnAction(e -> activeLayerComboBox.getSelectionModel().select(EditorLayer.Ground1));
		selectGround2.setOnAction(e -> activeLayerComboBox.getSelectionModel().select(EditorLayer.Ground2));
		selectActor.setOnAction(e -> activeLayerComboBox.getSelectionModel().select(EditorLayer.Actor));
		selectObject1.setOnAction(e -> activeLayerComboBox.getSelectionModel().select(EditorLayer.Object1));
		selectObject2.setOnAction(e -> activeLayerComboBox.getSelectionModel().select(EditorLayer.Object2));
		selectCollision.setOnAction(e -> activeLayerComboBox.getSelectionModel().select(EditorLayer.Collision));
		selectLight.setOnAction(e -> activeLayerComboBox.getSelectionModel().select(EditorLayer.Light));
	}

	protected void setupGridLayer() {
		GraphicsContext brush = gridLayer.getGraphicsContext2D();
		brush.setFill(Color.GREY);
		for (int x = 0; x < gridLayer.getWidth() / 32; x++) {
			if (x!=0) brush.fillRect(x*32, 0, 1, gridLayer.getHeight());
		}
		
		for (int y = 0; y < gridLayer.getHeight() / 32; y++) {
			if (y!=0) brush.fillRect(0, y*32, gridLayer.getWidth(), 1);
		}
	}

	protected void setupShowFXs() {
		collisionLayer.visibleProperty().bind(showCollisionsOption.selectedProperty());
		lightLayer.visibleProperty().bind(showLightsOption.selectedProperty());
		gridLayer.visibleProperty().bind(showGridOption.selectedProperty());
	}

	protected void setupLayerSelection() {
		EventHandler<ActionEvent> layerSelect = (event) -> {
			activeTilesBox.getChildren().clear();
			EditorLayer selected = getSelectedLayer();

			for (Character c : selected.getLayerMap().keySet()) {
				ImageView iv = new ImageView(selected.getLayerMap().get(c));
				iv.setFitHeight(32);
				iv.setFitWidth(32);
				iv.setOnMouseClicked(ev -> {
					activeChar.setText(String.valueOf(c));
					activeImage.setImage(new Image(selected.getLayerMap().get(c)));
				});
				iv.setPickOnBounds(true);
				activeTilesBox.getChildren().add(iv);
			}

		};
		activeLayerComboBox.getItems().setAll(EditorLayer.values());
		activeLayerComboBox.getSelectionModel().selectFirst();
		activeLayerComboBox.setOnAction(layerSelect);
		layerSelect.handle(new ActionEvent());

	}

	protected EditorLayer getSelectedLayer() {
		return activeLayerComboBox.getSelectionModel().getSelectedItem();
	}

}
