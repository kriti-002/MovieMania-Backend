package com.demo.MovieMania.Service;

import com.demo.MovieMania.Model.Domain.User;
import com.demo.MovieMania.Model.Request.UserLoginRequest;
import com.demo.MovieMania.Model.Request.UserRequest;
import com.demo.MovieMania.Model.Response.UserLoginResponse;
import com.demo.MovieMania.Model.Response.UserResponse;
import com.demo.MovieMania.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String PASSWORD_REGEX =
            "^(?=.*[0-9])" +          // At least one digit
                    "(?=.*[a-z])" +           // At least one lowercase letter
                    "(?=.*[A-Z])" +           // At least one uppercase letter
                    "(?=.*[@#$%^&+=!])" +     // At least one special character from the set
                    "(?=\\S+$)" +             // No whitespace allowed
                    ".{8,20}$";               // Length between 8 and 20 characters
    private static final Pattern MOBILE_PATTERN= Pattern.compile("^[6-9]\\d{9}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    public String deleteUser(Long id) {
        boolean flag= userRepository.findById(id).isPresent();
        if(!flag) return "User with the following ID doesn't exists";
        User u= userRepository.findById(id).get();
        userRepository.delete(u);
        return "User with the given ID is deleted.";
    }

    public UserResponse getUser(Long id) {
        boolean flag= userRepository.findById(id).isPresent();
        if(!flag) return UserResponse.builder().message("User with the following ID doesn't exists").build();
        User u= userRepository.findById(id).get();
        return u.toResponse("Got the User");
    }

    public UserResponse updateUserDetails(Long id, UserRequest ur) {
        boolean flag= userRepository.findById(id).isPresent();
        if(!flag) return UserResponse.builder().message("User with the following ID doesn't exists").build();

        User update= userRepository.findById(id).get();

        String mobile= ur.getMobile(), email= ur.getEmail(), password= ur.getPassword();

        if(!emailCheck(email)) return UserResponse.builder().message("Please enter a valid email address").build();
        if(!passwordCheck(password)) return UserResponse.builder().message("Please follow the guidelines to write password.").build();
        if(!userRepository.existsByPassword(passwordEncoder.encode(password))) return UserResponse.builder().message("Password Already Exists, enter a new password").build();
        if(!mobileCheck(mobile)) return UserResponse.builder().message("Please enter a valid mobile number").build();

        User data= ur.toRequest();

        if(data.getAge() != null) update.setAge(data.getAge());
        if(data.getName() != null) update.setName(data.getName());
        if(data.getMobile() != null) update.setMobile(data.getMobile());
        if(data.getRole() != null) update.setRole(data.getRole());
        if(data.getEmail() != null) update.setEmail(data.getEmail());
        if(data.getPassword() != null ) update.setPassword(data.getPassword());
        return update.toResponse("Updated the details");
    }


    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        User authenticatedUser = authenticationService.authenticate(userLoginRequest);
        //System.out.println(authenticatedUser.getId() + " " + authenticatedUser.getName());

        HashMap<String, String> map= new HashMap<>();
        map.put("Id", authenticatedUser.getId().toString());
        map.put("Email", authenticatedUser.getEmail());
        map.put("Role", authenticatedUser.getRole().toString());

        String jwtToken = jwtService.generateToken(map, authenticatedUser);

        return UserLoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .message("I got the response.")
                .build();
    }

    public UserResponse addUser(UserRequest userRequest) {
        User registeredUser = authenticationService.signup(userRequest);
        if(registeredUser != null) return registeredUser.toResponse("Signed Up");
        return UserResponse.builder().message("Please check the guidelines before creating the account.").build();
    }

    public UserResponse authenticateUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return currentUser.toResponse("I got my details.");
    }
    public boolean emailCheck(String email){
        if(email == null) return false;
        Matcher matcher1 = EMAIL_PATTERN.matcher(email);
        return matcher1.matches() && !userRepository.existsByEmail(email);

    }
    public boolean passwordCheck(String password){
        if(password == null) return false;
        Matcher matcher2 = PASSWORD_PATTERN.matcher(password);
        return matcher2.matches();

    }
    public boolean mobileCheck(String mobile){
        if(mobile == null) return false;
        Matcher matcher3= MOBILE_PATTERN.matcher(mobile);
        return matcher3.matches() && !userRepository.existsByMobile(mobile);
    }
}
