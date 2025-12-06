package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping("/simple")
    public ResponseEntity<List<UserDto>> getSimpleUsers() {
        final List<UserDto> userDtos = userService.findAllUsers().stream().map(userMapper::toSimpleDto).toList();
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        final List<UserDto> userDtos = userService.findAllUsers().stream().map(userMapper::toDto).toList();
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {

        Optional<UserDto> user = userService.getUser(userId).map(userMapper::toDto);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email")
    public ResponseEntity<List<UserDto>> getUserByEmail(@RequestParam("email") String email) {

        Optional<UserDto> user = userService.getUserByEmail(email).map(userMapper::toDto);
        return user.map(List::of).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/older/{time}")
    public ResponseEntity<List<UserDto>> getUsersOlderBy(@PathVariable("time") LocalDate time) {

        List<UserDto> users = userService.findAllUsers().stream()
                .map(userMapper::toDto)
                .filter(userDto -> time.isAfter(userDto.birthdate()))
                .toList();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) {
        User user = userService.createUser(userMapper.toEntity(userDto));
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto userDto) {

        userService.updateUser(userId, userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}