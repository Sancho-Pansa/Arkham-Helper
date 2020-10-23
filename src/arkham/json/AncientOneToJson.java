package arkham.json;

import arkham.mechanics.AncientOne;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Optional;

public class AncientOneToJson {

    public static boolean convertAncientOne(AncientOne ao, String filepath) {
        String fPath = Optional.ofNullable(filepath).orElse("resources/AncientOnes.json");
        Gson gson = new Gson();

        try(FileReader reader = new FileReader(fPath)) {

            Collection<AncientOne> ancients = gson.fromJson(reader, new TypeToken<Collection<AncientOne>>() {}.getType());
            ancients.add(ao);

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            gsonBuilder.setFieldNamingStrategy(Field::getName);
            gson = gsonBuilder.create();

            String result = gson.toJson(ancients);

            FileWriter fw = new FileWriter(fPath);
            fw.write(result);
            fw.flush();

        } catch(FileNotFoundException e) {
            System.err.println("Error during json read");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.err.println("Error during json IO");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
