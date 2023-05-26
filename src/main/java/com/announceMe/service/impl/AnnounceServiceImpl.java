package com.announceMe.service.impl;

import com.announceMe.entity.*;
import com.announceMe.repository.AnnounceRepository;
import com.announceMe.repository.CategoryRepository;
import com.announceMe.service.AnnounceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class AnnounceServiceImpl implements AnnounceService {

    private final AnnounceRepository announceRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public HttpResponse<Announce> addNewAnnounce(Announce announce, Category category) throws Exception {
        // Check if the category exists
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        if (optionalCategory.isEmpty()) {
            throw new Exception("The category does not exist");
        }

        // Check if an announce with the same title already exists
        if (announceRepository.findByTitle(announce.getTitle()).isPresent()) {
            throw new Exception("An Announce with this title already exists");
        }

        // Get the authenticated user
        Role userRole = null;
        User user = null;
        try {
            user = getAuthenticatedUser();
            userRole = user.getRole();
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
            // Gérez l'exception selon vos besoins
        }

        // Check if the user has the necessary permissions
        if (userRole == Role.ADMIN || userRole == Role.MANAGER) {
            // Set the connected user for the announce
            announce.setUser(user);
            announce.setCreatedAt(new Date());

            // Set the status of the announce based on the user's role
            announce.setIsValid(userRole == Role.ADMIN);

            Announce savedAnnounce = announceRepository.save(announce);
            return HttpResponse.<Announce>builder()
                    .data(Collections.singleton(savedAnnounce))
                    .message("Announce added successfully")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } else {
            throw new Exception("You are not authorized to add an announce");
        }
    }

    @Override
    public HttpResponse<Announce> updateAnnounce(Announce announce) throws Exception {
        Optional<Announce> optionalAnnounce = announceRepository.findById(announce.getId());
        User user = null;
        try {
            user = getAuthenticatedUser();
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }

        if (optionalAnnounce.isPresent()) {
            Announce updatedAnnounce = optionalAnnounce.get();
            updatedAnnounce.setUser(user);
            updatedAnnounce.setDescription(announce.getDescription());
            updatedAnnounce.setTitle(announce.getTitle());
            updatedAnnounce.setIsValid(announce.getIsValid());
            updatedAnnounce.setCategory(announce.getCategory());
            updatedAnnounce.setUpdatedAt(new Date());
            updatedAnnounce.setImage(announce.getImage());
            announceRepository.save(updatedAnnounce);
            return HttpResponse.<Announce>builder()
                    .data(Collections.singleton(updatedAnnounce))
                    .message("Announce updated successfully")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    //.timeStamp()
                    .build();
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    @Override
    public HttpResponse<Announce> getAnnounceById(int id) throws Exception {
        Announce announce = announceRepository.findAll()
                .stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("The announce with "+ id + " does not exist"));
        announceRepository.delete(announce);
        return HttpResponse.<Announce>builder()
                .data(Collections.singleton(announce))
                .message("Announce found successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                //.timeStamp(LocalDateTime.now().format(dateTimeFormatter()))
                .build();
    }

    @Override
    public HttpResponse<Announce> deleteAnnounce(int id) throws Exception {
        Announce announce = announceRepository.findAll()
                .stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("The announce with id " + id + " does not exist"));
        announceRepository.delete(announce);
        return HttpResponse.<Announce>builder()
                .data(Collections.singleton(announce))
                .message("Announce deleted successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                //.timeStamp(LocalDateTime.now().format(dateTimeFormatter()))
                .build();
    }

    @Override
    public HttpResponse<Announce> getAllAnnounces() {
        return HttpResponse.<Announce>builder()
                .data(announceRepository.findAll()
                        .stream()
                        .sorted(Comparator.comparing(Announce::getCreatedAt))
                        .collect(Collectors.toList())
                )
                .message("Announces found successfully")
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public HttpResponse<Announce> validateAnnounce(Announce announce) throws Exception {
        User user = null;
        try {
            user = getAuthenticatedUser();
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
        if(isAdmin(user.getRole().name())) {
            announce.setIsValid(true);
            return HttpResponse.<Announce>builder()
                  .data(Collections.singleton(announce))
                  .message("Announce validated successfully")
                  .status(HttpStatus.OK)
                  .statusCode(HttpStatus.OK.value())
                  .build();
        }else{
            throw new Exception("You are not authorized to validate an announce");
        }
    }

    public Boolean isAdmin(String role){
        return role.equals("ADMIN");
    }

    private User getAuthenticatedUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            throw new Exception("The user is not an authenticated user");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return (User) userDetails;
    }
}
