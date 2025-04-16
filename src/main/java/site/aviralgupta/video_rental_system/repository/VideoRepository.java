package site.aviralgupta.video_rental_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.aviralgupta.video_rental_system.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
}
