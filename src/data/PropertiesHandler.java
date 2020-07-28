package data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHandler {
    
    private Properties props;
    
    private static PropertiesHandler instance;
    
    public static PropertiesHandler getInstance() {
        if ( instance == null )
            instance = new PropertiesHandler();
        return instance;
    }
    
    public PropertiesHandler() {
        props = new Properties();
        
        InputStream in = getClass().getClassLoader().getResourceAsStream( "resources\\settings.properties" );
        if ( in != null ) {
            try {
                props.load( in );
            } catch( IOException ioe ) {
                System.err.println( "Issue loading properties: " + ioe.getMessage() );
            }
        }
        System.out.println( "Loaded settings.properties" );
    }
    
    public String getProperty( String propertyName ) {
        if ( props.getProperty( propertyName, "" ).isBlank() )
            System.err.println( "Unknown property: " + propertyName );
        
        System.out.println( "getProperty( " + propertyName + " ) = " + props.getProperty( propertyName ) );
        
        return props.getProperty( propertyName, "UNAVAILABLE PROPERTY" );
    }
}
