package benchmarking;

public class Timer {
    
    private static long timeNow;
    
    public static void start() {
        timeNow = System.currentTimeMillis();
    }
    
    public static void stop( String msg ) {
        System.out.println( msg + " took " + ( System.currentTimeMillis() - timeNow ) + " milliseconds" );
    }
}
