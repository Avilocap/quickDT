    
    package controllers;


    import domain.Sponsorship;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.SponsorshipService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/sponsorship")
    public class SponsorshipController extends AbstractController {

        @Autowired
        private SponsorshipService    sponsorshipService;


        public SponsorshipController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Sponsorship sponsorship) {
            ModelAndView result;

            result = SponsorshipController.createEditModelAndView(sponsorship, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Sponsorship sponsorship, final String message) {
            ModelAndView result;

            result = new ModelAndView("sponsorship/edit");
            result.addObject("sponsorship", sponsorship);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Sponsorship> sponsorships;
                sponsorships = this.sponsorshipService.findAll();
                result = new ModelAndView("sponsorship/list");
                result.addObject("sponsorship",sponsorships);
                result.addObject("requestURI", "sponsorship/list.do");
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
                final Sponsorship sponsorship = this.sponsorshipService.create();
                result = SponsorshipController.createEditModelAndView(sponsorship);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int sponsorshipId) {
            ModelAndView result;
            Sponsorship sponsorship;
            try{
                sponsorship = this.sponsorshipService.findOne(sponsorshipId);
                Assert.notNull(sponsorship);
                result = SponsorshipController.createEditModelAndView(sponsorship);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Sponsorship sponsorship, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = SponsorshipController.createEditModelAndView(sponsorship);
            else
                try {
                    this.sponsorshipService.save(sponsorship);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = SponsorshipController.createEditModelAndView(sponsorship, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Sponsorship sponsorship) {
            ModelAndView result;
            try {
                this.sponsorshipService.delete(sponsorship);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    