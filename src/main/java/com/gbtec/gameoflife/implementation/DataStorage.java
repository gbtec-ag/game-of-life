package com.gbtec.gameoflife.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class DataStorage {

    private static Map<Integer, boolean[][]> data = new HashMap<>();
    private static final File file = new File("src/main/resources/storage.json");

    /**
     * Sets the data for the given storageId
     * @param storageId The storageId
     * @param generationData The data
     */
    public static void setData(int storageId, @NotNull boolean[][] generationData) {
        data.put(storageId, generationData);
        updateFile();
    }

    /**
     * Gets the data for the given storageId
     * @param storageId The storageId
     * @return The data
     */
    @Nullable
    public static Data getData(int storageId) {
        if (isStorageEmpty(storageId))
            return null;

        return new Data(storageId, data.get(storageId));
    }

    /**
     * Checks if the storage is empty
     * @param storageId The storageId
     * @return True if the storage is empty
     */
    public static boolean isStorageEmpty(int storageId) {
        return !data.containsKey(storageId);
    }

    /**
     * Reads the data from the file
     */
    public static void readFromFile() {
        try {
            if (!file.exists())
                file.createNewFile();
            Arrays.asList(new ObjectMapper().readValue(file, Data[].class)).forEach(d -> data.put(d.storageId(), d.generationData()));
        } catch (Exception ignored) {
        }
    }

    /**
     * Updates the file with the current data
     */
    public static void updateFile() {
        try {
            List<Data> dataList = new ArrayList<>();
            data.forEach((k, v) -> dataList.add(new Data(k, v)));
            new ObjectMapper().writeValue(file, dataList);
        } catch (IOException e) {
            LoggerFactory.getLogger(DataStorage.class).error("Error while writing to file", e);
        }
    }

    public record Data(int storageId, boolean[][] generationData) {
    }

}
