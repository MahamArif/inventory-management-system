package application;
	
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;


public class Main extends Application {
	
	public static String screen1ID = "Login";
    public static String screen1File = "Login.fxml";
    public static String screen2ID = "MainWindow";
    public static String screen2File = "MainWindow.fxml";
	
	@Override
	public void start(final Stage primaryStage) {
		try {
			
			 ScreensController mainContainer = new ScreensController();
		     mainContainer.loadScreen(screen1ID, screen1File);
		     mainContainer.loadScreen(screen2ID, screen2File);
		        
		        
		     mainContainer.setScreen(screen1ID);
		     
		     
		     //Group root = new Group();
		     Pane root = new Pane();
		     root.getChildren().addAll(mainContainer);
			
			//Parent root1 = FXMLLoader.load(getClass().getResource("/application/MainWindow.fxml"));
			Scene scene = new Scene(new Group(root));
		
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(true);
			primaryStage.setResizable(true);
			primaryStage.setTitle("SOFTcodein Inventory System");
			Image icon = new Image(getClass().getResourceAsStream("/application/store_icon.jpg")); 
			primaryStage.getIcons().add(icon);
			primaryStage.show();
			
			
			letterbox(scene, root);
		    primaryStage.setFullScreen(true);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	 private void letterbox(final Scene scene, final Pane contentPane) {
		    final double initWidth  = scene.getWidth();
		    final double initHeight = scene.getHeight();
		    final double ratio      = initWidth / initHeight;

		    SceneSizeChangeListener sizeListener = new SceneSizeChangeListener(scene, ratio, initHeight, initWidth, contentPane);
		    scene.widthProperty().addListener(sizeListener);
		    scene.heightProperty().addListener(sizeListener);
		  }

	 
	 private static class SceneSizeChangeListener implements ChangeListener<Number> {
		    private final Scene scene;
		    private final double ratio;
		    private final double initHeight;
		    private final double initWidth;
		    private final Pane contentPane;

		    public SceneSizeChangeListener(Scene scene, double ratio, double initHeight, double initWidth, Pane contentPane) {
		      this.scene = scene;
		      this.ratio = ratio;
		      this.initHeight = initHeight;
		      this.initWidth = initWidth;
		      this.contentPane = contentPane;
		    }

		    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
		      final double newWidth  = scene.getWidth();
		      final double newHeight = scene.getHeight();

		      double scaleFactor =
		          newWidth / newHeight > ratio
		              ? newHeight / initHeight
		              : newWidth / initWidth;

		      if (scaleFactor >= 1) {
		        Scale scale = new Scale(scaleFactor, scaleFactor);
		        scale.setPivotX(0);
		        scale.setPivotY(0);
		        scene.getRoot().getTransforms().setAll(scale);

		        contentPane.setPrefWidth (newWidth  / scaleFactor);
		        contentPane.setPrefHeight(newHeight / scaleFactor);
		      } else {
		        contentPane.setPrefWidth (Math.max(initWidth,  newWidth));
		        contentPane.setPrefHeight(Math.max(initHeight, newHeight));
		      }
		    }
		  }
	 
}
