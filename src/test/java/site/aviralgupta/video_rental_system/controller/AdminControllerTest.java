package site.aviralgupta.video_rental_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import site.aviralgupta.video_rental_system.component.JwtUtil;
import site.aviralgupta.video_rental_system.dto.VideoDto;
import site.aviralgupta.video_rental_system.exceptions.exceptions.EntityNotFoundException;
import site.aviralgupta.video_rental_system.service.AdminService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(AdminController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {


    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private AdminService adminService;

    @MockitoBean
    JwtUtil jwtUtil; // Need this even if we don't use it here

    @Test
    public void AdminController_CreateVideo_ReturnsVideoDto() throws Exception {

        VideoDto videoDto = VideoDto.builder()
                .videoId(1)
                .title("Title")
                .available(true)
                .genre("Thriller")
                .director("Aviral")
                .build();

        when(adminService.createVideo(Mockito.any(VideoDto.class))).thenReturn(videoDto);

        ResultActions response = mockMvc.perform(
                post("/admin/video/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(videoDto))
        );

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.videoId", CoreMatchers.is(videoDto.getVideoId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(videoDto.getTitle())));

        verify(adminService).createVideo(Mockito.any(VideoDto.class));

    }


    @Test
    public void AdminController_DeleteVideo_ReturnsOkStatusIfVideoIdExists() throws Exception {

        doNothing().when(adminService).deleteVideById(Mockito.anyInt());

        ResultActions response = mockMvc.perform(
                delete("/admin/video/delete/1" )
        );

        response.andExpect(MockMvcResultMatchers.status().isOk());

        verify(adminService).deleteVideById(anyInt());
    }

    @Test
    public void AdminController_DeleteVideo_ReturnsNotFoundIfVideoNotExists() throws Exception {


        doThrow(new EntityNotFoundException("Invalid video ID")).when(adminService).deleteVideById(anyInt());

        ResultActions response = mockMvc.perform(
                delete("/admin/video/delete/1" )
        );

        response.andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(adminService).deleteVideById(anyInt());
    }
}
