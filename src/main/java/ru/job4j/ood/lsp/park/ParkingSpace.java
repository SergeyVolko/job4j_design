package ru.job4j.ood.lsp.park;

import java.util.*;

public class ParkingSpace implements StoreCar<Car> {

    private int passSpace;
    private int trackSpace;
    private final Set<Car> passengers = new HashSet<>();
    private final Set<Car> tracks = new HashSet<>();

    public ParkingSpace(int passSpace, int trackSpace) {
        this.passSpace = passSpace;
        this.trackSpace = trackSpace;
    }

    @Override
    public boolean park(Car car) {
        boolean result = true;
        int carSize = car.getSize();
        if (carSize == 1 && passSpace > 0) {
            passengers.add(car);
            passSpace -= carSize;
        } else if (carSize > 1) {
            if (trackSpace > 0) {
                tracks.add(car);
                trackSpace--;
            } else if (carSize <= passSpace) {
                passengers.add(car);
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
    public boolean toLeave(Car car) {
        boolean result = true;
        int carSize = car.getSize();
        if (passengers.remove(car)) {
            passSpace += carSize;
        } else if (tracks.remove(car)) {
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

    public Set<Car> getPassengers() {
        return new HashSet<>(passengers);
    }

    public Set<Car> getTracks() {
        return new HashSet<>(tracks);
    }
}
