package pro.vasudevan.PlaywrightTestFramework.config;

import com.microsoft.playwright.*;
import org.testng.ITestContext;
import pro.vasudevan.PlaywrightTestFramework.constants.Global;

import java.util.Arrays;
import java.util.Map;

/*
Created By: Vasudevan Sampath

 IPlaywrightTestConfig.java has static methods for Playwright specifics.
 Supports all Chromium browsers (Chrome, MS Edge etc.), Safari and Firefox
 */
public interface IPlaywrightTestConfig
{
    ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    ThreadLocal<BrowserContext> beowserContextThreadLocal = new ThreadLocal<>();
    ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    static void init(ITestContext testContext) {
        Map<String, String> map = testContext.getCurrentXmlTest().getLocalParameters();

        switch (map.get("browserName").toLowerCase()) {
            case Global.Chromium -> {
                playwrightThreadLocal.set(Playwright.create());
                BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
                launchOptions.headless = Boolean.valueOf(map.get("headless"));
 //               launchOptions.slowMo = 1000.0;
                launchOptions.args = Arrays.asList("--start-fullscreen"); // not working yet. Researching
                browserThreadLocal.set(getPlaywright().chromium().launch(launchOptions));
                beowserContextThreadLocal.set(getBrowser().newContext());
                pageThreadLocal.set(getBrowserContext().newPage());
            }
            case Global.Webkit -> {
                playwrightThreadLocal.set(Playwright.create());
                BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
                launchOptions.headless = Boolean.valueOf(map.get("headless"));
 //               launchOptions.slowMo = 1000.0;
                launchOptions.args = Arrays.asList("--start-fullscreen"); // not working yet. Researching
                browserThreadLocal.set(getPlaywright().webkit().launch(launchOptions));
                beowserContextThreadLocal.set(getBrowser().newContext());
                pageThreadLocal.set(getBrowserContext().newPage());
            }
            case Global.Firefox -> {
                playwrightThreadLocal.set(Playwright.create());
                BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
                launchOptions.headless = Boolean.valueOf(map.get("headless"));
 //               launchOptions.slowMo = 1000.0;
                launchOptions.args = Arrays.asList("--start-fullscreen"); // not working yet. Researching
                browserThreadLocal.set(getPlaywright().firefox().launch(launchOptions));
                beowserContextThreadLocal.set(getBrowser().newContext());
                pageThreadLocal.set(getBrowserContext().newPage());
            }
        }
        getPage().navigate(map.get("launchURL"));
    }

    static Playwright getPlaywright() {
        return playwrightThreadLocal.get();
    }

    static Browser getBrowser() {
        return browserThreadLocal.get();
    }

    static BrowserContext getBrowserContext() {
        return beowserContextThreadLocal.get();
    }

    static Page getPage() {
        return pageThreadLocal.get();
    }

    static void tearDown() {
        if (getPlaywright() != null) {
            getPage().close();
            getBrowserContext().close();
            getBrowser().close();
            getPlaywright().close();
        }
    }
}
