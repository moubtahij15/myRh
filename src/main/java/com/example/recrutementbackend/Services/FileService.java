package com.example.recrutementbackend.Services;

import com.example.recrutementbackend.Entities.File;
import com.example.recrutementbackend.Repositories.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {


    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    public File saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence "
                        + fileName);
            }
            System.out.println("from serviccce" + file.getBytes());
            File file1
                    = new File(fileName,
                    file.getContentType(),
                    file.getBytes());
            return fileRepository.save(file1);

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName + e);
        }
    }

    public File getAttachment(String fileId) throws Exception {
        System.out.println(fileId);
        return fileRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }
}
