package flight;
import flight.reservation.plane.Helicopter;
import flight.reservation.plane.PassengerDrone;
import flight.reservation.plane.PassengerPlane;
import flight.reservation.plane.Plane;

public class PlaneFactory {
    public Plane createPlane(String type, String model) {
        if (type.equals("PassengerPlane")) {
            return new PassengerPlane(model);
        } else if (type.equals("Helicopter")) {
            return new Helicopter(model);
        } else if (type.equals("PassengerDrone")) {
            return new PassengerDrone(model);
        } else {
            throw new IllegalArgumentException(String.format("Model type '%s' is not recognized", model));
    }
    }
}

