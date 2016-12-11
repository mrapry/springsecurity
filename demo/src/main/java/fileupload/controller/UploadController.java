package fileupload.controller;

import org.apache.tika.Tika;
import org.apache.tika.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Created by mrapry on 12/6/16.
 */

@RestController
public class UploadController {

    private final List<String> allowedFiles = Arrays.asList(
            "application/pdf",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            "application/vnd.ms-excel",
            "application/msword",
            "image/jpeg",
            "image/png",
            "image/x-citrix-png",
            "image/x-png",
            "image/x-citrix-jpeg",
            "application/x-rar-compressed",
            "application/zip"
    );

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> upload(@RequestParam(value = "foto", required = true) MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        String hasil="";
        Map<String, Object> result = new HashMap();
        String extensi = multipartFile.getContentType();

        if (multipartFile.isEmpty()){
            System.out.println("File tidak ada");
        } else {
            byte[] data = multipartFile.getBytes();
            Tika t = new Tika();
            hasil = t.detect(data);
            if (allowedFiles.contains(hasil) && hasil.equals(extensi)) {
                result.put("status", "1");
                result.put("msg", "File is allowed");
            } else {
                result.put("status", "0");
                result.put("msg", "File not supported");
            }
        }
        return result;
    }
}
