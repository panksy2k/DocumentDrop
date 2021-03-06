package com.pardasani.digital.controller;

import com.pardasani.digital.business.DocumentManagementService;
import com.pardasani.digital.dto.APIOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.net.URL;

/**
 * Created by pankajpardasani on 12/03/2016.
 */
@Controller
@RequestMapping("/document")
public class DocumentManagementController {

    @Autowired
    private DocumentManagementService dms;

    @RequestMapping(value = "/registration/form", method = RequestMethod.GET)
    public String initDocumentUploadForm() {
        return "/dropContent";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String createNewDocument(@RequestParam("file") MultipartFile uploadedDocument, ModelAndView modelAndView) {
        APIOptions options = new APIOptions.APIOptionsBuilder().withMultipartFile(uploadedDocument)
                .withUserEmail("pankaj.d.p@gmail.com") //TODO: pass the user details from UI like ID in encrypted formet
                .build();

        URL documentRerefenceURL = dms.addNewDocument(options);
        modelAndView.addObject("uploadedDocument", documentRerefenceURL.toString());

        return "/dropContent";
    }
}
