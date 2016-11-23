package top.dongzeviva.config;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by ze.dong on 2016/11/23.
 */
public class ResourcesConfig {

    private static ResourcesConfig config = null;
    private static HashMap<String, String> resourceMap = new HashMap<String, String>();
    private static String PATH = "/application.properties";

    public synchronized static ResourcesConfig getInstance() {
        if (config == null) {
            config = new ResourcesConfig();
        }
        return config;
    }

    public String getMessage(String key, String defaults) {

        if (resourceMap.get(key) == null) {
            return defaults;
        } else {
            return resourceMap.get(key);
        }
    }

    public boolean init() {
        try {
            InputStream is = this.getClass().getResourceAsStream(ResourcesConfig.PATH);
            if (is != null) {
                Properties p = new Properties();
                p.load(is);
                is.close();
                Iterator<?> names = p.keySet().iterator();
                while (names.hasNext()) {
                    String key = (String) names.next();
                    String message = new String(((String) p.get(key)).getBytes("ISO-8859-1"), "UTF-8");
                    resourceMap.put(key, message);
                }
                return true;
            } else {
                System.out.println("message-resource:" + PATH + " can not be finded!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Load message-resource failure!  key:" + PATH);
            return false;
        }
    }
}

