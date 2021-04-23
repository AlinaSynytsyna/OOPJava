package com.java.inheritance.collections;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrchestraSerializer {


    public static void serialize(Orchestra orchestra) throws IOException {
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM-dd HHmmss");
        LocalDateTime NOW = LocalDateTime.now();
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(String.format("./src/main/resources/Orchestra %s.dat",
                FORMATTER.format(NOW))))) {
            output.writeObject(orchestra);
            System.out.println("Коллекция успешно сериализована!");
        } catch (IOException ex) {
            System.out.println("Не удалось сериализовать коллекцию!");
            System.out.println(ex.getMessage());
        }
    }

    public static Orchestra deserialize(String fileName) throws ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(String.format("./src/main/resources/%s.dat", fileName)))) {
            return (Orchestra) input.readObject();
        } catch (IOException ex) {
            System.out.println("Не удалось десериализовать коллекцию!");
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
