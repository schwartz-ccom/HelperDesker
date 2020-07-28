package data;

import javafx.beans.property.SimpleStringProperty;

public class Computer {
    
    final private SimpleStringProperty name;
    final private SimpleStringProperty type;
    final private SimpleStringProperty lastInventory;
    final private SimpleStringProperty serialNumber;
    final private SimpleStringProperty steward;
    final private SimpleStringProperty facility;
    final private SimpleStringProperty room;
    final private SimpleStringProperty make;
    final private SimpleStringProperty model;
    final private SimpleStringProperty comment;
    
    public Computer( String[] components ) {
        SimpleStringProperty[] all = new SimpleStringProperty[ 10 ];
        for ( int index = 0; index < 10; index += 1 ) {
            if ( !( index >= components.length ) && !components[ index ].isBlank() ) {
                all[ index ] = new SimpleStringProperty( components[ index ] );
            } else {
                all[ index ] = new SimpleStringProperty( "Unavailable" );
            }
        }
        
        this.lastInventory = all[ 0 ];
        this.type = all[ 1 ];
        this.name = all[ 2 ];
        this.steward = all[ 3 ];
        this.facility = all[ 4 ];
        this.room = all[ 5 ];
        this.make = all[ 6 ];
        this.model = all[ 7 ];
        this.serialNumber = all[ 8 ];
        this.comment = all[ 9 ];
    }
    
    // These are used by the Table Column things to get the property.
    // Really fucking ratchet but I'm okay with it. Less lines bb.
    public String getName() {
        return name.get();
    }
    
    public String getType() {
        return type.get();
    }
    
    public String getLastInventory() {
        return lastInventory.get();
    }
    
    public String getSerialNumber() {
        return serialNumber.get();
    }
    
    public String getSteward() {
        return steward.get();
    }
    
    public String getFacility() {
        return facility.get();
    }
    
    public String getRoom() {
        return room.get();
    }
    
    public String getMake() {
        return make.get();
    }
    
    public String getModel() {
        return model.get();
    }
    
    public String getComment() {
        return comment.get();
    }
    
    @Override
    public String toString() {
        return getName().toString();
    }
}
