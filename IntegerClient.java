import java.util.Scanner;

public class IntegerClient {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        DoubleLinkedList<Integer> list_1 = new DoubleLinkedList<Integer>();
        DoubleLinkedList<Integer> list_2 = new DoubleLinkedList<Integer>();
        int item;
        list_1.insertNode(2);
        list_1.insertNode(4);
        list_1.insertNode(23);
        list_1.insertNode(4);
        list_1.insertNode(21);
        list_1.insertNode(46);
        list_1.insertNode(13);
        list_1.insertNode(13);
        list_1.insertNode(6);
        list_1.insertNode(12);
        list_1.insertNode(6);
        list_1.insertNode(4);
        list_1.insertNode(15);

        System.out.println("Numbers inserted in list 1 are: 2, 4, 23, 4, 21, 46, 13, 13, 6, 12, 6, 13, 15");
        System.out.println("List 1 sorted is: " + list_1);
        System.out.println();

        //Testing toString methods
        System.out.println("Testing toString; List 1 is: " + list_1.toString());
        System.out.println("Testing recursive toString; List 1 is: " + list_1.recursiveToString());
        System.out.println();


        //List size test
        System.out.println("Testing size; List 1 size is: " + list_1.length());

        //testing backwards toString
        System.out.println("Testing backwards toString; List 1 backwards is: " + list_1.backwardsString() + "\nTesting recursive backwardstostring; List 1 backwards is; " + list_1.recursiveBackwardsString());
        System.out.println();

        //Testing delete integer from list
        System.out.println("Testing delete and copy into new list: ");
        int delete = getInt(sc, "Please enter an integer to delete: ");
        
            while(!list_1.isEmptyList()) {
                item = list_1.front();
                list_1.deleteNode(item);
                if (item != delete) {
                    list_2.insertNode(item);
                }
            }

        System.out.println("List 1 is: " + list_1);
        System.out.println("List 2 without " + delete +" is: " + list_2);
        System.out.println();

        //Testing equals method
        System.out.println("Testing equals ");
        if (list_1.equals(list_2)) 
            System.out.println("List 1 and List 2 are equal");
        else 
          System.out.println("List 1 and List 2 are not equal");
        System.out.println();

        //Testing copy methods
        System.out.println("Testing copy: ");
        list_1.copy(list_2);
        System.out.println("List 1 after copy is: " + list_1);
        list_1.reversedCopy(list_2);
        System.out.println("List 1 after reversed copy is: " + list_1);
        System.out.println();


        //Testing reversePrint method 
        System.out.println("Testing reversePrint: \nList 2: " + list_2 + "\nList 2 reversed: ");
        list_2.reversePrint();
        System.out.println();

        //Testing search method
        System.out.println("Testing search in list 2: ");
        int searchInt = getInt(sc, "Please enter an integer to search for: ");
        boolean found = list_2.search(searchInt);
        if (found) 
            System.out.println(searchInt + " was foudn in list 2.");
        else 
            System.out.println(searchInt + " was not found in list 2.");

          //
        
    }
  
    //Get valid integer from user
    public static int getInt(Scanner sc, String prompt) {
        System.out.println(prompt);
        while (!sc.hasNextInt()) {
          sc.next();
          System.out.println("Invalid integer, please try again.");
        }
        return sc.nextInt();
    }
}