package sample;

import data.Computer;
import data.Values;

import java.util.Random;

public class RandomGenerator {
    
    public static Computer[] generateShit( int howMany ) {
        
        Computer[] generatedSampleComputers = new Computer[ howMany ];
        
        Random r = new Random();
        r.setSeed( System.currentTimeMillis() );
        
        for ( int index = 0; index < howMany; index += 1 ) {
            
            String type = "Desktop";
            if ( r.nextBoolean() )
                type = "Laptop";
            
            String serial = String.valueOf( r.nextDouble() * 10000 );
            
            String make = "Precision 5520";
            if ( r.nextBoolean() )
                make = "Latitude E450";
            
            int sampleNumber = index + Values.SAMPLES_DONE;
            
            String[] rnd = { "Today", type, "Sample " + sampleNumber, "IT Group", "CCOM", "S211", "Dell", make, serial, "A randomly generated entry" };
            
            generatedSampleComputers[ index ] = new Computer( rnd );
        }
    
        Values.SAMPLES_DONE += howMany;
        return generatedSampleComputers;
    }
}
