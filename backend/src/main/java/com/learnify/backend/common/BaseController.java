package com.learnify.backend.common;

import com.learnify.backend.masterservice.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    protected MasterService masterService;

    @Autowired
    public BaseController (MasterService masterService) {
        this.masterService = masterService;
    }
}
