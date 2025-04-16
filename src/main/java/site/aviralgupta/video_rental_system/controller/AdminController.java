package site.aviralgupta.video_rental_system.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.aviralgupta.video_rental_system.dto.VideoDto;
import site.aviralgupta.video_rental_system.service.AdminService;
import site.aviralgupta.video_rental_system.service.UserService;

import java.util.Map;


@RestController
@RequestMapping("/admin/video")
public class AdminController {

    AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createVideo(@Valid @RequestBody VideoDto dto){
        return ResponseEntity.ok(adminService.createVideo(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable Integer id){
        adminService.deleteVideById(id);

        return ResponseEntity.ok(Map.of("Message", "Video deleted"));
    }
}
