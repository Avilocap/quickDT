    
    package controllers;


    import domain.Paper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.PaperService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/paper")
    public class PaperController extends AbstractController {

        @Autowired
        private PaperService    paperService;


        public PaperController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Paper paper) {
            ModelAndView result;

            result = PaperController.createEditModelAndView(paper, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Paper paper, final String message) {
            ModelAndView result;

            result = new ModelAndView("paper/edit");
            result.addObject("paper", paper);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Paper> papers;
                papers = this.paperService.findAll();
                result = new ModelAndView("paper/list");
                result.addObject("paper",papers);
                result.addObject("requestURI", "paper/list.do");
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
                final Paper paper = this.paperService.create();
                result = PaperController.createEditModelAndView(paper);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int paperId) {
            ModelAndView result;
            Paper paper;
            try{
                paper = this.paperService.findOne(paperId);
                Assert.notNull(paper);
                result = PaperController.createEditModelAndView(paper);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Paper paper, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = PaperController.createEditModelAndView(paper);
            else
                try {
                    this.paperService.save(paper);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = PaperController.createEditModelAndView(paper, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Paper paper) {
            ModelAndView result;
            try {
                this.paperService.delete(paper);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    