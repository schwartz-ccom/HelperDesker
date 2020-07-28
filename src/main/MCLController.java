package main;

import data.Computer;
import data.ComputerHandler;
import data.PropertiesHandler;
import data.Reloader;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import messengers.StatusMessenger;
import messengers.StatusSubscriber;
import sample.RandomGenerator;

import java.io.File;

public class MCLController implements StatusSubscriber {
    
    // UI Elements I need to access
    public Label lblStatus;
    
    public TableView< Computer > tableMCL;
    
    public TableColumn< Computer, String > columnInventoried;
    public TableColumn< Computer, String > columnType;
    public TableColumn< Computer, String > columnName;
    public TableColumn< Computer, String > columnSteward;
    public TableColumn< Computer, String > columnFacility;
    public TableColumn< Computer, String > columnRoom;
    public TableColumn< Computer, String > columnMake;
    public TableColumn< Computer, String > columnModel;
    public TableColumn< Computer, String > columnSerial;
    public TableColumn< Computer, String > columnComments;
    
    public Button btnSample;
    
    public Slider sliderSampleCount;
    
    public MenuItem mnItemClearInventory;
    
    public void initialize() {
        
        StatusMessenger.subscribe( this );
        
        columnInventoried.setCellValueFactory( new PropertyValueFactory<>("lastInventory") );
        columnType.setCellValueFactory( new PropertyValueFactory<>("type") );
        columnName.setCellValueFactory( new PropertyValueFactory<>("name") );
        columnSteward.setCellValueFactory( new PropertyValueFactory<>("steward") );
        columnFacility.setCellValueFactory( new PropertyValueFactory<>("facility") );
        columnRoom.setCellValueFactory( new PropertyValueFactory<>("room") );
        columnMake.setCellValueFactory( new PropertyValueFactory<>("make") );
        columnModel.setCellValueFactory( new PropertyValueFactory<>("model") );
        columnSerial.setCellValueFactory( new PropertyValueFactory<>("serialNumber") );
        columnComments.setCellValueFactory( new PropertyValueFactory<>("comment") );
    
        String path = PropertiesHandler.getInstance().getProperty( "last_file" );
        
        File theFileToReadFrom = new File( path );
        
        if ( Reloader.readFromDataFile( theFileToReadFrom, true ) )
            System.out.println( "Read from file" );
        
        btnSample.setOnMouseClicked( e -> {
            Computer[] sampleComputers = RandomGenerator.generateShit( ( int ) sliderSampleCount.getValue() );
            
            ComputerHandler.getInstance().appendList( sampleComputers );
    
            tableMCL.setItems( FXCollections.observableArrayList( ComputerHandler.getInstance().getList() ) );
        } );
        
        mnItemClearInventory.setOnAction( e -> {
            Reloader.clearList( theFileToReadFrom );
            tableMCL.getItems().clear();
            tableMCL.refresh();
        } );
    
        tableMCL.setItems( FXCollections.observableArrayList( ComputerHandler.getInstance().getList() ) );
    }
    
    @Override
    public void updateStatus( String statusMessage ) {
        lblStatus.setText( statusMessage );
    }
}
