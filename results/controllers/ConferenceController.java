    
    package controllers;


    import domain.Conference;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.ConferenceService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/conference")
    public class ConferenceController extends AbstractController {

        @Autowired
        private ConferenceService    conferenceService;


        public ConferenceController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Conference conference) {
            ModelAndView result;

            result = ConferenceController.createEditModelAndView(conference, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Conference conference, final String message) {
            ModelAndView result;

            result = new ModelAndView("conference/edit");
            result.addObject("conference", conference);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Conference> conferences;
                conferences = this.conferenceService.findAll();
                result = new ModelAndView("conference/list");
                result.addObject("conference",conferences);
                result.addObject("requestURI", "conference/list.do");
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/create", method = RequestMethod.GET)
        public ModelAndView create() {

            ModelAndView result;
            try{
                final Conference conference = this.conferenceService.create();
                result = ConferenceController.createEditModelAndView(conference);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int conferenceId) {
            ModelAndView result;
            Conference conference;
            try{
                conference = this.conferenceService.findOne(conferenceId);
                Assert.notNull(conference);
                result = ConferenceController.createEditModelAndView(conference);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Conference conference, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = ConferenceController.createEditModelAndView(conference);
            else
                try {
                    this.conferenceService.save(conference);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = ConferenceController.createEditModelAndView(conference, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Conference conference) {
            ModelAndView result;
            try {
                this.conferenceService.delete(conference);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    