import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//should have no methods. only values java objects(pojo)
class MyDto{
//    if use primitives, can't use null
    public final String name;

    public final String greeting;

    public final Integer age;

//    right click -> generate -> constructor
    public MyDto(String name, String greeting, Integer age) {
        this.name = name;
        this.greeting = greeting;
        this.age = age;
    }
}


//encapsulate the translating/load/store logic
class MyDao{
//    private static Gson gson = new Gson(); // Original version

//    this version of implementing will make the .json file and the components inside look neater.
    private static Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    // Java object (pojo) -> string
    //
    public void save(MyDto dto){
        String json = gson.toJson(dto);
        System.out.println(json); // {"name": "Brad","greeting":"Hello there","age":3456}

//        saves info somewhere else. this info can be read be another progream or my own program. info can be used for some time in the future.
        try {
            FileWriter writer = new FileWriter("src/" + dto.name + ".json");
            writer.write(json);
            writer.close();
    }   catch (IOException e) {
        throw new RuntimeException(e);
        }
    }

//    converts string -> back to java object (pojo)
    public MyDto load(String name){
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get("src/" + name + ".json")));
            System.out.println(fileContent);
            MyDto convertedDto = gson.fromJson(fileContent, MyDto.class); //gets fileContent and converts it to the "MyDto.class" class type.
            return convertedDto;
        } catch (IOException e) {
            throw   new RuntimeException(e);
        }
    }
}

public class JsonDemo {
    public static void main(String[] args) {
        MyDao dao = new MyDao();
//        run save first, before running load. Since we have to make sure the .json file exists
        dao.save(new MyDto("Brad", "Hello there", 3456)); //must save object of same types, String, String, Integer //use this when we don't have "MyDto load" class
//        MyDto loaded = dao.load("Braden"); // uses MyDto load class
//        System.out.println(loaded.greeting);  //remembers from previous runs
//    can edit.json files manually by eiditing the file contents by opening them up.
    }
}
