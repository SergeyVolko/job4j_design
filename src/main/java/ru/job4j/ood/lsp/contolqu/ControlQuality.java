package ru.job4j.ood.lsp.contolqu;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControlQuality {
    private Map<String, Store> storeMap;

    public ControlQuality(Map<String, Store> storeMap) {
        this.storeMap = storeMap;
    }

    public boolean addFoodInStores(Food food, Date date) {
        boolean result = false;
        for (Store store : storeMap.values()) {
            if (store.add(food, date)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean addFoodInStores(Food food, Date date, Store store) {
        return store.add(food, date);
    }

    public  void resort(Date date) {
        List<Food> foods = storeMap.values().stream()
                .flatMap(s -> s.getFoods().stream())
                .collect(Collectors.toList());
        storeMap.values().forEach(Store::removeFoods);
        foods.forEach(e -> addFoodInStores(e, date));
    }
    public Store getStore(String storeName) {
        return storeMap.get(storeName);
    }

    public Store addStore(AbstractStore store) {
        return storeMap.put(store.getNameStore(), store);
    }
}
