package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    Road selectedRoad;
    Kiwara kiwara;
    String obenweiss = "src/pfeil_weiss_oben.png";
    String untenweiss = "src/pfeil_weiss_unten.png";
    String linksweiss = "src/pfeil_weiss_links.png";
    String rechtsweiss = "src/pfeil_weiss_rechts.png";
    String obengruen = "src/pfeil_gruen_oben.png";
    String untengruen = "src/pfeil_gruen_unten.png";
    String linksgruen = "src/pfeil_gruen_links.png";
    String rechtsgruen = "src/pfeil_gruen_rechts.png";


    @FXML
    private ImageView leftImageView;

    @FXML
    private ImageView upperImageView;

    @FXML
    private ImageView rightImageView;

    @FXML
    private ImageView lowerImageView;

    @FXML
    private ImageView middleImageView;

    @FXML
    private Button signalFourButton;

    @FXML
    private Button signalThreeButton;

    @FXML
    private Button firstRoadButton;

    @FXML
    private Button secondRoadButton;

    @FXML
    private Button thirdRoadButton;

    @FXML
    private Button fourthRoadButton;

    @FXML
    private Button directionLeftButton;

    @FXML
    private Button directionStraightButton;

    @FXML
    private Button directionRightButton;

    @FXML
    private Button signalOneButton;

    @FXML
    private Button signalTwoButton;

    @FXML
    private Button resetButton;

    @FXML
    void directionLeftHanlder(ActionEvent event) {
        selectedRoad.direction = "L";
        selectedRoad.isOccupied = true;
        kiwara.setRoad(selectedRoad);

        File file = getPath(selectedRoad, 0);
        Image direction = new Image(file.toURI().toString());
        checkPlace(selectedRoad, direction);

    }

    private void checkPlace(Road r, Image dir){
        switch (r.getId()){
            case 0:
                lowerImageView.setImage(dir);
                break;
            case 1:
                leftImageView.setImage(dir);
                break;
            case 2:
                upperImageView.setImage(dir);
                break;
            case 3:
                rightImageView.setImage(dir);
                break;
        }
    }

    private File getPath(Road r, int gruen) {
        if(gruen == 0){
            switch (r.getId()){
                case 0:
                    if(r.direction.equals("S"))return new File(obenweiss);
                    if(r.direction.equals("L"))return new File(linksweiss);
                    if(r.direction.equals("R"))return new File(rechtsweiss);
                    break;
                case 1:
                    if(r.direction.equals("S"))return new File(rechtsweiss);
                    if(r.direction.equals("L"))return new File(obenweiss);
                    if(r.direction.equals("R"))return new File(untenweiss);
                    break;
                case 2:
                    if(r.direction.equals("S"))return new File(untenweiss);
                    if(r.direction.equals("L"))return new File(rechtsweiss);
                    if(r.direction.equals("R"))return new File(linksweiss);
                    break;
                case 3:
                    if(r.direction.equals("S"))return new File(linksweiss);
                    if(r.direction.equals("L"))return new File(untenweiss);
                    if(r.direction.equals("R"))return new File(obenweiss);
                    break;
            }
        }
        else{
            switch (r.getId()){
                case 0:
                    if(r.direction.equals("S"))return new File(obengruen);
                    if(r.direction.equals("L"))return new File(linksgruen);
                    if(r.direction.equals("R"))return new File(rechtsgruen);
                    break;
                case 1:
                    if(r.direction.equals("S"))return new File(rechtsgruen);
                    if(r.direction.equals("L"))return new File(obengruen);
                    if(r.direction.equals("R"))return new File(untengruen);
                    break;
                case 2:
                    if(r.direction.equals("S"))return new File(untengruen);
                    if(r.direction.equals("L"))return new File(rechtsgruen);
                    if(r.direction.equals("R"))return new File(linksgruen);
                    break;
                case 3:
                    if(r.direction.equals("S"))return new File(linksgruen);
                    if(r.direction.equals("L"))return new File(untengruen);
                    if(r.direction.equals("R"))return new File(obengruen);
                    break;
            }
        }
        return null;
    }

    @FXML
    void directionRightHandler(ActionEvent event) {
        selectedRoad.direction = "R";
        selectedRoad.isOccupied = true;
        kiwara.setRoad(selectedRoad);

        File file = getPath(selectedRoad, 0);
        Image direction = new Image(file.toURI().toString());
        checkPlace(selectedRoad, direction);
    }

    @FXML
    void directionStraightHandler(ActionEvent event) {
        selectedRoad.direction = "S";
        selectedRoad.isOccupied = true;
        kiwara.setRoad(selectedRoad);

        File file = getPath(selectedRoad, 0);
        Image direction = new Image(file.toURI().toString());
        checkPlace(selectedRoad, direction);
    }

    @FXML
    void firstRoadHandler(ActionEvent event) {
        selectedRoad = kiwara.getRoads()[0];
    }

    @FXML
    void fourthRoadHandler(ActionEvent event) {
        selectedRoad = kiwara.getRoads()[3];
    }

    @FXML
    void resetHandler(ActionEvent event) {
        upperImageView.setImage(null);
        leftImageView.setImage(null);
        rightImageView.setImage(null);
        lowerImageView.setImage(null);
        setup();
    }

    @FXML
    void secondRoadHandler(ActionEvent event) {
        selectedRoad = kiwara.getRoads()[1];
    }

    @FXML
    void signalFourHandler(ActionEvent event) {
        kiwara.signal(kiwara.getRoads()[3]);
        updateDriven();
    }

    @FXML
    void signalOneHandler(ActionEvent event) {
        kiwara.signal(kiwara.getRoads()[0]);
        updateDriven();
    }

    @FXML
    void signalThreeHandler(ActionEvent event) {
        kiwara.signal(kiwara.getRoads()[2]);
        updateDriven();
    }

    @FXML
    void signalTwoHandler(ActionEvent event) {
        kiwara.signal(kiwara.getRoads()[1]);
        updateDriven();
    }

    @FXML
    void thirdRoadHandler(ActionEvent event) {
        selectedRoad = kiwara.getRoads()[2];
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setup();
    }

    public void setup(){
        Road firstRoad = new Road("", 0, 4, false);
        Road secondRoad = new Road("", 1, 4, false);
        Road thirdRoad = new Road("", 2, 4, false);
        Road fourthRoad = new Road("", 3, 4, false);
        Road[] roads = new Road[]{firstRoad, secondRoad, thirdRoad, fourthRoad};
        kiwara = new Kiwara(roads);
        File file = new File("src/road.jpg");
        Image road = new Image(file.toURI().toString());
        middleImageView.setImage(road);
    }

    public void updateDriven(){
        Road[] roads = kiwara.getRoads();
        if(!roads[0].isOccupied){
            File file = getPath(roads[0], 1);
            Image direction = new Image(file.toURI().toString());
            checkPlace(roads[0], direction);
        }
        if(!roads[1].isOccupied){
            File file = getPath(roads[1], 1);
            Image direction = new Image(file.toURI().toString());
            checkPlace(roads[1], direction);
        }
        if(!roads[2].isOccupied){
            File file = getPath(roads[2], 1);
            Image direction = new Image(file.toURI().toString());
            checkPlace(roads[2], direction);
        }
        if(!roads[3].isOccupied){
            File file = getPath(roads[3], 1);
            Image direction = new Image(file.toURI().toString());
            checkPlace(roads[3], direction);
        }
    }
}
