package tinder.service;

import java.util.List;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tinder.entities.Location;
import tinder.repository.LocationRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository LocationRepo;

    public Location saveUserLocation(Integer user_id, String lat, String longitude) throws ParseException{
        WKTReader wktReader = new WKTReader();
        Geometry geometry = wktReader.read("POINT (+" + lat + " " + longitude + ")");

        Location userLocation = new Location();
        userLocation.setPoint(geometry);
        userLocation.setUser_id(user_id);

        LocationRepo.save(userLocation);
        return userLocation;
    }

    public List<Object[]> findNearestlocatio(Integer userId) {
        Geometry geometry = LocationRepo.findCoordinateByUserId(userId);

        WKTWriter reader = new WKTWriter();
        String point = reader.write(geometry);

        List<Object[]> locationsDetails = LocationRepo.findAlltheLocation(point, userId);

        return locationsDetails;
    }
}