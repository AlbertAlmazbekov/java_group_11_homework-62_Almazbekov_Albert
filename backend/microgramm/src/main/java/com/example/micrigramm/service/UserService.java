package com.example.micrigramm.service;

import com.example.micrigramm.dto.PublicationDTO;
import com.example.micrigramm.dto.UserProfileDTO;
import com.example.micrigramm.dto.UserRegistrationDTO;
import com.example.micrigramm.dto.UserDTO;
import com.example.micrigramm.entity.Publication;
import com.example.micrigramm.entity.User;
import com.example.micrigramm.repository.PublicationRepository;
import com.example.micrigramm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final PublicationService publicationService;
    private final UserRepository usersRepository;
    private final PublicationRepository publicationsRepository;
    private final PasswordEncoder encoder;

    public ResponseEntity<String> register(UserRegistrationDTO userRegistrationDTO) {
        if (usersRepository.existsByAccountName(userRegistrationDTO.getAccountName()) ||
                usersRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            return new ResponseEntity<>("Such a user already exists.", HttpStatus.CONFLICT);
        }
        usersRepository.save(User.builder()
                .name(userRegistrationDTO.getName())
                .accountName(userRegistrationDTO.getAccountName())
                .email(userRegistrationDTO.getEmail())
                .password(encoder.encode(userRegistrationDTO.getPassword()))
                .countPublications(0)
                .countSubscribers(0)
                .countSubscribes(0)
                .build()
        );
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<List<UserDTO>> search(String text, String type) {
        List<UserDTO> list = new ArrayList<>();
        Optional<User> optUser = Optional.empty();
        if (type.equals("email")) {
            optUser = usersRepository.findByEmail(text);
        } else if (type.equals("account")) {
            optUser = usersRepository.findByAccountName(text);
        } else if (type.equals("name")) {
            list.addAll(usersRepository.findByNameContainsIgnoringCase(text).stream()
                    .map(UserDTO::from)
                    .collect(Collectors.toList())
            );
        } else {
            return ResponseEntity.badRequest().build();
        }
        if (optUser.isPresent()) {
            list.add(UserDTO.from(optUser.get()));
        }
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    public ResponseEntity<UserProfileDTO> getUser(Long userId) {
        Optional<User> optUser = usersRepository.findById(userId);
        if (optUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Publication> publications = publicationsRepository.findAllByAuthor(optUser.get(), Pageable.unpaged()).stream().collect(Collectors.toList());
        List<PublicationDTO> publicationDTOList = publications.stream()
                .map(publicationService::createPublicationDto)
                .collect(Collectors.toList());
        var userDTO = UserProfileDTO.from(optUser.get(), publicationDTOList);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Not found"));
    }


}
