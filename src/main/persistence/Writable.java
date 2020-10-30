package persistence;

import org.json.JSONObject;

// code cited from the example repo JsonSerializationDemo, Interface Writable
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
