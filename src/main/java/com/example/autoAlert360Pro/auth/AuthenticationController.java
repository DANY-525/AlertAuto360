package com.example.autoAlert360Pro.auth;
import com.example.autoAlert360Pro.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")

@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    RegistrationService registrationService;
    private final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ){
        boolean validatePassword =registrationService.validatePassword(request.getPassword());
        boolean validateEmail = registrationService.ValidateEmail(request.getEmail());
        boolean validateAlreadyExistOnDatabase = registrationService.validateEmailExist(request.getEmail());
        if(!validateEmail){
            return new ResponseEntity<>("WrongEmail", HttpStatus.NOT_ACCEPTABLE);
        }else if (!validatePassword){
            return new ResponseEntity<>("Wrong password: password contains at least 10 characters, one lowercase letter, one\n" +
                    "uppercase letter and one of the following characters: !, @, #, ? or ].",HttpStatus.NOT_ACCEPTABLE);
        } else if (!validateAlreadyExistOnDatabase) {
           return new ResponseEntity<>("Email already exist on Database",HttpStatus.NOT_ACCEPTABLE);
        } else {
            return  ResponseEntity.ok(service.register(request));
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> register(
            @RequestBody AuthenticationRequest request
    ){
        boolean validatePassword =registrationService.validatePassword(request.getPassword());
        boolean validateEmail = registrationService.ValidateEmail(request.getEmail());
        if(!validateEmail){
            return new ResponseEntity<>("WrongEmail", HttpStatus.NOT_ACCEPTABLE);
        }else if (!validatePassword){
            return new ResponseEntity<>("Wrong password: password contains at least 10 characters, one lowercase letter, one\\n\" +\n" +
                    "                    \"uppercase letter and one of the following characters: !, @, #, ? or ].",HttpStatus.NOT_ACCEPTABLE);
        }  else {
            return  ResponseEntity.ok(service.authenticate(request));
        }

    }

}
