package ru.job4j.ood.lsp.park;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class ParkingSpaceTest {

    @Test
    public void whenAddOneTruckAndTwoPassengerIsNotPassenger() {
        ParkingSpace parking = new ParkingSpace(2, 1);
        Car car1 = new Passenger("Toyota");
        Car car2 = new Truck("Kamaz", 2);
        Car car3 = new Passenger("Lada");
        parking.park(car1);
        parking.park(car2);
        parking.park(car3);
        assertThat(parking.getPassSpace()).isEqualTo(0);
    }

    @Test
    public void whenAddOneTruckAndTwoPassengerIsNotTruck() {
        ParkingSpace parking = new ParkingSpace(2, 1);
        Car car1 = new Passenger("Toyota");
        Car car2 = new Truck("Kamaz", 2);
        Car car3 = new Passenger("Lada");
        parking.park(car1);
        parking.park(car2);
        parking.park(car3);
        assertThat(parking.getTrackSpace()).isEqualTo(0);
    }

    @Test
    public void whenAddOneTruckAndTwoPassengerIsNotOneTruck() {
        ParkingSpace parking = new ParkingSpace(2, 1);
        Car car1 = new Passenger("Toyota");
        Car car2 = new Truck("Kamaz", 2);
        Car car3 = new Passenger("Lada");
        parking.park(car1);
        parking.park(car2);
        parking.park(car3);
        parking.toLeave(new Truck("Kamaz", 2));
        assertThat(parking.getTrackSpace()).isEqualTo(1);
    }

    @Test
    public void whenAddOneTruckAndTwoPassengerAndLeavePassIsNotPass() {
        ParkingSpace parking = new ParkingSpace(2, 1);
        Car car1 = new Passenger("Toyota");
        Car car2 = new Truck("Kamaz", 2);
        Car car3 = new Passenger("Lada");
        parking.park(car1);
        parking.park(car2);
        parking.park(car3);
        parking.toLeave(car1);
        parking.toLeave(car3);
        assertThat(parking.getPassSpace()).isEqualTo(2);
    }

    @Test
    public void whenAddOnePassengerAndTwoTruck() {
        ParkingSpace parking = new ParkingSpace(6, 0);
        Car car1 = new Passenger("Toyota");
        Car car2 = new Truck("Kamaz", 2);
        Car car3 = new Truck("Maz", 2);
        parking.park(car1);
        parking.park(car2);
        parking.park(car3);
        assertThat(parking.getPassSpace()).isEqualTo(1);
    }

    @Test
    public void whenAddOnePassengerAndTwoTruckTAndLeaveTrack() {
        ParkingSpace parking = new ParkingSpace(6, 1);
        Car car1 = new Passenger("Toyota");
        Car car2 = new Truck("Kamaz", 2);
        Car car3 = new Truck("Maz", 2);
        parking.park(car1);
        parking.park(car2);
        parking.park(car3);
        parking.toLeave(car3);
        assertThat(parking.getPassSpace()).isEqualTo(5);
    }
}