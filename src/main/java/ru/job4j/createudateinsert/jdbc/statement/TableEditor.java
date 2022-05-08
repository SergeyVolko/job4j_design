package ru.job4j.createudateinsert.jdbc.statement;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    private void actionInTable(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "create table if not exists %s();",
                tableName
        );
        actionInTable(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format(
                "drop table %s;",
                tableName
        );
        actionInTable(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "alter table %s ADD %s %s;",
                tableName,
                columnName,
                type
        );
        actionInTable(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "alter table %s drop column %s;",
                tableName,
                columnName
        );
        actionInTable(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "alter table %s rename column %s to %s;",
                tableName,
                columnName,
                newColumnName
        );
        actionInTable(sql);
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    private static Properties getProperties(String name) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream(name)) {
            properties.load(in);
        }
        return properties;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        try (TableEditor tableEditor = new TableEditor(TableEditor.getProperties("app.properties"))) {
            tableEditor.initConnection();
            DatabaseMetaData metaData = tableEditor.connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());

            String tableName = "my_table";
            System.out.println("Удадение таблицы.");
            tableEditor.dropTable(tableName);

            System.out.println("Добавление таблицы.");
            tableEditor.createTable(tableName);
            System.out.println(TableEditor.getTableScheme(tableEditor.connection, tableName));


            System.out.println("Добавление колонки.");
            String columnName = "column_new";
            String typeColumn = "varchar(255)";
            tableEditor.addColumn(tableName, columnName, typeColumn);
            System.out.println(TableEditor.getTableScheme(tableEditor.connection, tableName));

            System.out.println("Переименование колонки.");
            String columnNameNew = "new_name_column";
            tableEditor.renameColumn(tableName, columnName, columnNameNew);
            System.out.println(TableEditor.getTableScheme(tableEditor.connection, tableName));

            System.out.println("Удаление колонки.");
            tableEditor.dropColumn(tableName, columnNameNew);
            System.out.println(TableEditor.getTableScheme(tableEditor.connection, tableName));
        }
    }
}
