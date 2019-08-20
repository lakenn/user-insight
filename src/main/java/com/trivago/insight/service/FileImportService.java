package com.trivago.insight.service;

import com.trivago.insight.util.FrequencyCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class FileImportService implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${selections.filepath}")
    private String selectionsFilePath;

    @Value("${clicks.filepath}")
    private String clicksFilePath;

    @Autowired
    private FrequencyCountUtil counter;

    @Override
    public void afterPropertiesSet() throws IOException {
        try(Stream<String> csvLine = Files.lines(Paths.get(clicksFilePath))){
            csvLine.forEach(
                    line ->
                    {
                        String[] cells = line.split(",");
                        String userId = cells[1];
                        int hotelId = Integer.parseInt(cells[2]);
                        counter.updateHotelClick(userId, hotelId);
                    }
            );
        }

        try(Stream<String> csvLine = Files.lines(Paths.get(selectionsFilePath))){
            csvLine.forEach(
                    line ->
                    {
                        String[] cells = line.split(",");
                        String userId = cells[1];
                        int amenityId = Integer.parseInt(cells[2]);
                        counter.updateUserSelection(userId, amenityId);
                    }
            );
        }
    }
}
