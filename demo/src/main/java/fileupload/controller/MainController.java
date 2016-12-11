package fileupload.controller;


import org.springframework.web.bind.annotation.*;


/**
 * Created by mrapry on 12/5/16.
 */

@RestController
public class MainController {

    @RequestMapping(value = "")
    public String upload(){
        return "index";
    }



}
