package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogbookSerializer {

    private static Logger logger = LogManager.getLogger(LogbookSerializer.class);

    private LogbookSerializer(){}

    public static String serializeToString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String resultString = null;
        try {
            resultString = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.info("There was problem with serialising logbook {} ", e);
        }
        return resultString;
    }
}