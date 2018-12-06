package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static org.launchcode.models.JobData.findByColumnAndValue;


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
    @RequestMapping(value = "results", method = RequestMethod.POST)
    public String search(@RequestParam String searchType, @RequestParam String searchTerm, Model model) {
        if (searchType.equals("all")) {
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("results", JobData.findByValue(searchTerm));
            return "search";

        }
        model.addAttribute("results", JobData.findByColumnAndValue(searchType, searchTerm));
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }
}
