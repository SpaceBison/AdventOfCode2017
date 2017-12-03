import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class Common {
    public static String getInputForDay(int day) {
        try {
            return IOUtils.toString(Common.class.getResourceAsStream("day" + day), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
