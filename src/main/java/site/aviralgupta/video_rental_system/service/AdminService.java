package site.aviralgupta.video_rental_system.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.aviralgupta.video_rental_system.dto.VideoDto;
import site.aviralgupta.video_rental_system.entity.Video;
import site.aviralgupta.video_rental_system.repository.VideoRepository;

@Service
public class AdminService {

    private final VideoRepository videoRepository;
    private final ModelMapper mapper;

    @Autowired
    public AdminService(VideoRepository videoRepository, ModelMapper mapper) {
        this.videoRepository = videoRepository;
        this.mapper = mapper;
    }

    public VideoDto createVideo(VideoDto dto){

        Video newVideo = mapper.map(dto, Video.class);

        Video savedVideo = videoRepository.save(newVideo);

        return mapper.map(savedVideo, VideoDto.class);
    }

    public void deleteVideById(Integer id){
        videoRepository.deleteById(id);
    }

}
