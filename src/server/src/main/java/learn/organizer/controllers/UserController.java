package learn.organizer.controllers;


import learn.organizer.domain.UserService;
import learn.organizer.domain.exceptions.AccountCreationException;
import learn.organizer.models.AppUser;
import learn.organizer.models.Contact;
import learn.organizer.security.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/authenticate")
public class UserController {
    // The `AuthenticationManager` interface defines a single method `authenticate()`
    // that processes an Authentication request.
    private final AuthenticationManager authenticationManager;
    private final JWTUtil converter;
    private final UserService authService;

    public UserController(AuthenticationManager authenticationManager, JWTUtil converter, UserService authService) {
        this.authenticationManager = authenticationManager;
        this.converter = converter;
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> credentials) {
        // The `UsernamePasswordAuthenticationToken` class is an `Authentication` implementation
        // that is designed for simple presentation of a username and password.
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password"));

        try {
            Authentication authentication = authenticationManager.authenticate(authToken);

            if (authentication.isAuthenticated()) {
                String jwtToken = converter.buildJwt((AppUser) authentication.getPrincipal());

                HashMap<String, String> map = new HashMap<>();
                map.put("jwt_token", jwtToken);
                //add contact into map here
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
        } catch (AuthenticationException ex) {
            System.out.println(ex);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createAccount( @RequestBody List<String> data) {
        AppUser appUser=null;
        try {
            String username = data.get(0);
            String password = data.get(1);
            System.out.println(username);
            BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
            password=bCryptPasswordEncoder.encode(password);
            Contact contact= new Contact(-1,data.get(2),data.get(3),data.get(4),data.get(5));
            appUser = new AppUser(username,password);
            appUser.setContact(contact);
            appUser = authService.createAccount(appUser);
            //add contact here as well. parse data create new contact. upload with user, maybe transactional
        } catch (DuplicateKeyException ex) {
            return new ResponseEntity<>(List.of("The provided username already exists"), HttpStatus.BAD_REQUEST);
        } catch (AccountCreationException e) {
            return new ResponseEntity<>(List.of("The provided username already exists"), HttpStatus.BAD_REQUEST);
        }

        HashMap<String, Integer> map = new HashMap<>();
        map.put("appUserId", appUser.getAppUserId());

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
