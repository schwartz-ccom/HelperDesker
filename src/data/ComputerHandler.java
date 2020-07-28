package data;

import java.util.ArrayList;
import java.util.Arrays;

public class ComputerHandler {
    
    private static ComputerHandler instance;
    private ArrayList< Computer > theMasterListOfComputers;
    
    public static ComputerHandler getInstance() {
        if ( instance == null )
            instance = new ComputerHandler();
        return instance;
    }
    
    private ComputerHandler() {
        theMasterListOfComputers = new ArrayList<>();
    }
    
    public void setList( ArrayList< Computer > list ) {
        theMasterListOfComputers = list;
    }
    
    public void appendList( ArrayList< Computer > list ) {
        theMasterListOfComputers.addAll( list );
    }
    
    public void appendList( Computer[] list ) {
        theMasterListOfComputers.addAll( new ArrayList<>( Arrays.asList( list ) ) );
    }
    
    public void addToList( Computer computer ) {
        theMasterListOfComputers.add( computer );
    }
    
    public void clearList() {
        theMasterListOfComputers.clear();
    }
    
    public ArrayList< Computer > getList() {
        return theMasterListOfComputers;
    }
}
