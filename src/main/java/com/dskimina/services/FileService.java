package com.dskimina.services;

import com.dskimina.data.File;
import com.dskimina.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    private UIDGeneratorService uidGeneratorService;

    @Autowired
    private FileRepository fileRepository;

    public File createFile(String name, byte[] content){
        File file = new File();
        file.setContent(content);
        file.setName(name);
        file.setSize(content.length);
        file.setIdentifier(uidGeneratorService.generateCode());
        return fileRepository.save(file);
    }

}
