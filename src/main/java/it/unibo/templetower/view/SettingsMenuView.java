package it.unibo.templetower.view;

import java.io.FileNotFoundException;
import java.io.InputStream;

import it.unibo.templetower.controller.MusicController;
import it.unibo.templetower.utils.BackgroundUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
/**
 * Class responsible for creating and managing the settings menu scene. This class
 * provides the settings menu interface for the game.
 */

public class SettingsMenuView {

    static final int BUTTON_HEIGHT = 30;
    static final int BUTTON_WIDTH = 30;

     /**
     * Creates and returns the settings menu scene.
     *
     * @param manager The scene manager to handle scene transitions
     * @return Scene object representing the settings menu
     * @throws FileNotFoundException if background image resource cannot be found
     */
    public StackPane createScene(final SceneManager manager) throws FileNotFoundException {
        // Create root container
        final StackPane root = new StackPane();

        // Set up background image
        final InputStream backgroundStream = getClass().getClassLoader()
                .getResourceAsStream("images/darkened-background.png");
        if (backgroundStream == null) {
            throw new FileNotFoundException("Could not find background image: images/darkened-background.png");
        }

        // Create and configure background
        final ImageView background = new ImageView(new Image(backgroundStream));
        background.setPreserveRatio(false);

        // Make background responsive using BackgroundUtils
        BackgroundUtils.setupBackgroundResizing(root, background);

        // Create content layout
        final VBox content = new VBox(10);
        content.setAlignment(Pos.BOTTOM_CENTER);

        // Add difficulty menu button
        final Button difficultyButton = new Button("Return to Enter Menu");
        difficultyButton.setOnAction(e -> {

            manager.switchTo("enter_menu");
        });

        // Mute button with image
       final Image image = new Image("images/muto.png");
        final ImageView imageView = new ImageView(image);
        imageView.setFitWidth(BUTTON_WIDTH);
        imageView.setFitHeight(BUTTON_HEIGHT);

        final Button muteButton = new Button();
        muteButton.setGraphic(imageView);
        muteButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        muteButton.setOnAction(e -> {
            MusicController.getInstance().stopMusic();
        });

        final Image image1 = new Image("images/alzavol.png");
        final ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(BUTTON_WIDTH);
        imageView1.setFitHeight(BUTTON_HEIGHT);

        final Image image2 = new Image("images/abbassavol.png");
        final ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(BUTTON_WIDTH);
        imageView2.setFitHeight(BUTTON_HEIGHT);

        final Button raiseButton = new Button();
        raiseButton.setGraphic(imageView1);
        raiseButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        raiseButton.setOnAction(e -> {
            MusicController.getInstance().raiseVol();

        });

        final Button lowerButton = new Button();
        lowerButton.setGraphic(imageView2);
        lowerButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        lowerButton.setOnAction(e -> {
            MusicController.getInstance().lowerVol();

        });

        content.getChildren().addAll(raiseButton, lowerButton, muteButton, difficultyButton);

        // Combine background and content
        root.getChildren().addAll(background, content);



        return root;
    }
}
