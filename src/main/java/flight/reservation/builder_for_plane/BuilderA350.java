package flight.reservation.builder_for_plane;

import flight.reservation.plane.PassengerPlane;

public class BuilderA350 implements PlaneInterface {

    PassengerPlane a350Plane = new PassengerPlane();

    public BuilderA350() {
        a350Plane.model = "A350";
    }

    @Override
    public void buildPassengerCapacity() {
        a350Plane.passengerCapacity = 320;
    }

    @Override
    public void buildCrewCapacity() {
        a350Plane.setCrewCapacity(40);
    }

    public PassengerPlane getPlane() {
        return a350Plane;
    }
    
}