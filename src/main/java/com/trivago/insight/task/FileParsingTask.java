package com.trivago.insight.task;

import com.trivago.insight.datasource.FileDataStream;
import com.trivago.insight.util.FrequencyCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Stream;

@Component
public class FileImportService implements InitializingBean{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${selections.filepath}")
    private String selectionsFilePath;

    @Value("${clicks.filepath}")
    private String clicksFilePath;

    @Autowired
    private FrequencyCountUtil counter;
    
    

    @Override
    public void afterPropertiesSet() {

            long startTime = System.currentTimeMillis();
           
            try(Stream<String> csvLine = Files.line(path)){
                String[] cells = split(csvLine);
                int userId= ..
                userRegistry.get(userId).recordHotesClick(...);
                
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("That took " + (endTime - startTime) + " milliseconds");

            //TODO implement the other one in the same manner

    }
}
