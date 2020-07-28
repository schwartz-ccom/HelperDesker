package data;

import benchmarking.Timer;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import messengers.StatusMessenger;

import java.io.*;

public class Reloader {

    private static boolean confirmDeletionOrOverwrite() {
        Alert confirmPotentialOverwrite = new Alert(
                Alert.AlertType.WARNING,
                "By having this function enabled, you may be overwriting an important list." +
                        "\nDo you absolutely know what you are doing? " +
                        "\nIt's also possible that this function isn't implemented. " +
                        "\nSeriously, back up the list you're about to write over.",
                ButtonType.YES,
                ButtonType.NO
        );
        confirmPotentialOverwrite.setHeaderText( "Just to check..." );
        confirmPotentialOverwrite.getDialogPane().setMinHeight( Region.USE_PREF_SIZE );
        confirmPotentialOverwrite.showAndWait();
    
        return confirmPotentialOverwrite.getResult() == ButtonType.YES;
    }
    
    public static void writeOutToDataFile( File f  ) {
    
        if( !confirmDeletionOrOverwrite() )
            return;
        
        try {
            if ( f.exists() && !f.delete() )
                throw new IOException( "[ WRITE ] Oh boy the file can't be deleted" );
            
            if ( !f.createNewFile() )
                throw new IOException( "[ WRITE ] Couldn't make the file." );
    
            FileWriter out = new FileWriter( f );
            
            out.write( "This isn't implemented. Sorry if I just destroyed your list. Shoulda said no dude." );
            
            out.close();
        } catch ( IOException e ) {
            System.err.println( "[ WRITE ] Error in writing to file: " + e.getMessage() );
            System.err.println( "[ WRITE ] On line: " + e.getCause() );
        }
    }
    
    public static boolean readFromDataFile( File f, boolean hasHeaders ) {
        try {
            if ( !f.getAbsoluteFile().exists() )
                throw new IOException( "The File referenced does not exist. This is fine on the first run through" );
    
            Timer.start();
            FileReader fileReader = new FileReader( f );
            boolean status = false;
            boolean isFirst = true;
            
            if ( fileReader.ready() ) {
                BufferedReader in = new BufferedReader( fileReader );
                String line;
                while ( ( line = in.readLine() ) != null ) {
                    if ( hasHeaders && isFirst ) {
                        isFirst = false;
                        continue;
                    }
                    
                    String[] computerComponents = line.split( "," );
                    try {
                        ComputerHandler.getInstance().addToList( new Computer( computerComponents ) );
                    } catch ( ArrayIndexOutOfBoundsException aioobe ) {
                        System.err.println( "The file is malformed" );
                    }
                    status = true;
                }
                in.close();
            }
            Timer.stop( "Loading file" );
            return status;
        } catch ( IOException ioe ) {
            System.err.println( "IOE: " + ioe.getMessage() );
            StatusMessenger.message( "There was an error reading the data file." );
        }
        return false;
    }
    
    public static void clearList( File f ) {
    
        if( !confirmDeletionOrOverwrite() )
            return;
        
        try {
            if ( f.exists() && !f.delete() )
                throw new IOException( "[ CLEAR ] Oh boy the file can't be deleted" );
        
            if ( !f.createNewFile() )
                throw new IOException( "[ CLEAR ] Couldn't make the file." );
        
            FileWriter out = new FileWriter( f );
            out.write( "" );
            out.close();
            
            ComputerHandler.getInstance().clearList();
        } catch ( IOException e ) {
            System.err.println( "[ CLEAR ] Error in writing to file: " + e.getMessage() );
            System.err.println( "[ CLEAR ] On line: " + e.getCause() );
        }
    }
}
