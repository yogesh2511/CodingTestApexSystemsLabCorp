package utilities;

import java.util.HashMap;
import java.util.Map;

public class AliasUtility {

	 // A private Map to store aliases and their associated values
    private static final Map<String, Object> aliasStore = new HashMap<>();
   
    
    // Private constructor to prevent instantiation
    private AliasUtility() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // Static method to add an alias at runtime
    public static void storeAlias(String alias, Object value) {
        aliasStore.put(alias, value);
        System.out.println("Alias stored: " + alias + " -> " + value);
    }

    // Static method to retrieve a value by alias
    @SuppressWarnings("unchecked")
    public static <T> T getValue(String alias) {
        return (T) aliasStore.getOrDefault(alias, "Alias not found");
    }
    
    public static <T> T getValue(String alias, Class<T> type) {
        return type.cast(aliasStore.get(alias));
    }
    
    public static void clear() {
    	aliasStore.clear();
    }
}
