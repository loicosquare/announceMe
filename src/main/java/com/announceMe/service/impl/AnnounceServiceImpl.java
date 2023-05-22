package com.announceMe.service.impl;

import com.announceMe.entity.Announce;
import com.announceMe.entity.HttpResponse;
import com.announceMe.repository.AnnounceRepository;
import com.announceMe.service.AnnounceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class AnnounceServiceImpl implements AnnounceService {

    private final AnnounceRepository announceRepository;

    @Override
    public HttpResponse<Announce> addNewAnnounce(Announce announce) {
        return null;
    }

    @Override
    public HttpResponse<Announce> updateAnnounce(Announce announce) {
        return null;
    }

    @Override
    public HttpResponse<Announce> getAnnounceById(int id) {
        return null;
    }

    @Override
    public void deleteAnnounce(Long id) {

    }

    @Override
    public List<HttpResponse<Announce>> getAllAnnounces() {
        return null;
    }

    @Override
    public HttpResponse<Announce> validateAnnounce(Announce announce) {
        return null;
    }
}
