package utilities;

import java.util.HashMap;
import java.util.Map;

public class AliasManager {
    private static final Map<String, Object> ALIAS_REGISTRY = new HashMap<>();
    
    // Store any type of value with an alias
    public static void store(String alias, Object value) {
        ALIAS_REGISTRY.put(alias, value);
    }
    
    // Retrieve value by alias with type safety
    @SuppressWarnings("unchecked")
    public static <T> T get(String alias) {
        return (T) ALIAS_REGISTRY.get(alias);
    }
    
    // Clear aliases between scenarios
    public static void clear() {
        ALIAS_REGISTRY.clear();
    }
}