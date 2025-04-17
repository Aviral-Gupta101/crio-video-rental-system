package site.aviralgupta.video_rental_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideoDto {

    private Integer videoId;

    @NotBlank
    private String title;

    @NotBlank
    private String director;

    @NotBlank
    private String genre;

    @NotNull
    private boolean available;
}
