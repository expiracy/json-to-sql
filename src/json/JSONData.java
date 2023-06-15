package json;



import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;
import java.util.Map;

public class JSONData implements Iterable<Map.Entry<String, Object>> {
    public JSONObject data;

    public JSONData(String data) throws ParseException {
        JSONParser parser = new JSONParser();
        this.data = (JSONObject) parser.parse(JSONObject.escape(data));
    }

    public Iterator<Map.Entry<String, Object>> iterator() {
        return this.data.entrySet().iterator();
    }

}
