package messengers;

import jdk.jshell.Snippet;

import java.util.ArrayList;

public class StatusMessenger {
    private final static ArrayList< StatusSubscriber > subs = new ArrayList<>();
    
    public static void subscribe( StatusSubscriber subscriber ) {
        subs.add( subscriber );
    }
    
    public static void unsubscribe( StatusSubscriber subscriber ) {
        subs.remove( subscriber );
    }
    
    public static void message( String statusMessage ) {
        for ( StatusSubscriber subscriber: subs ) {
            subscriber.updateStatus( statusMessage );
        }
    }
}
