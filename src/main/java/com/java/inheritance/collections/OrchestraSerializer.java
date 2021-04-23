package com.java.inheritance.collections;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class OrchestraSerializer {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void serialize(Orchestra orchestra) throws IOException {

        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM-dd HHmmss");
        LocalDateTime NOW = LocalDateTime.now();
        FileWriter writer = new FileWriter(
                String.format("./src/main/resources/Orchestra %s.json",
                        FORMATTER.format(NOW)));
        String gsonString = MAPPER.writeValueAsString(orchestra);
        writer.write(gsonString);
        writer.close();
        System.out.println("Коллекция успешно сериализована!");
    }

    public static Orchestra deserialize(String fileName) throws IOException, FileNotFoundException {
        FileReader reader = new FileReader(String.format("./src/main/resources/%s.json", fileName));
        Scanner scanner = new Scanner(reader);
        StringBuilder gsonString= new StringBuilder();
        while (scanner.hasNextLine()) {
            gsonString.append(scanner.next());
        }
        reader.close();
        return MAPPER.readValue(String.valueOf(gsonString), Orchestra.class);
    }

}
