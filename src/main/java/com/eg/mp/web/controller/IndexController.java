package com.eg.mp.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author liangjiange
 * @since 2017-02-07 20:46
 */
@Controller
public class IndexController {


    @RequestMapping(value = {"", "/", "index", "main"})
    public String index(HttpServletRequest request, ModelMap modelMap) {


        return "index";
    }

}
