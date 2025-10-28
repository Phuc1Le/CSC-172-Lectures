import java.util.*;
public class Main{
    public static void main(String[] args) {
        Integer [] intArr = {1,2,3,4,5};
        Generics.printarray(intArr);

        Box<Integer> ibox = new Box<>();
        Box<Double> dbox = new Box<>();

        ibox.set(3);
        dbox.set(4.0);

        Box<Integer> ibox2 = new Box<>();
        ibox2.set(5);
        System.out.println(ibox.compareTo(ibox2)); 
        System.out.println(ibox.get());
        System.out.println(dbox.get());

        Integer[] nums = {1, 3, 5, 7};
        System.out.println("Count > 4 = " + Box.count(nums, 4));

        // Wildcards
        ArrayList<? extends Number> arr = new ArrayList<>(); //An ArrayList is a resizable array that can grow as needed.
        ArrayList<? super Number> sarr = new ArrayList<>();
        sarr.add(1);
        sarr.add(2);
        sarr.add(2.0);
        System.out.println(sarr);
        for(Object i: sarr){
            System.out.println(i);
        }
        // Unbounded wildcard
        ArrayList<?> wilda = new ArrayList<>();
        //A HashSet is a collection where every element is unique - no duplicates are allowed.
        HashSet<String> cars = new HashSet<String>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("BMW");  // Duplicate
        System.out.println(cars);
        
        Iterator<String> it = cars.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}