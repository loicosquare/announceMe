package com.announceMe.service;

import com.announceMe.entity.Announce;
import com.announceMe.entity.Category;
import com.announceMe.entity.HttpResponse;

import java.util.List;

public interface AnnounceService {
    HttpResponse<Announce> addNewAnnounce(Announce announce, Category category) throws Exception;
    HttpResponse<Announce> updateAnnounce(Announce announce) throws Exception;
    HttpResponse<Announce> getAnnounceById(int id) throws Exception;
    HttpResponse<Announce> deleteAnnounce(int id) throws Exception;
    HttpResponse<Announce> getAllAnnounces();
    //List<Announce> getAnnouncesByCategory(String category);
    HttpResponse<Announce> validateAnnounce(Announce announce) throws Exception;
}
