package demo_jdbc;

	import javafx.application.Application;
	import javafx.collections.FXCollections;
	import javafx.collections.ObservableList;
	import javafx.geometry.Insets;
	import javafx.geometry.Pos;
	import javafx.scene.Scene;
	import javafx.scene.control.*;
	import javafx.scene.control.cell.PropertyValueFactory;
	import javafx.scene.layout.BorderPane;
	import javafx.scene.layout.HBox;
	import javafx.scene.layout.VBox;
	import javafx.scene.text.Font;
	import javafx.stage.Stage;

	import java.util.List;

import demo_jdbc.Repository.QueryRepository;
import demo_jdbc.Repository.SeasonRepository;
import demo_jdbc.models.MaxPointConstructor;
import demo_jdbc.models.Season;

	public class ConstructorMaxPoint extends Application{

	    private ObservableList<MaxPointConstructor> constructorResults = FXCollections.observableArrayList();

	    public Label createLabel(String texto) {
	        Label lbl = new Label(texto);
	        lbl.setFont(Font.font("Times New Roman", 18));
	        String style = "-fx-font-weight: bold";  // Negrita
	        lbl.setStyle(style);
	        return lbl;
	    }

	    public ComboBox<String> createComboBox() {
	        ComboBox<String> comboBox = new ComboBox<>();
	        comboBox.setPrefSize(100, 10);

	        SeasonRepository seasonRepository = new SeasonRepository();
	        List<Season> years = seasonRepository.getSeasons();

	        for (Season season : years) {
	            comboBox.getItems().add(String.valueOf(season.getYear()));
	        }

	        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
	            System.out.println("Ha seleccionado el año: " + newValue);
	            if (newValue != null) {
	                updateTableView(Integer.parseInt(newValue));
	            }
	        });

	        return comboBox;
	    }

	    private void updateTableView(int selectedYear) {
	        QueryRepository queryRepository = new QueryRepository();
	        List<MaxPointConstructor> results = queryRepository.getMaxPointConstructor(selectedYear);

	        constructorResults.clear();
	        constructorResults.addAll(results);
	    }

	    public TableView<MaxPointConstructor> createTableView() {
	        TableView<MaxPointConstructor> tableView = new TableView<>(constructorResults);
	        tableView.setPrefSize(400, 500);
	        String style = "-fx-background-color: #20B2AA";
	        tableView.setStyle(style);

	        TableColumn<MaxPointConstructor, String> nameColumn = new TableColumn<>("Constructor Name");
	        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

	        TableColumn<MaxPointConstructor, Integer> pointsColumn = new TableColumn<>("Total Points");
	        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

	        tableView.getColumns().addAll(nameColumn, pointsColumn);

	        return tableView;
	    }

	    @Override
	    public void start(Stage primaryStage) throws Exception {
	        BorderPane root = new BorderPane();
	        Scene scene = new Scene(root, 600, 600);

	        primaryStage.setTitle("CONSTRUCTOR RESULTS");
	        primaryStage.setScene(scene);
	        primaryStage.show();

	        VBox vBox = new VBox();
	        vBox.setPrefSize(100, 400);
	        vBox.setPadding(new Insets(10, 10, 10, 10));
	        vBox.getChildren().add(createTableView());

	        HBox hBox = new HBox();
	        hBox.setPrefSize(40, 10);
	        hBox.setAlignment(Pos.CENTER);
	        hBox.getChildren().add(createLabel("Año:"));
	        hBox.getChildren().add(createComboBox());

	        root.setPadding(new Insets(10, 10, 10, 10));
	        root.setTop(createLabel("Resultado de Constructors Max Points"));
	        root.setCenter(hBox);
	        root.setBottom(vBox);
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
	}

