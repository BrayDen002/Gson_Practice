import java.util.HashSet;
import java.util.Set;

interface MyInterface<MyPlaceHolder>{ //defer choosing a type to the implementing class
//    MyPlaceHolder can be any label, it's what ever makes sense to label it as needed.

    void doStuff(MyPlaceHolder key);

    void saveItem(MyPlaceHolder item);
}
//<MyGeneric> makes the class a generic class
class ClassA<MyGeneric> implements MyInterface<MyGeneric>{ //if we know we want this class to only be specific variable like "String" or "Integer" then we implement it as that.
    // However, for this tutorial we have it as a generic so it can be used as any when implemented in further classes.

//    no option to override and needs doStuff method since the interface has it.

    private Set<MyGeneric> data = new HashSet<>();

    @Override //it's for looks, tells others that these methods are overridden
    public void doStuff(MyGeneric key){
        if(data.contains(key)){
            System.out.println(key + " was found in the data!");
        }else{
            System.out.println("Your item was not found");
        }
    }
    @Override
    public void saveItem(MyGeneric item){
        data.add(item);
    }
}

public class GenericInterfaceDemo {
    public static void main(String[] args) {
//    does not compile so we have to create a special implementation for the interface.
//        MyInterface myInterface = new MyInterface();

// It is ClassA<String> because we want this to specifically be a string type
        ClassA<String> classA = new ClassA<>(); //Classes are implemented with the "<>" since the class it's implementing from has it and we must match them.
        classA.doStuff("Hello");

        classA.saveItem("Hello");
        classA.doStuff("Hello");
// will find the data if the "keys" are the same

//        can be an integer too since the OG class is a generic class.
        ClassA<Integer> classA1 = new ClassA<>();
        classA1.saveItem(1);
        classA1.saveItem(2);
        classA1.doStuff(3); //miss
        classA1.doStuff(1); //hit

    }
}
