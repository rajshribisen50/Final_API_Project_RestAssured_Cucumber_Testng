package utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*Debug failures

Track API flow
*/

public class LoggerUtil {
    public static Logger getLogger(Class<?> cls) {
        return LogManager.getLogger(cls);
    }
}