package arkham.json;

import arkham.mechanics.AncientOne;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;

public class AncientOneJson {

    private final String filepath;

    public AncientOneJson(String filepath) {
        this.filepath = filepath;
    }

    public AncientOneJson() {
        this.filepath = "resources/AncientOnes.json";
    }

    /**
     * Adds an Ancient One into JSON, containing an array of AOs
     * @param ao Ancient One Java object
     * @return true, if successful, false otherwise
     */
    public boolean add(AncientOne ao) {
        Gson gson = new Gson();

        try(FileReader reader = new FileReader(filepath)) {
            Collection<AncientOne> ancients = gson.fromJson(reader, new TypeToken<Collection<AncientOne>>() {}.getType());
            ancients.add(ao);

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            gsonBuilder.setFieldNamingStrategy(Field::getName);
            gson = gsonBuilder.create();

            String result = gson.toJson(ancients);

            FileWriter fw = new FileWriter(filepath);
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

    /**
     * Returns a Collection of Ancient Ones, written in JSON file
     * @return Collection of Ancient Ones
     */
    @Nullable
    public Collection<AncientOne> extractCollection() {
        Gson gson = new Gson();
        Collection<AncientOne> ancients = null;

        try(FileReader reader = new FileReader(filepath)) {
            ancients = gson.fromJson(reader, new TypeToken<Collection<AncientOne>>() {}.getType());

        } catch(FileNotFoundException e) {
            System.err.println("Error during json read");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error during json IO");
            e.printStackTrace();
        }

        return ancients;
    }

    /**
     * Returns a specific Ancient One from JSON file
     * @param name Name of AO
     * @return Ancient One Java object
     */
    @Nullable
    public AncientOne extractAO(String name) {
        Gson gson = new Gson();
        Collection<AncientOne> ancients = null;
        AncientOne ao = null;

        try(FileReader reader = new FileReader(filepath)) {
            ancients = gson.fromJson(reader, new TypeToken<Collection<AncientOne>>() {}.getType());

        } catch(FileNotFoundException e) {
            System.err.println("Error during json read");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error during json IO");
            e.printStackTrace();
        }

        assert ancients != null;
        for(AncientOne x: ancients) {
            if(x.getName().equals(name))
                ao = x;
        }

        return ao;
    }
}
