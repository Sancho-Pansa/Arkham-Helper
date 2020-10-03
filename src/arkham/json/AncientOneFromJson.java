package arkham.json;

import arkham.mechanics.AncientOne;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AncientOneFromJson {

    private static String filepath = "resources/AncientOnes.json";

    public static AncientOne getAncientOne() {
        String result;

        try {
            result = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (IOException e) {
            System.err.println("Error occurred during reading of AncientOnes.json");
            e.printStackTrace();
            return null;
        }

        Gson gson = new Gson();
        return gson.fromJson(result, AncientOne.class);
    }
}
