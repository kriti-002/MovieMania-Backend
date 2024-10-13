package com.demo.MovieMania.Service;

import com.demo.MovieMania.Model.Domain.User;
import com.demo.MovieMania.Model.Request.UserLoginRequest;
import com.demo.MovieMania.Model.Request.UserRequest;
import com.demo.MovieMania.Model.Response.UserResponse;
import com.demo.MovieMania.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String PASSWORD_REGEX =
            "^(?=.*[0-9])" +          // At least one digit
                    "(?=.*[a-z])" +           // At least one lowercase letter
                    "(?=.*[A-Z])" +           // At least one uppercase letter
                    "(?=.*[@#$%^&+=!])" +     // At least one special character from the set
                    "(?=\\S+$)" +             // No whitespace allowed
                    ".{8,20}$";               // Length between 8 and 20 characters
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    public UserResponse addUser(UserRequest u) {
        String mobile= u.getMobile(), email= u.getEmail(), password= u.getPassword();
        if(mobile.length() != 10){
            return UserResponse.builder().message("Please enter valid mobile number").build();
        }
        if(userRepository.existsByMobile(mobile)){
            return UserResponse.builder().message("This mobile number already exists, try with a different number").build();
        }
        Matcher matcher1 = EMAIL_PATTERN.matcher(email);
        if(!matcher1.matches() || userRepository.existsByEmail(email)) return UserResponse.builder().message("Please enter a valid email address").build();

        Matcher matcher2= PASSWORD_PATTERN.matcher(password);
        if(!matcher2.matches()) {
            return UserResponse.builder().message("Please follow this guideline for the perfect password. " +
                    "\n 1. At least one digit should be present." +
                    "\n 2. At least one lowercase letter should be present." +
                    "\n 3. At least one uppercase letter should be present." +
                    "\n 4. At least one special character from [@#$%^&+=!] should be present." +
                    "\n 5. No whitespace is allowed" +
                    "\n 6. Length should be between 8 and 20 characters").build();
        }
        if(userRepository.existsByPassword(password)){
            return UserResponse.builder().message("This password already exists, try again.").build();
        }
        User user= u.toRequest();
        userRepository.save(user);
        return user.toResponse("Created the user.");
    }

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
        if( mobile != null && mobile.length() != 10){
            return UserResponse.builder().message("Please enter valid mobile number").build();
        }
        if(mobile != null && userRepository.existsByMobile(mobile)){
            return UserResponse.builder().message("This mobile number already exists, try with a different number").build();
        }
        if(email != null ){
            Matcher matcher1 = EMAIL_PATTERN.matcher(email);
            if(!matcher1.matches() || userRepository.existsByEmail(email)) return UserResponse.builder().message("Please enter a valid email address").build();
        }
        if(password != null){
            Matcher matcher2= PASSWORD_PATTERN.matcher(password);
            if(!matcher2.matches()) {
                return UserResponse.builder().message("Please follow this guideline for the perfect password. " +
                        "\n 1. At least one digit should be present." +
                        "\n 2. At least one lowercase letter should be present." +
                        "\n 3. At least one uppercase letter should be present." +
                        "\n 4. At least one special character from [@#$%^&+=!] should be present." +
                        "\n 5. No whitespace is allowed" +
                        "\n 6. Length should be between 8 and 20 characters").build();
            }
            if(userRepository.existsByPassword(password)){
                return UserResponse.builder().message("This password already exists, try again.").build();
            }
        }

        User data= ur.toRequest();

        if(data.getAge() != null) update.setAge(data.getAge());
        if(data.getName() != null) update.setName(data.getName());
        if(data.getMobile() != null) update.setMobile(data.getMobile());
        if(data.getRole() != null) update.setRole(data.getRole());
        if(data.getEmail() != null) update.setEmail(data.getEmail());
        if(data.getPassword() != null ) update.setPassword(data.getPassword());
        return update.toResponse("Updated the details");
    }


}
