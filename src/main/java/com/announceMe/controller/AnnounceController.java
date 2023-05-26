package com.announceMe.controller;

import com.announceMe.entity.Announce;
import com.announceMe.entity.HttpResponse;
import com.announceMe.service.AnnounceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/announce")
public class AnnounceController {
    private final AnnounceService announceService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('management:create', 'admin:create') or hasRole('ADMIN')")
    public ResponseEntity<HttpResponse<Announce>> addNewAnnounce(@RequestBody Announce announce, @RequestParam Long idCategory) {
        try {
            HttpResponse<Announce> response = announceService.addNewAnnounce(announce, idCategory);
            return new ResponseEntity<>(response, response.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //@PutMapping("/{id}")
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('admin:update') or hasRole('ADMIN')")
    public ResponseEntity<HttpResponse<Announce>> updateAnnounce(/*@PathVariable("id") int id, */@RequestBody Announce announce) {
        try {
            //announce.setId(id);
            HttpResponse<Announce> response = announceService.updateAnnounce(announce);
            return new ResponseEntity<>(response, response.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse<Announce>> getAnnounceById(@PathVariable("id") int id) {
        try {
            HttpResponse<Announce> response = announceService.getAnnounceById(id);
            return new ResponseEntity<>(response, response.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('admin:delete') or hasRole('ADMIN')")
    public ResponseEntity<HttpResponse<Announce>> deleteAnnounce(@PathVariable("id") int id) {
        try {
            HttpResponse<Announce> response = announceService.deleteAnnounce(id);
            return new ResponseEntity<>(response, response.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<HttpResponse<Announce>> getAllAnnounces() {
        HttpResponse<Announce> response = announceService.getAllAnnounces();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/{id}/validate")
    @PreAuthorize("hasAnyAuthority('admin:update') or hasRole('ADMIN')")
    public ResponseEntity<HttpResponse<Announce>> validateAnnounce(@PathVariable("id") int id, @RequestBody Announce announce) {
        try {
            //announce.setId(id);
            HttpResponse<Announce> response = announceService.validateAnnounce(announce);
            return new ResponseEntity<>(response, response.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}