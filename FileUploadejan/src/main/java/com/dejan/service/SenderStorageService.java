package com.dejan.service;

import com.dejan.model.Sender;
import com.dejan.repository.SenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class SenderStorageService {
    @Autowired
    private SenderRepository senderRepository;

    public Sender store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Sender sender = new Sender(fileName, file.getContentType(), file.getBytes());

        return senderRepository.save(sender);
    }

    public Sender getFile(String id) {
        return senderRepository.findById(id).get();
    }

    public Stream<Sender> getAllFiles() {
        return senderRepository.findAll().stream();
    }
}
