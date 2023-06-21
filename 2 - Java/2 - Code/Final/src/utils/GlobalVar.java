package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;

public class GlobalVar {
    private boolean admin = false;
    private static final String VAR_FILE_PATH = "res/vars.json";

    public GlobalVar() {
        loadVars();
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
        saveVars();
    }

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
