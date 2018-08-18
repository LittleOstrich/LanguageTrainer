package configs;

import java.util.Properties;

public class ConfigManager {

    protected static Properties prop;

    public void readConfigs() {
	if (prop == null)
	    loadConfigs();
	for (Object o : prop.keySet())
	    System.out.println(o + "=" + prop.get(o));
    }

    public void loadConfigs() {
	prop = new Properties();

    }

    public void saveConfigs() {

    }

    public void resetConfigs() {

    }
}
