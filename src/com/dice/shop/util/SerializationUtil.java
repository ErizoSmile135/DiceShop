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

    public static Object deserialize(String filePath) {

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(filePath))) {

            return inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Ошибка загрузки данных", e);
        }
    }
}
