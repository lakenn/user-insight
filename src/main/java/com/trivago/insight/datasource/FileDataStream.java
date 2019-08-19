package com.trivago.insight.datasource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileDataStream {

    public static Stream<String[]> getDataStream(String filepath) throws IOException {
        //Pattern comma = Pattern.compile("\\s*,\\s*");
        Stream<String[]> stream = Files.lines(Paths.get(filepath)).map(line -> line.split(",")).parallel();
        return stream;
    }
}