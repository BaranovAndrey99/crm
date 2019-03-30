package crm.controller;


import crm.dao.Interview;
import crm.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class InterviewController {
    @Autowired
    InterviewRepository interviewRepository;

    @GetMapping("/interview")
    public String interviewPage(){
        return "interview";
    }

    /*
     * Handling of form for writing list of all seekers.
     */
    @RequestMapping(params = "listinterview", method = RequestMethod.POST)
    public void listInterview(@ModelAttribute("listinterviewform") Interview interview, Map<String, Object> model){
        Iterable<Interview> allInterviewsFromDb = interviewRepository.findAll();
        if(((List<Interview>) allInterviewsFromDb).isEmpty()){
            model.put("listnoresult", "Собеседования не найдены");
        } else {
            model.put("listresult", allInterviewsFromDb);
        }
    }
}
