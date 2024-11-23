package com.learnify.backend.common;

import com.learnify.backend.masterservice.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    @Autowired
    protected MasterService masterService;


}
