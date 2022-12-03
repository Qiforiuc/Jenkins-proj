package Context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static ScenarioContext instance;
    Map<String, Object> contextMap;
    private ScenarioContext()
    {
        contextMap = new HashMap<String, Object>();
    }
    public static ScenarioContext getInstance()
    {
        if(instance == null)
        {
            instance = new ScenarioContext();
        }
        return instance;
    }

    public Object getData(String key)
    {
        return contextMap.get(key);
    }

    public void saveData(String key, Object data)
    {
        contextMap.put(key,data);
    }

}
