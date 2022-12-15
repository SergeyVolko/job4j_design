package ru.job4j.ood.lsp.contolqu;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    public void whenAddFoodInWarehouse() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date expire = dateFormat.parse("20-01-2022");
        Date create = dateFormat.parse("01-01-2022");
        Date add = dateFormat.parse("03-01-2022");
        AbstractStore store = new Warehouse("Warehouse");
        Food food = new Food("Apple", expire, create, 100, 5);
        store.add(food, add);
        assertThat(store.getFoods()).contains(food);
    }

    @Test
    public void whenNoAddFoodInWarehouse() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date expire = dateFormat.parse("20-01-2022");
        Date create = dateFormat.parse("01-01-2022");
        Date add = dateFormat.parse("19-01-2022");
        AbstractStore store = new Warehouse("Warehouse");
        Food food = new Food("Apple", expire, create, 100, 5);
        store.add(food, add);
        assertThat(store.getFoods()).isEmpty();
    }

    @Test
    public void whenNoAddFoodInWarehouseThenException() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date expire = dateFormat.parse("20-01-2022");
        Date create = dateFormat.parse("01-01-2022");
        Date add = dateFormat.parse("19-12-2021");
        AbstractStore store = new Warehouse("Warehouse");
        Food food = new Food("Apple", expire, create, 100, 5);
        assertThatThrownBy(() -> store.add(food, add)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenAddFoodInShop() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date expire = dateFormat.parse("11-01-2022");
        Date create = dateFormat.parse("01-01-2022");
        Date add = dateFormat.parse("06-01-2022");
        AbstractStore store = new Shop("Shop");
        Food food = new Food("Apple", expire, create, 100, 5);
        store.add(food, add);
        assertThat(store.getFoods()).contains(food);
    }

    @Test
    public void whenNoAddFoodInShop() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date expire = dateFormat.parse("11-01-2022");
        Date create = dateFormat.parse("01-01-2022");
        Date add = dateFormat.parse("09-01-2022");
        AbstractStore store = new Shop("Shop");
        Food food = new Food("Apple", expire, create, 100, 5);
        store.add(food, add);
        assertThat(store.getFoods()).isEmpty();
    }

    @Test
    public void whenAddFoodInTrash() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date expire = dateFormat.parse("11-01-2022");
        Date create = dateFormat.parse("01-01-2022");
        Date add = dateFormat.parse("09-01-2022");
        AbstractStore store = new Trash("Trash");
        Food food = new Food("Apple", expire, create, 100, 5);
        store.add(food, add);
        assertThat(store.getFoods()).contains(food);
    }

    @Test
    public void whenNoAddFoodInTrash() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date expire = dateFormat.parse("11-01-2022");
        Date create = dateFormat.parse("01-01-2022");
        Date add = dateFormat.parse("06-01-2022");
        AbstractStore store = new Trash("Trash");
        Food food = new Food("Apple", expire, create, 100, 5);
        store.add(food, add);
        assertThat(store.getFoods()).isEmpty();
    }

    @Test
    public void whenAddFoodControl() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date expire = dateFormat.parse("11-01-2022");
        Date create = dateFormat.parse("01-01-2022");
        Date add = dateFormat.parse("06-01-2022");
        Food food = new Food("Apple", expire, create, 100, 5);
        Map<String, Store> map = Map.of(
                "Trash", new Trash("Trash"),
                "Shop", new Shop("Shop"),
                "Warehouse", new Warehouse("Warehouse")
        );
        ControlQuality controlQuality = new ControlQuality(map);
        controlQuality.addFoodInStores(food, add);
        assertThat(((AbstractStore) controlQuality.getStore("Shop")).getFoods()).contains(food);

    }

    @Test
    public void whenNoAddFoodControl() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date expire = dateFormat.parse("11-01-2022");
        Date create = dateFormat.parse("01-01-2022");
        Date add = dateFormat.parse("06-01-2022");
        Food food = new Food("Apple", expire, create, 100, 5);
        Map<String, Store> map = Map.of(
                "Trash", new Trash("Trash"),
                "Shop", new Shop("Shop"),
                "Warehouse", new Warehouse("Warehouse")
        );
        ControlQuality controlQuality = new ControlQuality(map);
        controlQuality.addFoodInStores(food, add);
        assertThat(((AbstractStore) controlQuality.getStore("Trash")).getFoods()).isEmpty();

    }

    @Test
    public void whenAddFoodAndStoreControl() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date expire = dateFormat.parse("11-01-2022");
        Date create = dateFormat.parse("01-01-2022");
        Date add = dateFormat.parse("06-01-2022");
        AbstractStore shop = new Shop("Shop");
        Food food = new Food("Apple", expire, create, 100, 5);
        Map<String, Store> map = Map.of(
                "Trash", new Trash("Trash"),
                shop.getNameStore(), shop,
                "Warehouse", new Warehouse("Warehouse")
        );
        ControlQuality controlQuality = new ControlQuality(map);
        controlQuality.addFoodInStores(food, add, shop);
        assertThat(((AbstractStore) controlQuality.getStore("Shop")).getFoods()).contains(food);
    }
}