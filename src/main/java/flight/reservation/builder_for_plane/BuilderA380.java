package flight.reservation.builder_for_plane;

import flight.reservation.plane.PassengerPlane;

public class BuilderA380 implements PlaneInterface {

    PassengerPlane a380Plane = new PassengerPlane();

    public BuilderA380() {
        a380Plane.model = "A380";
    }

    @Override
    public void buildPassengerCapacity() {
        a380Plane.passengerCapacity = 500;
    }

    @Override
    public void buildCrewCapacity() {
        a380Plane.setCrewCapacity(42);
    }
    
    public PassengerPlane getPlane() {
        return a380Plane;
    }
    
}
