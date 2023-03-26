package com.LocalisFood.LocalisFood.Controller;

import com.LocalisFood.LocalisFood.Model.User;
import com.LocalisFood.LocalisFood.Service.AuthService;
import com.LocalisFood.LocalisFood.dto.UserDto;
import com.LocalisFood.LocalisFood.dto.UserResponseDto;
import io.swagger.annotations.*;
import com.LocalisFood.LocalisFood.dto.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
@Api(tags = "auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/login")
    @ApiOperation(value = "${AuthController.login}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public AuthenticationResponse  login(//
                        @ApiParam("username") @RequestParam("username") String username, //
                        @ApiParam("password") @RequestParam("password") String password) {
        String Token = userService.login(username, password);
        return AuthenticationResponse.builder().Token(Token).build();
    }

    @PostMapping("/signup")
    @ApiOperation(value = "${AuthController.signup}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use")})
    public String signup(@RequestBody UserDto userdto) {

        return userService.signup(modelMapper.map(userdto, User.class));
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${AuthController.delete}", authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String delete(@ApiParam("Username") @PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${AuthController.search}", response = UserResponseDto.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserResponseDto search(@ApiParam("Username") @PathVariable String username) {
        return modelMapper.map(userService.search(username), UserResponseDto.class);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${AuthController.me}", response = UserResponseDto.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserResponseDto whoami(HttpServletRequest req) {
        return modelMapper.map(userService.whoami(req), UserResponseDto.class);
    }

    @GetMapping("/refresh")

    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }

}


























