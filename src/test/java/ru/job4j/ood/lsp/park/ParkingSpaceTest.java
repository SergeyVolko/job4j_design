package ru.job4j.ood.lsp.park;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class ParkingSpaceTest {

    @Test
    public void whenAddOneTruckAndTwoPassengerIsNotPassenger() {
        ParkingSpace parking = new ParkingSpace(2, 1);
        Machine machine1 = new Car("Toyota");
        Machine machine2 = new Truck("Kamaz", 2);
        Machine machine3 = new Car("Lada");
        parking.park(machine1);
        parking.park(machine2);
        parking.park(machine3);
        assertThat(parking.getPassSpace()).isEqualTo(0);
    }

    @Test
    public void whenAddOneTruckAndTwoPassengerIsNotTruck() {
        ParkingSpace parking = new ParkingSpace(2, 1);
        Machine machine1 = new Car("Toyota");
        Machine machine2 = new Truck("Kamaz", 2);
        Machine machine3 = new Car("Lada");
        parking.park(machine1);
        parking.park(machine2);
        parking.park(machine3);
        assertThat(parking.getTrackSpace()).isEqualTo(0);
    }

    @Test
    public void whenAddOneTruckAndTwoPassengerIsNotOneTruck() {
        ParkingSpace parking = new ParkingSpace(2, 1);
        Machine machine1 = new Car("Toyota");
        Machine machine2 = new Truck("Kamaz", 2);
        Machine machine3 = new Car("Lada");
        parking.park(machine1);
        parking.park(machine2);
        parking.park(machine3);
        parking.leave(new Truck("Kamaz", 2));
        assertThat(parking.getTrackSpace()).isEqualTo(1);
    }

    @Test
    public void whenAddOneTruckAndTwoPassengerAndLeavePassIsNotPass() {
        ParkingSpace parking = new ParkingSpace(2, 1);
        Machine machine1 = new Car("Toyota");
        Machine machine2 = new Truck("Kamaz", 2);
        Machine machine3 = new Car("Lada");
        parking.park(machine1);
        parking.park(machine2);
        parking.park(machine3);
        parking.leave(machine1);
        parking.leave(machine2);
        assertThat(parking.getPassSpace()).isEqualTo(1);
    }

    @Test
    public void whenAddOnePassengerAndTwoTruck() {
        ParkingSpace parking = new ParkingSpace(6, 0);
        Machine machine1 = new Car("Toyota");
        Machine machine2 = new Truck("Kamaz", 2);
        Machine machine3 = new Truck("Maz", 2);
        parking.park(machine1);
        parking.park(machine2);
        parking.park(machine3);
        assertThat(parking.getPassSpace()).isEqualTo(1);
    }

    @Test
    public void whenAddOnePassengerAndTwoTruckTAndLeaveTrack() {
        ParkingSpace parking = new ParkingSpace(6, 1);
        Machine machine1 = new Car("Toyota");
        Machine machine2 = new Truck("Kamaz", 2);
        Machine machine3 = new Truck("Maz", 2);
        parking.park(machine1);
        parking.park(machine2);
        parking.park(machine3);
        parking.leave(machine3);
        assertThat(parking.getPassSpace()).isEqualTo(5);
    }
}