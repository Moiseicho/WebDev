package tinder.controller;

import java.util.List;

import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tinder.entities.Location;
import tinder.service.LocationService;

@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/saveUserLocation")
    public String saveLocation(@RequestParam Integer user_id, @RequestParam String lat,
                               @RequestParam String longitude) throws ParseException {
        Location Location = locationService.saveUserLocation(user_id, lat, longitude);

        System.out.println("User location details saved successfully with id::" + Location.getUser_id());

        return "User location details saved successfully.";
    }

    @GetMapping("/findNearestLocation")
    public List<Object[]> findNearestLocation(Integer userId) {
        return locationService.findNearestlocatio(userId);
    }
}