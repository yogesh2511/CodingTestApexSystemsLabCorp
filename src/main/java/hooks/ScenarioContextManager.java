package hooks;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class ScenarioContextManager {

    private static final Map<String, Object> scenarioData = new HashMap<>();
   
    public static void setResponse(Response response) {
        scenarioData.put("response", response);
    }

    public static Response getResponse() {
        return (Response) scenarioData.get("response");
    }

    public static void setResponseBodyAsMap(Map<String, Object> bodyMap) {
        scenarioData.put("responseBodyMap", bodyMap);
    }

    public static Map<String, Object> getResponseBodyAsMap() {
        return (Map<String, Object>) scenarioData.get("responseBodyMap");
    }

    public static void clear() {
        scenarioData.clear();
    }
}
