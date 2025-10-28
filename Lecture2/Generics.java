//Strong type checking
//Elimination of cast
public class Generics{
    public static<E> void printarray(E [] a){
        for(E ele: a){
            System.out.print(ele + ", ");
        }
    }
}