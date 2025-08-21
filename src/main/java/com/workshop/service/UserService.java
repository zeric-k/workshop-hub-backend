package com.workshop.service;

import com.workshop.dto.RegisterRequest;
import com.workshop.model.Space;
import com.workshop.model.User;
import com.workshop.model.UserProfile;
import com.workshop.repository.SpaceRepository;
import com.workshop.repository.UserProfileRepository;
import com.workshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(RegisterRequest request) {
        // 1️⃣ Hash password
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 2️⃣ Save user first
        User user = new User(
                request.getUsername(),
                encodedPassword,
                request.getRole()
        );
        user = userRepository.save(user); // persist to get user.id

        // 3️⃣ If SPACE_OWNER → insert into Space table
        if ("SPACE_OWNER".equalsIgnoreCase(request.getRole())) {
            Space space = new Space();
            space.setSpace(request.getSpaceName());
            space.setLocation(request.getLocation());
            space.setOwner(user); // link via FK

            spaceRepository.save(space);
        }

        // 4️⃣ If REGULAR_USER → insert into UserProfile table
        else if ("REGULAR_USER".equalsIgnoreCase(request.getRole())) {
            UserProfile profile = new UserProfile();
            profile.setFirstName(request.getFirstName());
            profile.setLastName(request.getLastName());
            profile.setEmail(request.getEmail());
            profile.setPhoneNo(request.getPhoneNo());
            profile.setUser(user); // link via FK

            userProfileRepository.save(profile);
        }

        return user;
    }
}
