package flight.reservation.builder_for_plane;

import flight.reservation.plane.PassengerPlane;

public class BuilderEmbraer190 implements PlaneInterface {

    PassengerPlane embraer190Plane = new PassengerPlane();

    public BuilderEmbraer190() {
        embraer190Plane.model = "Embraer 190";
    }

    @Override
    public void buildPassengerCapacity() {
        embraer190Plane.passengerCapacity = 100;
    }

    @Override
    public void buildCrewCapacity() {
        embraer190Plane.setCrewCapacity(4);
    }

    public PassengerPlane getPlane() {
        return embraer190Plane;
    }
}
