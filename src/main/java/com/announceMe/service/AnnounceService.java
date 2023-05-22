package com.announceMe.service;

import com.announceMe.entity.Announce;
import com.announceMe.entity.HttpResponse;

import java.util.List;

public interface AnnounceService {
    HttpResponse<Announce> addNewAnnounce(Announce announce);
    HttpResponse<Announce> updateAnnounce(Announce announce);
    HttpResponse<Announce> getAnnounceById(int id);
    void deleteAnnounce(Long id);
    List<HttpResponse<Announce>> getAllAnnounces();
    //List<Announce> getAnnouncesByCategory(String category);
    HttpResponse<Announce> validateAnnounce(Announce announce);
}
