package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte one = 1;
        short two = 2;
        int three = 3;
        long four = 4;
        float five = 5.0f;
        double six = 6.0;
        boolean seven = true;
        char eight = 'A';
        String pattern = "Primitive variables : \r\n"
                            + "byte - {}, short - {}, int - {},  long - {}\r\n"
                            + "float - {}, double - {}\r\n"
                            + "boolean - {}, char - {}";
        LOG.debug(pattern, one, two, three, four, five, six, seven, eight);
        LOG.error(pattern, one, two, three, four, five, six, seven, eight);
    }
}
