package beans.controllers;

import beans.models.Event;
import beans.models.User;
import beans.services.EventService;
import beans.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class UploadController {
    private static final ObjectMapper mapper = new ObjectMapper();

    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public UploadController(@Value("#{eventServiceImpl}") EventService eventService, @Value("#{userServiceImpl}") UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String showUploadPage() {
        return "uploader";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ModelAndView uploadFiles(@RequestParam(required = false) MultipartFile fileUsers,
                                    @RequestParam(required = false) MultipartFile fileEvents) throws IOException {

        ModelAndView mav = new ModelAndView("upload-result");
        if(fileUsers.isEmpty() && fileEvents.isEmpty()) {
            return mav.addObject("result", "Please add at least one file for");
        } else {
            if(!fileUsers.isEmpty()) {
                parseUsersJSON(fileUsers).forEach(userService::register);
            }
            if(!fileEvents.isEmpty()) {
                parseEventsJSON(fileEvents).forEach(eventService::create);
            }
        }
        return mav.addObject("result", "Success");
    }

    private List<User> parseUsersJSON(MultipartFile file) throws IOException {
        return mapper.readValue(file.getInputStream(), new TypeReference<List<User>>(){});
    }

    private List<Event> parseEventsJSON(MultipartFile file) throws IOException {
        return mapper.readValue(file.getInputStream(), new TypeReference<List<Event>>(){});
    }
}
