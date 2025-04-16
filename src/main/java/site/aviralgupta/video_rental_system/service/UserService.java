package site.aviralgupta.video_rental_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.aviralgupta.video_rental_system.entity.Video;
import site.aviralgupta.video_rental_system.repository.VideoRepository;

import java.util.List;

@Service
public class UserService {

    private final VideoRepository videoRepository;

    @Autowired
    public UserService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<Video> getAllVideos(){

        return videoRepository.findAll();
    }
}
