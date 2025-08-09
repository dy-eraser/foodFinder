package free.task.foodfinder.repository;

import free.task.foodfinder.entity.PlaceDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceDetailRepository extends JpaRepository<PlaceDetail, Long> {
}
