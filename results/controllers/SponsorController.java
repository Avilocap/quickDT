    
    package controllers;


    import domain.Sponsor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.SponsorService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/sponsor")
    public class SponsorController extends AbstractController {

        @Autowired
        private SponsorService    sponsorService;


        public SponsorController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Sponsor sponsor) {
            ModelAndView result;

            result = SponsorController.createEditModelAndView(sponsor, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Sponsor sponsor, final String message) {
            ModelAndView result;

            result = new ModelAndView("sponsor/edit");
            result.addObject("sponsor", sponsor);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Sponsor> sponsors;
                sponsors = this.sponsorService.findAll();
                result = new ModelAndView("sponsor/list");
                result.addObject("sponsor",sponsors);
                result.addObject("requestURI", "sponsor/list.do");
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
                final Sponsor sponsor = this.sponsorService.create();
                result = SponsorController.createEditModelAndView(sponsor);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int sponsorId) {
            ModelAndView result;
            Sponsor sponsor;
            try{
                sponsor = this.sponsorService.findOne(sponsorId);
                Assert.notNull(sponsor);
                result = SponsorController.createEditModelAndView(sponsor);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Sponsor sponsor, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = SponsorController.createEditModelAndView(sponsor);
            else
                try {
                    this.sponsorService.save(sponsor);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = SponsorController.createEditModelAndView(sponsor, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Sponsor sponsor) {
            ModelAndView result;
            try {
                this.sponsorService.delete(sponsor);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    