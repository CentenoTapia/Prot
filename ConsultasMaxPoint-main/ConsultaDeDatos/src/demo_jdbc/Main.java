package demo_jdbc;



import java.util.List;
import java.util.Map;
import java.util.Observable;

import demo_jdbc.Repository.DriverResultRepository;
import demo_jdbc.Repository.SeasonRepository;
import demo_jdbc.models.DriverResult;
import demo_jdbc.models.Season;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private ObservableList<DriverResult> driverResults = FXCollections.observableArrayList();

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
        DriverResultRepository driverResultRepository = new DriverResultRepository();
        List<DriverResult> results = driverResultRepository.getResultByYear(selectedYear);

        driverResults.clear();
        driverResults.addAll(results);
    }

    public TableView<DriverResult> creTableView() {
        TableView<DriverResult> tableView = new TableView<>(driverResults);
        tableView.setPrefSize(400, 500);
        String style = "-fx-background-color: #20B2AA";
        tableView.setStyle(style);

        TableColumn<DriverResult, String> tableColumn1 = new TableColumn<>("Driver Name");
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("driverName"));

        TableColumn<DriverResult, Integer> tableColumn2 = new TableColumn<>("Wins");
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("wins"));

        TableColumn<DriverResult, Integer> tableColumn3 = new TableColumn<>("Total Points");
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));

        TableColumn<DriverResult, Integer> tableColumn4 = new TableColumn<>("Rank");
        tableColumn4.setCellValueFactory(new PropertyValueFactory<>("rank"));

        tableView.getColumns().addAll(tableColumn1, tableColumn2, tableColumn3, tableColumn4);

        return tableView;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 600);

        primaryStage.setTitle("DRIVER RESULTS");
        primaryStage.setScene(scene);
        primaryStage.show();

        VBox vBox = new VBox();
        vBox.setPrefSize(100, 400);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().add(creTableView());

        HBox hBox = new HBox();
        hBox.setPrefSize(40, 10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(createLabel("Año:"));
        hBox.getChildren().add(createComboBox());

        root.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(createLabel("Resultado de Drivers "));
        root.setCenter(hBox);
        root.setBottom(vBox);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

	

