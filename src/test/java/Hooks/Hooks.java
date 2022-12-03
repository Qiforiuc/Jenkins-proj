package Hooks;

import Context.ScenarioContext;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Hooks {
    public static Logger logger = LogManager.getLogger(Hooks.class);
    private  static final ScenarioContext SCENARIO_CONTEXT = ScenarioContext.getInstance();
    @Before
    public void setup()
    {
        Properties prop = new Properties();
        String filename = "src/main/resources/config.properties";

        try(FileInputStream inputStream = new FileInputStream(filename);){
            prop.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SCENARIO_CONTEXT.saveData("urlString", prop.getProperty("app.uri"));
    }
}
