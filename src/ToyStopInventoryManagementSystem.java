package toystopinventorymanagementsystem;

import java.util.Scanner; 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fahad Satti
 */
public class ToyStopInventoryManagementSystem {
    ToyStopService tsService = new ToyStopService();
    public void init(){
        
        tsService.initEmployees();
        tsService.initStores();
        tsService.initToys();
        System.out.println("Init complete");
    }
    /**
     * @param args the command line arguments
     */
    
    public void saveData(ToyStopService ts) {
        
        try {
         FileOutputStream fileOut =  new FileOutputStream("data.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(ts);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in data.ser");
      }catch(IOException ex) {
         ex.printStackTrace();
      }
        
    }
    
    public ToyStopService loadData(){
     ToyStopService ts=null;
        try {
         
         FileInputStream fileIn = new FileInputStream("data.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         ts = (ToyStopService) in.readObject();
         in.close();
         fileIn.close();
        }catch(IOException ex) {
         ex.printStackTrace();
        }   catch (ClassNotFoundException ex) {
            Logger.getLogger(ToyStopInventoryManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
       return ts;
    }
                        
    public static void main(String[] args) throws ClassNotFoundException {
       
        ToyStopInventoryManagementSystem tsims = new ToyStopInventoryManagementSystem();    
        tsims.init();
        
        tsims.saveData(tsims.tsService); 
        
        for (int i=0; i<60; i++){  // run for 60 days
        
            tsims.tsService=tsims.loadData();

            tsims.tsService.sellToys();  //100 toys sold each day
            if (i % 7 == 0 && i != 0){           
                tsims.tsService.addNewToys(); // 100 toys added to the stores each week
            }
            
            tsims.saveData(tsims.tsService);
        }
        tsims.printAll();
          Scanner reader = new Scanner(System.in);
          
          
          while (true){ 
                tsims.showMenu();
                int n = reader.nextInt();
                
                if (n == 1){
                tsims.printAll();
                }
                else if( n==2){
                  int newStore = tsims.tsService.addStore();
                  System.out.println("Store created with UID:"+newStore);

                }
                else if( n==3){
                int newEmployee = tsims.tsService.addEmployee();
                System.out.println("Employee added with UID:"+newEmployee);
                }
                else if( n==4){
                int newToy = tsims.tsService.addToy();
                System.out.println("Toy added with UID:"+newToy);
               

                }
                else if( n==0){
                tsims.saveData(tsims.tsService);
                }
                else if(n==10){
                System.out.println("Exiting");
                break;
                }
                
            }      

        }
        //load previous data
        //tsims.loadData();
        
        //tsims.showMenu();
          
    


    
//    private void loadData() {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
////    }

    private void showMenu() {
        System.out.println("");
        System.out.println("Welcome to Toy Stop Inventory Management System");
        System.out.println("Enter 1 to show all data");
        System.out.println("Enter 2 to add a new Store");
        System.out.println("Enter 3 to add a new Employee");
        System.out.println("Enter 4 to add a new Toy");
        System.out.println("Enter 0 to save state");
        System.out.println("Enter 10 to exit");
    }

    private void printAll() {
        System.out.println(this.tsService.stores);
    }
    
}
