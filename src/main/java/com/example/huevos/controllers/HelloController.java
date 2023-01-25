package com.example.huevos.controllers;

import com.example.huevos.models.Gallina;
import com.example.huevos.models.Huevos;
import com.example.huevos.models.Nube;
import com.example.huevos.models.Vector;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class HelloController implements Observer {
    MediaPlayer media_player_soundtrack;
    MediaPlayer media_player_menu;
    String game_over_menu = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\gameOver.gif";
    String path_one = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\gallinaIzq.png";
    String path_two = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\gallinaIzq2.png";
    String path_three = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\gallinaIzq3.png";
    String path_four = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\gallinaDer.png";
    String path_five = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\gallinaDer2.png";
    String path_six = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\gallinaDer3.png";
    String lifes_5 = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\lifes_5.png";
    String lifes_4 = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\lifes_4.png";
    String lifes_3 = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\lifes_3.png";
    String lifes_2 = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\lifes_2.png";
    String lifes_1 = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\lifes_1.png";
    String lifes_0 = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\lifes_0.png";
    String golden_egg = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\golden_egg.png";
    String playMenu_image = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\playMenu.gif";
    String path_game_over_soundtrack = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\sounds\\game_over_soundtrack.mp3";
    String path_gallina = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\sounds\\gallina.mp3";
    String path_soundtrack_game = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\sounds\\soundtrack.mp3";
    String path_menu = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\sounds\\menu.mp3";
    String gallina_izq_gif = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\gallina_left.gif";
    String gallina_der_gif = "C:\\Users\\crash\\IdeaProjects\\huevos\\src\\main\\resources\\assets\\imgs\\gallina_right.gif";
    Media menu_soundtrack = new Media(new File(path_menu).toURI().toString());
    Media media2 = new Media(new File(path_soundtrack_game).toURI().toString());
    Media game_over_soundtrack = new Media(new File(path_game_over_soundtrack).toURI().toString());
    Media path_gallina_soundtrack = new Media(new File(path_gallina).toURI().toString());

    private Gallina gallina;
    private Huevos huevo;
    private Nube nube;
    private double mov = 0;
    private Random first_random;
    public boolean egg_flag = true;
    private int lifes = 5;
    private int nivel = 0;
    private boolean restart = false;

    @FXML
    private ImageView character, egg, second_egg, health, menu, game_over, img_cloud;
    @FXML
    private Button btnEmpezar;
    @FXML
    private Label final_score, score;

    @FXML
    private AnchorPane rootScene;

    @FXML
    void btnEmpezarOnMouse(MouseEvent event) {
        score.setVisible(true);
        media_player_menu.stop();

        Media media = new Media(new File(path_gallina).toURI().toString());
        media_player_menu = new MediaPlayer(media);
        media_player_menu.play();

        health.setImage(new Image(lifes_5));

        FadeTransition fadeTransition = new FadeTransition(
            Duration.millis(1500), btnEmpezar);
            fadeTransition.setToValue(0);
        fadeTransition.play();

        FadeTransition fadeMenu = new FadeTransition(
                Duration.millis(1500), menu);
        fadeMenu.setToValue(0);
        fadeMenu.play();

        media_player_soundtrack = new MediaPlayer(media2);
        media_player_soundtrack.setCycleCount(MediaPlayer.INDEFINITE);
        media_player_soundtrack.play();

        nube = new Nube();
        nube.addObserver(this);
        nube.setPosicion(new Vector(3, 8, 103));
        Thread cloud = new Thread(nube);
        cloud.start();

        huevo = new Huevos(0);
        huevo.setPosicion(new Vector(2, 137, -600));
        huevo.addObserver(this);
        new Thread(huevo).start();
        img_cloud.toFront();
        img_cloud.setVisible(true);
    }

    @FXML
    void characterOnKeyPressed(KeyEvent event) {

        if (event.getCode() == KeyCode.A) {
            if (character.getLayoutX() < 11 ) {
                character.setLayoutX(character.getLayoutX());
            } else {
                character.setImage(new Image(gallina_izq_gif));
                character.setLayoutX(character.getLayoutX() - 10);
                Platform.runLater(()-> {
                    FadeTransition ft = new FadeTransition(Duration.millis(500), character);
                    ft.setFromValue(1.0);
                    ft.play();
                    character.setImage(new Image(path_two));
                    ft.setFromValue(1.0);
                    ft.play();
                    character.setImage(new Image(path_three));
                });
            }


        } else if (event.getCode() == KeyCode.D) {
            if (character.getLayoutX() > 850 ) {
                character.setLayoutX(character.getLayoutX());
            } else {
                character.setImage(new Image(gallina_der_gif));
                character.setLayoutX(character.getLayoutX() + 10);
                Platform.runLater(()-> {
                    FadeTransition fadeTransition4 = new FadeTransition(
                            Duration.millis(100), character);
                    fadeTransition4.setCycleCount(1);
                    fadeTransition4.play();
                    character.setImage(new Image(path_four));
                    FadeTransition fadeTransition5 = new FadeTransition(
                            Duration.millis(100), character);
                    fadeTransition5.setCycleCount(1);
                    fadeTransition5.play();
                    character.setImage(new Image(path_five));
                    FadeTransition fadeTransition6 = new FadeTransition(
                            Duration.millis(50L), character);
                    fadeTransition6.setCycleCount(1);
                    fadeTransition6.play();
                    character.setImage(new Image(path_six));
                });
            }
        }

    }

    @FXML
    public void initialize() {
        menu.setVisible(true);
        menu.setImage(new Image((playMenu_image)));
        btnEmpezar.toFront();
        img_cloud.toBack();
        character.setImage(new Image(path_four));
        second_egg.setImage(new Image(golden_egg));
        score.setText("Score: 0");

        media_player_menu = new MediaPlayer(menu_soundtrack);
        media_player_menu.setCycleCount(MediaPlayer.INDEFINITE);
        media_player_menu.play();

        gallina = new Gallina();
        gallina.setPosicion(new Vector(1, 100, 554));
        gallina.addObserver(this);
        new Thread(gallina).start();
    }

    @Override
    public void update(Observable o, Object arg) {
        first_random = new Random(System.currentTimeMillis());
        String s = score.getText().toString().substring(7);

        Vector pos = (Vector)arg;
        switch (pos.getId()) {
            case 2:
                Platform.runLater(() -> {
                    egg.setLayoutY(pos.getY());
                });
                break;
            case 3:
                Platform.runLater(() -> {
                    img_cloud.setLayoutX(pos.getX());
                });
                break;
        }

        Platform.runLater(() -> {
            if (character.getBoundsInParent().intersects(egg.getBoundsInParent())) {
                int aleatorio = first_random.nextInt((827));
                this.huevo.setStatus(false);
                this.egg.setVisible(false);

                int puntuacion = Integer.parseInt(s);
                puntuacion = puntuacion + 10;
                score.setText("Score: " + puntuacion);
                if ((!huevo.getStatus() && lifes > 0)) {
                    this.huevo.setStatus(true);
                    huevo.setPosicion(new Vector(2, aleatorio, -50));
                    this.egg.setVisible(true);
                    egg.setTranslateX(aleatorio);
                    egg.setLayoutY(-50);
                    this.egg_flag = true;
                }
            }

            if (egg.getLayoutY() > 736 && lifes > 0) {
                //int aleatorio = first_random.nextInt((750-50+1) + 200);
                int aleatorio = first_random.nextInt((827));
                this.egg_flag = false;
                this.huevo.setStatus(true);
                huevo.setPosicion(new Vector(2, aleatorio, -50));
                this.egg.setVisible(true);
                egg.setTranslateX(aleatorio);
                egg.setLayoutY(-50);
                media_player_menu = new MediaPlayer(path_gallina_soundtrack);
                media_player_menu.play();
                lifes--;
                System.out.println(egg.getLayoutX() + "     " + egg.getLayoutY());
            }
            if (lifes==0 && !restart) {
                media_player_soundtrack.stop();
                game_over.setImage(new Image(game_over_menu));
                game_over.setVisible(true);
                game_over.toFront();
                final_score.setText(score.getText());
                final_score.toFront();
                media_player_menu = new MediaPlayer(path_gallina_soundtrack);
                media_player_menu.play();

                media_player_menu = new MediaPlayer(game_over_soundtrack);
                media_player_menu.play();
                restart = true;
            }

            if ((Integer.parseInt(s)) > 30) {
                huevo.setVel(10);
            } else if ((Integer.parseInt(s)) > 70) {
                huevo.setVel(20);
            } if ((Integer.parseInt(s)) > 150) {
                huevo.setVel(30);
            } if ((Integer.parseInt(s)) > 200) {
                huevo.setVel(50);
            }

            switch (lifes) {
                case 4: health.setImage(new Image(lifes_4));
                    break;
                case 3: health.setImage(new Image(lifes_3));
                    break;
                case 2: health.setImage(new Image(lifes_2));
                    break;
                case 1: health.setImage(new Image(lifes_1));
                    break;
                case 0: health.setImage(new Image(lifes_0));
                    break;
            }
        });

    }
}
