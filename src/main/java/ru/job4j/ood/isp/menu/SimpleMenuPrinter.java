package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class SimpleMenuPrinter implements MenuPrinter {

    public static final String INDENT = "----";

    @Override
    public void print(Menu menu) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Menu.MenuItemInfo> iterator = menu.iterator();
        int count;
        while (iterator.hasNext()) {
            Menu.MenuItemInfo menuItemInfo = iterator.next();
            count = menuItemInfo.getNumber().split("\\.").length - 1;
            stringBuilder.append(INDENT.repeat(count))
                    .append(menuItemInfo.getNumber())
                    .append(menuItemInfo.getName())
                    .append(System.lineSeparator());
        }
        System.out.print(stringBuilder);
    }
}
