package tinder.repository;

import java.util.List;

import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tinder.entities.Location;

public interface LocationRepository extends CrudRepository<Location, Integer> {

    @Query(value = "select point from Location where user_id = :user_id")
    Geometry findCoordinateByUserId(Integer user_id);

    @Query(value = "select user_id, ST_Distance_Sphere(ST_GeomFromText(:geometry), coordinate) / 1000 as distance "
            + "from user_location "
            + "HAVING user_id != :user_id and distance < 200 "
            + "order by distance"
            + "limit 10", nativeQuery = true)
    List<Object[]> findAlltheLocation(@Param("geometry") String geometry, @Param("user_id") Integer user_id);

}