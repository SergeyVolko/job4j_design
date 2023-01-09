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
        if (carSize > passSpace && (carSize == Car.SIZE || trackSpace == 0)) {
            return false;
        }
        if (carSize == Car.SIZE || (carSize > Car.SIZE && trackSpace == 0)) {
            passengers.add(machine);
            passSpace -= carSize;
        } else {
            tracks.add(machine);
            trackSpace--;
        }
        return true;
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

    public Set<Machine> getPassengers() {
        return new HashSet<>(passengers);
    }

    public Set<Machine> getTracks() {
        return new HashSet<>(tracks);
    }
}
