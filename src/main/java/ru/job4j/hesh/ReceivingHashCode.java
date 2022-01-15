package ru.job4j.hesh;

public class ReceivingHashCode {

    public static int hash(Object... values) {
        return hashCode(values);
    }

    public static int hashCode(Object... values) {
        if (values == null) {
            return 0;
        }
        int result = 1;
        for (Object element : values) {
            result = 31 * result + (element == null ? 0 : element.hashCode());
        }
        return result;
    }
}
