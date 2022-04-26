package com.example.micrigramm.util;

import com.example.micrigramm.entity.Like;
import com.example.micrigramm.entity.Publication;
import com.example.micrigramm.entity.User;
import com.example.micrigramm.repository.LikeRepository;
import com.example.micrigramm.repository.PublicationRepository;
import com.example.micrigramm.repository.SubscriptionRepository;
import com.example.micrigramm.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

public class InitDatabase {
    private static final Random r = new Random();
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final SubscriptionRepository subscriptionRepository;

    public InitDatabase(UserRepository userRepository, PasswordEncoder encoder, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Bean
    CommandLineRunner init(UserRepository userRepo, PublicationRepository publicationRepo, LikeRepository likeRepository) {
        return (args) -> {
            likeRepository.deleteAll();
            userRepo.deleteAll();
            publicationRepo.deleteAll();

            List<Publication> publications = readPublications("users.json");


//            List<User> users = Stream.generate(User::random)
//                    .limit(10)
//                    .collect(toList());
//            userRepo.saveAll(users);

            publicationRepo.saveAll(publications);

            List<Like> likes = new ArrayList<>();

//            users.forEach(user -> {
//                selectRandomMovies(publications, r.nextInt(3)+1).stream()
//                        .map(movie -> Like.random(user, publications))
//                        .peek(likes::add)
//                        .forEach(likeRepository::save);
//            });

            likes.stream()
                    .collect(groupingBy(Like::getPublication, averagingDouble(Like::getId)))
                    .forEach(Publication::setDateAdded);
        };
    }

    private List<Publication> selectRandomPublications(List<Publication> publications, int amountOfPublications) {
        return Stream.generate(() -> pickRandom(publications))
                .distinct()
                .limit(amountOfPublications)
                .collect(toList());
    }

    private static Publication pickRandom(List<Publication> publications) {
        return publications.get(r.nextInt(publications.size()));
    }

    private static List<Publication> readPublications(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            var data = Files.readString(Paths.get(fileName));
            var listType = new TypeReference<List<Publication>>() {
            };
            return mapper.readValue(data, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Bean
    public CommandLineRunner fillData() {
        return (args) -> {
            subscriptionRepository.deleteAll();
            userRepository.deleteAll();

//            userRepository.save(User.builder().name("Make the shelter").eventDate(LocalDateTime.now().minusDays(1)).build());
//            userRepository.save(User.builder().name("Repair the shelter").description("Make it safe and cozy.").eventDate(LocalDateTime.now().plusDays(1)).build());
//            userRepository.save(User.builder().name("Gather some firewood").description("To keep the fireplace running").eventDate(LocalDateTime.now().plusDays(1)).build());
//            userRepository.save(User.builder().name("Winter is coming!").description("Survive the winter!").eventDate(LocalDateTime.now().plusDays(1)).build());


            userRepository.deleteAll();

            User user1 = new User("email", encoder.encode("test"));
            user1.setEmail("test@test");
            user1.setPassword(encoder.encode("test"));
            userRepository.save(user1);

            User user2 = new User("email", encoder.encode("quest"));
            user2.setEmail("guest@test");
            user2.setPassword(encoder.encode("guest"));
            userRepository.save(user2);

            User user3 = new User("email", encoder.encode("admin"));
            user3.setEmail("admin@test");
            user3.setPassword(encoder.encode("admin"));
            userRepository.save(user3);
        };
    }
}
