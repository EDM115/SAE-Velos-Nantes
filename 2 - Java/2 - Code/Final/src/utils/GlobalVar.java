package utils;

import java.io.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * GlobalVar, allows to know if the user is an admin or not
 */
public class GlobalVar {

    /**
     * Admin or not
     */
    private boolean admin = false;

    /**
     * Path to the file containing the variables
     */
    private static final String VAR_FILE_PATH = "res/vars.json";

    /**
     * GlobalVar constructor
     */
    public GlobalVar() {
        loadVars();
    }

    /**
     * Get if the user is an admin or not
     * @return boolean admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Set if the user is an admin or not
     * @param admin boolean
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
        saveVars();
    }

    /**
     * Load the variables from the file
     */
    private void loadVars() {
        try {
            File file = new File(VAR_FILE_PATH);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                this.admin = json.get("admin").getAsBoolean();
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save the variables in the file
     */
    private void saveVars() {
        try {
            File file = new File(VAR_FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            JsonObject json = new JsonObject();
            json.addProperty("admin", admin);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(json);

            FileWriter writer = new FileWriter(file);
            writer.write(jsonString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
