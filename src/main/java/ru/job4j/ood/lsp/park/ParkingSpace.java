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
        boolean result = true;
        int carSize = machine.getSize();
        if (carSize == 1 && passSpace > 0) {
            passengers.add(machine);
            passSpace -= carSize;
        } else if (carSize > 1) {
            if (trackSpace > 0) {
                tracks.add(machine);
                trackSpace--;
            } else if (carSize <= passSpace) {
                passengers.add(machine);
                passSpace -= carSize;
            } else {
                result = false;
            }
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public boolean leave(Machine machine) {
        boolean result = true;
        int carSize = machine.getSize();
        if (passengers.remove(machine)) {
            passSpace += carSize;
        } else if (tracks.remove(machine)) {
            trackSpace++;
        } else {
            result = false;
        }
        return result;
    }

    public int getPassSpace() {
        return passSpace;
    }

    public int getTrackSpace() {
        return trackSpace;
    }

    public Set<Machine> getPassengers() {
        return new HashSet<>(passengers);
    }

    public Set<Machine> getTracks() {
        return new HashSet<>(tracks);
    }
}
