package sql;

import sql.Enums.SqlModifier;
import sql.Enums.SqlType;

import java.util.ArrayList;
import java.util.List;

public class SqlAttribute {
    private Object value;
    private SqlType type;
    private List<SqlModifier> modifiers;

    public SqlAttribute(Object value, SqlType type) {
        this.value = value;
        this.type = type;
        this.modifiers = new ArrayList<>();
    }
    public SqlAttribute(Object value, SqlType type, List<SqlModifier> modifiers) {
        this.value = value;
        this.type = type;
        this.modifiers = modifiers;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public SqlType getType() {
        return type;
    }

    public void setType(SqlType type) {
        this.type = type;
    }

    public List<SqlModifier> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<SqlModifier> modifiers) {
        this.modifiers = modifiers;
    }
}
