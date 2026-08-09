package com.dice.shop.util;

import java.io.*;

public class SerializationUtil {

    public static void serialize(Object object, String filePath) {
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(object);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка сохранения данных", e);
        }
    }

    public static <T> T deserialize(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {
            return (T) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Ошибка загрузки данных", e);
        }
    }
}