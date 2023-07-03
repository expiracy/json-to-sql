package json;

import sql.Enums.SqlModifier;
import sql.Enums.SqlType;
import org.json.JSONArray;
import org.json.JSONObject;
import sql.SqlAttributes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class JsonAttributeExtractor {
    private JSONObject json;
    private SqlAttributes attributes = new SqlAttributes();

    public JsonAttributeExtractor(JSONObject json) {
        this.json = json;
    }

    public void extract() {
        this.extractId();

        // Identify the other attributes
        for (String key : this.json.keySet()) {
            Object value = this.json.get(key);

            if (value instanceof JSONObject) {
                // Need to create new table here
            }

            else if (value instanceof JSONArray) {
                // Need to create new table here
            }

            else if (value instanceof Integer) {
                this.attributes.storeAttribute(key, value, SqlType.INT);
            }

            else if (value instanceof BigInteger) {
                this.attributes.storeAttribute(key, value.toString(), SqlType.BIGINT);
            }

            else if (value instanceof Float) {
                this.attributes.storeAttribute(key, value, SqlType.FLOAT);
            }

            else if (value instanceof BigDecimal) {
                this.attributes.storeAttribute(key, value.toString(), SqlType.FLOAT);
            }

            else {
                this.attributes.storeAttribute(key, value.toString(), SqlType.VARCHAR);
            }
        }
    }

    private void extractId() {
        String key = "id";

        if (!this.json.has(key)) {
            SqlModifier[] modifiers = {SqlModifier.PRIMARY_KEY, SqlModifier.AUTO_INCREMENT, SqlModifier.UNIQUE};
            this.attributes.storeAttribute(key, 1, SqlType.INT, List.of(modifiers));
        }

        SqlModifier[] modifiers = {SqlModifier.PRIMARY_KEY, SqlModifier.UNIQUE};
        this.attributes.storeAttribute(key, 1, SqlType.INT, List.of(modifiers));
    }
}
