package com.andikadeveloper.bankingspring.user.controller;

import com.andikadeveloper.bankingspring.user.model.dto.request.UpdateUserRequest;
import com.andikadeveloper.bankingspring.user.model.dto.response.UserResponse;
import com.andikadeveloper.bankingspring.user.model.entity.UserEntity;
import com.andikadeveloper.bankingspring.user.service.contract.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class UserController extends BaseController {
    private final UserService userService;

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponse> getCurrentUser() {
        UserEntity currentUser = getAuthenticatedUser();

        UserResponse response = new UserResponse();
        response.setId(currentUser.getId());
        response.setFullName(currentUser.getFullName());
        response.setEmail(currentUser.getEmail());

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponse> updateCurrentUser(@RequestBody UpdateUserRequest request) {
        UserResponse response = userService.updateUserById(getAuthenticatedUser().getId(), request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> response = userService.getAllUsers();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
        UserResponse response = userService.getUserById(id);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable("id") Long id, @RequestBody UpdateUserRequest request) {
        UserResponse response = userService.updateUserById(id, request);

        return ResponseEntity.ok(response);
    }
}
