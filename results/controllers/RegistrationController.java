    
    package controllers;


    import domain.Registration;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.RegistrationService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/registration")
    public class RegistrationController extends AbstractController {

        @Autowired
        private RegistrationService    registrationService;


        public RegistrationController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Registration registration) {
            ModelAndView result;

            result = RegistrationController.createEditModelAndView(registration, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Registration registration, final String message) {
            ModelAndView result;

            result = new ModelAndView("registration/edit");
            result.addObject("registration", registration);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Registration> registrations;
                registrations = this.registrationService.findAll();
                result = new ModelAndView("registration/list");
                result.addObject("registration",registrations);
                result.addObject("requestURI", "registration/list.do");
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
                final Registration registration = this.registrationService.create();
                result = RegistrationController.createEditModelAndView(registration);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int registrationId) {
            ModelAndView result;
            Registration registration;
            try{
                registration = this.registrationService.findOne(registrationId);
                Assert.notNull(registration);
                result = RegistrationController.createEditModelAndView(registration);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Registration registration, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = RegistrationController.createEditModelAndView(registration);
            else
                try {
                    this.registrationService.save(registration);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = RegistrationController.createEditModelAndView(registration, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Registration registration) {
            ModelAndView result;
            try {
                this.registrationService.delete(registration);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    