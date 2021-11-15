package examples.hcollections;

// not the import that is needed - ArrayList is 'built in' to Java, but not part of the core functions
import java.util.ArrayList;

public class StringList {
    public static void main(String[] args) {
        
        // define an array list that can hold strings
        // the 'type' inside the <> tells Java that we will have Strings inside this ArrayList
        ArrayList<String> myList = new ArrayList<>();

        // an array can't grow, but an ArrayList can
        myList.add("2491");
        myList.add("No Mythic");
        myList.add("Robotics");
        // add item out of order
        myList.add(1, " ");
        // remove an item
        myList.remove(2);

        // a new type of loop - "forEach" for every String in myList assign the value to word
        for (String word : myList) {
            System.out.print(word);            
        }
    }
}
