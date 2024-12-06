package com.sadi.hackathonbase.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.sadi.hackathonbase.exceptions.OtpMismatchException;
import com.sadi.hackathonbase.exceptions.OtpTimedOutException;
import com.sadi.hackathonbase.exceptions.UserAlreadyExistsException;
import com.sadi.hackathonbase.models.User;
import com.sadi.hackathonbase.models.dtos.UnverifiedUser;
import com.sadi.hackathonbase.models.requests.RegistrationRequest;
import com.sadi.hackathonbase.repository.UserRepository;
import com.sadi.hackathonbase.repository.UserVerificationRepository;
import com.sadi.hackathonbase.utils.CodeGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRegistrationAndVerificationService {
    @Value("${verification.email.message}")
    private String verificationEmailMessage;

    @Value("${verification.email.subject}")
    private String verificationEmailSubject;

    @Value("${verification.email.timeout}")
    private int optExpiration;

    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final UserVerificationRepository userVerRepo;

    public UserRegistrationAndVerificationService(PasswordEncoder passwordEncoder,
                                                  EmailService emailService,
                                                  UserRepository userRepository,
                                                  UserVerificationRepository userVerRepo) {
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.userVerRepo = userVerRepo;
    }
    public void checkIfUserExists(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException(String.format("User with email=%s already exists", username));
        }

    }
    public void cacheDetails(RegistrationRequest regReq, String otp) throws JsonProcessingException {
        UnverifiedUser unverifiedUser = new UnverifiedUser(new User(regReq.getUsername(),
                passwordEncoder.encode(regReq.getPassword()),
                regReq.getFullName(), regReq.getDob(), regReq.getGender()), otp);

        userVerRepo.putUserVerificationInfo(regReq.getUsername(), unverifiedUser);
    }

    public void sendVerificationEmail(String username, String otp) {
        emailService.sendMimeEmail(username, verificationEmailSubject,
                String.format(verificationEmailMessage, otp, (optExpiration / 60)));
    }

    public String getOtp(){
        return CodeGenerator.generateOtp();
    }

    public User verifyUser(String username, String otp) throws JsonProcessingException {
        Optional<UnverifiedUser> unverifiedUser = userVerRepo.getUserVerificationInfoByUsername(username);

        if (unverifiedUser.isEmpty()) {
            throw new OtpTimedOutException("Your opt has timed out");
        }

        if(!unverifiedUser.get().getOtp().equals(otp)){
            throw new OtpMismatchException("Your OTP doesn't match. Please try again");
        }
        return unverifiedUser.get().getUser();
    }

    public void saveUser(User user) {
        userVerRepo.deleteUserVerInfoByUsername(user.getUsername());
        userRepository.save(user);
    }
}