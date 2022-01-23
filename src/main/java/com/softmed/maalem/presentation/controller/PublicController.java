package com.softmed.maalem.presentation.controller;

import com.softmed.maalem.security.CurrentUser;
import com.softmed.maalem.security.UserPrincipal;
import com.softmed.maalem.service.ProfileServiceFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private ProfileServiceFace profileService;

    @GetMapping(value = "/image-profile/{type}",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getProfileImage(@PathVariable String type, @RequestParam("img") String img) throws IOException {
        //System.err.println(type);
        return profileService.getProfileImage(img,type);
    }

    @GetMapping("/test")
    public String getServerStatus(){
        return "server: it's ok bro.";
    }

}
