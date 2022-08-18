package uiLogic.utils;

public class TestHelper {
    public static void sleep5Seconds() {
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
