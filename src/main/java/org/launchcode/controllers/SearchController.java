package org.launchcode.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String results(Model model, HttpServletRequest request) {
        String type = request.getParameter("searchType");
        String term = request.getParameter("searchTerm");

        if (type.equals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(term);
            model.addAttribute("title", "All Jobs with: " + term);
            model.addAttribute("jobs", jobs);
            return "list-jobs";
        }
        else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(type, term);
            model.addAttribute("title", "Jobs with " + type + ": " + term);
            model.addAttribute("jobs", jobs);
            return "list-jobs";
        }
    }

}
