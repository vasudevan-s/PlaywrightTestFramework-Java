package pro.vasudevan.PlaywrightTestFramework.base;

import com.microsoft.playwright.Locator;
import org.apache.commons.io.FileUtils;
import pro.vasudevan.PlaywrightTestFramework.config.IPlaywrightTestConfig;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
Created By: Vasudevan Sampath

 ElementBase.java has web element specific methods.
 Also, initializes object repository file (expects object.repo.properties under src/test/resources in your test suite).
 Use getLocatorRefByKey() to get a Locator object for a valid object id defined in the properties file
 and use getLocatorRef() method if direct reference is needed without storing in the properties file
 */
public class ElementBase implements IPlaywrightTestConfig {

    static Map<String, String> map = new HashMap<>();

    protected static void initObjectRepo() throws Exception {
        final Properties properties = new Properties();

        if (map.size() == 0) {
            properties.load(new FileInputStream(new File(FileUtils.getFile("src", "test", "resources", "object.repo.properties").getAbsolutePath())));
            map = (Map) properties;    // map Properties (key/value pair) to a Hashmap
        }
    }

    public static Locator getLocatorRefByKey(String objectKey) {
        return IPlaywrightTestConfig.getPageObject().locator(map.get(objectKey));
    }

    public static Locator getLocatorRef(String objectId) {
        return IPlaywrightTestConfig.getPageObject().locator(objectId);
    }
}
