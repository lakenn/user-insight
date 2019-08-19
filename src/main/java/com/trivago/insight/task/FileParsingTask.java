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
public class FileParsingTask implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${selections.filepath}")
    private String selectionsFilePath;

    @Value("${clicks.filepath}")
    private String clicksFilePath;

    @Autowired
    private FrequencyCountUtil counter;

    @Override
    public void run() {

            long startTime = System.currentTimeMillis();
            try(Stream<String[]> clickStream = FileDataStream.getDataStream(clicksFilePath)){
                clickStream.forEach(click -> counter.updateHotelClick(click[1], Integer.parseInt(click[2])));
            } catch (IOException e) {
                logger.error("Error reading " + clicksFilePath, e);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("That took " + (endTime - startTime) + " milliseconds");

        try(Stream<String[]> selectionStream = FileDataStream.getDataStream(selectionsFilePath)){
                selectionStream.forEach(selection -> counter.updateUserSelection(selection[1], Integer.parseInt(selection[2])));
            } catch (IOException e) {
            logger.error("Error reading " + selectionsFilePath, e);
        }

    }
}
