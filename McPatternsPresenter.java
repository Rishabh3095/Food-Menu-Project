package application;
import java.util.*;
import java.io.*;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


class McPatternsPresenter {
    //This is the class that will handle the model <-> UI communication.
    ItemMenu model = new ItemMenu();
    McPatternsGUI view;

    void loadMenuItems() {
        // TODO: Add code to read a file and load the menu items.

        File fl = new File("C:\\My Stuff\\Software Projects\\Java\\workspace\\CS151HW4R\\src\\application\\rs.txt");
        
        try{
            Scanner input = new Scanner(fl);
            String ln ="";
            
            while(input.hasNextLine()){
                ln = input.nextLine();               
                double itemPrice = Double.parseDouble(ln.substring(ln.indexOf("|")+1));               
                String itemName = ln.substring(0, ln.indexOf("|"));
                model.newItem(new ItemListed(itemName, itemPrice));               
            }
        }catch(FileNotFoundException notThere){
            notThere.printStackTrace();
        }
              
    } 

    void attachView(McPatternsGUI view) {
        this.view = view;
    }
    // Add functions to return the menu items. 
}