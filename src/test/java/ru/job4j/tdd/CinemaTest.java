package ru.job4j.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.List;

@Disabled
public class CinemaTest {

    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions).isNull();
    }

    @Test
    public void whenInvalidPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> cinema.buy(account, -1, 1, date));
    }

    @Test
    public void whenAddSession() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        assertThat(cinema.find(s -> s.equals(session)))
                .contains(session)
                .hasSize(1);
    }

    @Test
    public void whenOccupiedPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        cinema.buy(account, 1, 1, date);
        assertThrows(IllegalArgumentException.class, () -> cinema.buy(account, 1, 1, date));
    }

    @Test
    public void whenInvalidCalendar() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        assertThrows(IllegalArgumentException.class, () -> cinema.buy(account, 1, 1, null));
    }
}