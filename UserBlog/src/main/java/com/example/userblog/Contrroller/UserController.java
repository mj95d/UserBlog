package com.example.userblog.Contrroller;

import com.example.userblog.Model.User;
import com.example.userblog.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    private final UserService userService;
    @GetMapping("/getAll")
    public ResponseEntity getAll(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(this.userService.getAll(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody User user){
        this.userService.add(user);
        return ResponseEntity.status(200).body("user added");
    }

    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal User userId, @Valid @RequestBody User user){
        this.userService.update(userId.getId(), user);
        return ResponseEntity.status(200).body("user updated");
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@AuthenticationPrincipal User userId){
        this.userService.delete(userId);
        return ResponseEntity.status(200).body("user deleted");
    }


}
