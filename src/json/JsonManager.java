package json;

import org.json.JSONObject;

public class JsonManager {
    JSONObject jsonObject;

    public JsonManager(String data) {
        this.jsonObject = new JSONObject(data);
    }
}