package ru.job4j.ood.isp.menu;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TodoApp {
    private static final ActionDelegate NO_ACTION = () -> System.out.println("No action!");
    private static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MENU_ACTION =
            """
            /////////////////////////////
            1. Add item in root.
            2. Add item in parent.
            3. Call action.
            4. Show menu.
            5. Exit.
            /////////////////////////////
            Enter question:""";

    private final Menu menu;
    private final MenuPrinter printer;
    private final List<ActionDelegate> listActionItem = List.of(
            () -> {
                System.out.println("--Add item in root.--");
                System.out.println("Enter name item:");
                String item = SCANNER.nextLine();
                 if (addInRoot(item, DEFAULT_ACTION)) {
                     System.out.println("Item added.");
                 } else {
                     System.out.println("Item not added.");
                 }
            },
            () -> {
                System.out.println("--Add item in parent.--");
                System.out.println("Enter name parent:");
                String parent = SCANNER.nextLine();
                System.out.println("Enter name item:");
                String item = SCANNER.nextLine();
                if (addInParent(parent, item, DEFAULT_ACTION)) {
                    System.out.println("Item added.");
                } else {
                    System.out.println("Item not added.");
                }
            },
            () -> {
                System.out.println("--Call action.--");
                System.out.println("Enter name item:");
                String item = SCANNER.nextLine();
                callAction(item);
            },
            () -> {
                System.out.println("--Show menu.--");
                showMenu();
            },
            () -> {
                System.out.println("Exit app.");
            }
    );

    public TodoApp(Menu menu, MenuPrinter printer) {
        this.menu = menu;
        this.printer = printer;
    }

    public boolean addInRoot(String childName, ActionDelegate actionDelegate) {
        return menu.add(Menu.ROOT, childName, actionDelegate);
    }

    public boolean addInParent(String parentName, String childName, ActionDelegate actionDelegate) {
        return menu.add(parentName, childName, actionDelegate);
    }

    public void callAction(String itemName) {
        Optional<Menu.MenuItemInfo> optional = menu.select(itemName);
        if (optional.isPresent()) {
            optional.get().getActionDelegate().delegate();
        } else {
            NO_ACTION.delegate();
        }
    }

    public void showMenu() {
        printer.print(menu);
    }

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        TodoApp app = new TodoApp(menu, printer);
        int question = 0;
        while (question != 5) {
            System.out.println(MENU_ACTION);
            question = Integer.parseInt(SCANNER.nextLine());
            if (question < 1 || question > 5) {
                System.out.println("Wrong question!");
                continue;
            }
            app.listActionItem.get(question - 1).delegate();
        }
    }
}
