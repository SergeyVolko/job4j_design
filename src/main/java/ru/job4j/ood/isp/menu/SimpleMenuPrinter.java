package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class SimpleMenuPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Menu.MenuItemInfo> iterator = menu.iterator();
        int count;
        String indent = "----";
        while (iterator.hasNext()) {
            Menu.MenuItemInfo menuItemInfo = iterator.next();
            count = menuItemInfo.getNumber().split("\\.").length - 1;
            stringBuilder.append(indent.repeat(count))
                    .append(menuItemInfo.getNumber())
                    .append(menuItemInfo.getName())
                    .append(System.lineSeparator());
        }
        System.out.print(stringBuilder);
    }
}
