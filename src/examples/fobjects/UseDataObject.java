package examples.fobjects;

// Shows how instances are kept seperate
// Will create 2 instances of our DataObject
public class UseDataObject {
    public static void main(String[] args) throws Exception {

       // default constructor - what is value of data
       DataObject one = new DataObject();
       System.out.println("one: " + one.getData());

       // call specific constructor to initialize the data
       DataObject two = new DataObject(22);
       System.out.println("two: " + two.getData());

       // they are seperate instances of DataObject. chaning one will not change the other
       two.setData(8);
       System.out.println("two: " + two.getData());
       // still 42
       System.out.println("one: " + one.getData());
       // lets square two
       two.squareData();
       System.out.println("two: " + two.getData());
       // stil 42
       System.out.println("one: " + one.getData());
    }
}
