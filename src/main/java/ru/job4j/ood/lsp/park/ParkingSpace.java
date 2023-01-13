package ru.job4j.ood.lsp.park;

import java.util.*;

public class ParkingSpace implements Parking {

    private int passSpace;
    private int trackSpace;
    private final Set<Machine> passengers = new HashSet<>();
    private final Set<Machine> tracks = new HashSet<>();

    public ParkingSpace(int passSpace, int trackSpace) {
        this.passSpace = passSpace;
        this.trackSpace = trackSpace;
    }

    @Override
    public boolean park(Machine machine) {
        int carSize = machine.getSize();
        if (carSize == Car.SIZE && passSpace > 0) {
            passengers.add(machine);
            passSpace -= carSize;
            return true;
        }
        if (carSize > Car.SIZE && trackSpace > 0) {
            tracks.add(machine);
            trackSpace--;
            return true;
        }
        if (carSize > Car.SIZE && passSpace >= carSize) {
            passengers.add(machine);
            passSpace -= carSize;
            return true;
        }
        return false;
    }

    @Override
    public boolean leave(Machine machine) {
        int carSize = machine.getSize();
        if (passengers.remove(machine)) {
            passSpace += carSize;
            return true;
        }
        if (tracks.remove(machine)) {
            trackSpace++;
            return true;
        }
        return false;
    }

    public int getPassSpace() {
        return passSpace;
    }

    public int getTrackSpace() {
        return trackSpace;
    }
}
