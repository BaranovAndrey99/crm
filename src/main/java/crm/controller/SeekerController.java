package crm.controller;

import crm.dao.Employee;
import crm.dao.Interview;
import crm.dao.InterviewStatus;
import crm.dao.Seeker;
import crm.repository.InterviewRepository;
import crm.repository.SeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Controller
public class SeekerController {
    @Autowired
    SeekerRepository seekerRepository;

    @Autowired
    InterviewRepository interviewRepository;

    @GetMapping("/seeker")
    public String seekerPage(){

        return "seeker";
    }
    /*
     * Handling of form for writing list of all seekers.
     */
    @RequestMapping(params = "listseeker", method = RequestMethod.POST)
    public void listSeeker(@ModelAttribute("listseekerform") Seeker seeker, Map<String, Object> model){
        Iterable<Seeker> allSeekersFromDb = seekerRepository.findAll();
        if(((List<Seeker>) allSeekersFromDb).isEmpty()){
            model.put("listnoresult", "Соискатели не найдены");
        } else {
            model.put("listresult", allSeekersFromDb);
        }
    }

    /*
     * Handling of form for addition of new seeker.
     */
    @RequestMapping(params = "addseeker", method = RequestMethod.POST)
    public void addSeeker(@ModelAttribute("addseekerform") Seeker seeker, Map<String, Object> model){
        Seeker seekerFromDb = seekerRepository.findSeekerByMail(seeker.getMail());
        if(seekerFromDb != null){
            model.put("adderror", "Такой соискатель уже существует!");
        } else {
            seekerRepository.save(seeker);
        }
    }

    /*
     * Handling of form for search of seeker.
     */
    @RequestMapping(params = "searchseeker", method = RequestMethod.POST)
    public void searchSeeker(@ModelAttribute("searchseekerform") Seeker seeker, @RequestParam String surname, @RequestParam String firstname, @RequestParam String fathername, Map<String, Object> model){
        Iterable<Seeker> seekerFromDb = seekerRepository.findSeekerBySurnameAndFirstnameAndFathername(surname, firstname, fathername);
        if(((List<Seeker>) seekerFromDb).isEmpty()){
            model.put("searchnoresult", "Соискатели не найдены");
        } else {
            model.put("searchresult", seekerFromDb);
        }
    }

    /*
     * Handling of form for deleting of seeker.
     */
    @RequestMapping(params = "deleteseeker", method = RequestMethod.POST)
    public void deleteSeeker(@ModelAttribute("deleteseekerform") Seeker seeker, Map<String, Object> model){
        Seeker seekerFromDb = seekerRepository.findSeekerById(seeker.getId());
        if(seekerFromDb == null){
            model.put("deleteerror", "Соискатель не существует!");
        } else {
            seekerRepository.delete(seeker);
        }
    }

    /*
     * Hadling of form for invitation to interview
     */
    @RequestMapping(params = "setinterview", method = RequestMethod.POST)
    public void setInterview(@ModelAttribute("setinterviewform") Seeker seeker, Map<String, Object> model){
        Seeker seekerFromDb = seekerRepository.findSeekerById(seeker.getId());
        if(seekerFromDb == null){
            model.put("deleteerror", "Соискатель не существует!");
        } else {
            Interview interview = new Interview();
            interview.setInterviewseekerid(seeker.getId());
            interview.setInterviewdate("Какая-то дата");
            interview.setInterviewStatuses(Collections.singleton(InterviewStatus.WILL_BE));
            interviewRepository.save(interview);
        }
    }
}
