package br.univille.projcolabassistant.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyErrorController implements ErrorController  {
 
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
    	Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    	HashMap<String, Object> data = new HashMap<String, Object>();
    	data.put("status", statusCode);
    	switch (statusCode) {
		    case 403:
		    	data.put("message", "Desculpe você não tem permissão!");
			
			break;
		}
        return new ModelAndView("error",data);
    }
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
}