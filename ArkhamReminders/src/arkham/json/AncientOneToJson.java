package arkham.json;

import arkham.mechanics.AncientOne;
import com.google.gson.*;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class AncientOneToJson {

    public static boolean convertAncientOne(AncientOne ao) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.setFieldNamingStrategy(Field::getName);
        Gson gson = gsonBuilder.create();

        String result = gson.toJson(ao);

        try(FileWriter fw = new FileWriter("resources/AncientOnes.json", true)) {
            fw.write(result);
            fw.append(',');
            fw.append('\n');
            fw.flush();
        } catch (IOException e) {
            System.err.println("Error occurred during writing into AncientOnes.json");
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
