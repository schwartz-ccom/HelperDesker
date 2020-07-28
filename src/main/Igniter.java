package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Igniter extends Application {
    
    @Override
    public void start( Stage primaryStage ) throws Exception {
        Parent root = FXMLLoader.load( getClass().getResource( "../views/mcl_view.fxml" ) );
        primaryStage.setTitle( "CCOM Desk Helper 2: Electric Boogaloo" );
        Scene s = new Scene( root, 800, 600 );
        primaryStage.setScene( s );
        primaryStage.show();
        //primaryStage.setOnCloseRequest( e -> Reloader.writeOutToDataFile() );
    }
    
    public static void main( String[] args ) {
        launch( args );
    }
}
