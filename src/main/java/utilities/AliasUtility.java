package utilities;

import java.util.HashMap;
import java.util.Map;

public class AliasUtility {

	 // A private Map to store aliases and their associated values
    private static final Map<String, String> aliasStore = new HashMap<>();

    // Private constructor to prevent instantiation
    private AliasUtility() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // Static method to add an alias at runtime
    public static void storeAlias(String alias, String value) {
        aliasStore.put(alias, value);
        System.out.println("Alias stored: " + alias + " -> " + value);
    }

    // Static method to retrieve a value by alias
    public static String getValue(String alias) {
        return aliasStore.getOrDefault(alias, "Alias not found");
    }
}
