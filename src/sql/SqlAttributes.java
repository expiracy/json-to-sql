package sql;

import sql.Enums.SqlModifier;
import sql.Enums.SqlType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlAttributes {
    private Map<String, SqlAttribute> keysToAttributes = new HashMap<>();

    public void storeAttribute(String key, Object value, SqlType type) {
        if (this.keysToAttributes.containsKey(key)) return;

        this.keysToAttributes.put(key, new SqlAttribute(value, type));
    }

    public void storeAttribute(String key, Object value, SqlType type, List<SqlModifier> modifiers) {
        if (this.keysToAttributes.containsKey(key)) return;

        this.keysToAttributes.put(key, new SqlAttribute(value, type, modifiers));
    }

    public SqlAttribute getAttribute(String key) {
        if (!this.keysToAttributes.containsKey(key)) return null;

        return this.keysToAttributes.get(key);
    }

}
