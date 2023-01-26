package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;
    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        assertThat(new Menu.MenuItemInfo("Купить хлеб",
                List.of(), STUB_ACTION, "1.1.1."))
                .isEqualTo(menu.select("Купить хлеб").get());
        assertThat(new Menu.MenuItemInfo("Купить молоко",
                List.of(), STUB_ACTION, "1.1.2."))
                .isEqualTo(menu.select("Купить молоко").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenPrintMenuThen() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        MenuPrinter printer = new SimpleMenuPrinter();
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        String tab = SimpleMenuPrinter.INDENT;
        StringBuilder expect = new StringBuilder();
        expect.append("1.Сходить в магазин").append(System.lineSeparator())
                .append(tab).append("1.1.Купить продукты").append(System.lineSeparator())
                .append(tab.repeat(2)).append("1.1.1.Купить хлеб").append(System.lineSeparator())
                .append(tab.repeat(2)).append("1.1.2.Купить молоко").append(System.lineSeparator())
                .append("2.Покормить собаку").append(System.lineSeparator());
        printer.print(menu);
        assertThat(expect.toString()).isEqualTo(output.toString());
        System.setOut(System.out);
    }
}