package com.rentlink.rentlink.utils;

import java.util.List;
import org.instancio.Instancio;

public interface DataGenerator<T> {

    default List<T> generateList(Class<T> clazz, int size) {
        return Instancio.ofList(clazz).size(size).create();
    }
}
