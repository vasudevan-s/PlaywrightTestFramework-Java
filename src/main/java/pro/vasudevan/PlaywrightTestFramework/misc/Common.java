package pro.vasudevan.PlaywrightTestFramework.misc;

/*
Created By: Vasudevan Sampath

 Common.java has utility methods
 */
public class Common {

    public static void runTerminalCommand(String command) throws Exception {
        new ProcessBuilder(command).start();
    }
}
