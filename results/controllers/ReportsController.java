    
    package controllers;


    import domain.Reports;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.ReportsService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/reports")
    public class ReportsController extends AbstractController {

        @Autowired
        private ReportsService    reportsService;


        public ReportsController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Reports reports) {
            ModelAndView result;

            result = ReportsController.createEditModelAndView(reports, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Reports reports, final String message) {
            ModelAndView result;

            result = new ModelAndView("reports/edit");
            result.addObject("reports", reports);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Reports> reportss;
                reportss = this.reportsService.findAll();
                result = new ModelAndView("reports/list");
                result.addObject("reports",reportss);
                result.addObject("requestURI", "reports/list.do");
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
                final Reports reports = this.reportsService.create();
                result = ReportsController.createEditModelAndView(reports);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int reportsId) {
            ModelAndView result;
            Reports reports;
            try{
                reports = this.reportsService.findOne(reportsId);
                Assert.notNull(reports);
                result = ReportsController.createEditModelAndView(reports);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Reports reports, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = ReportsController.createEditModelAndView(reports);
            else
                try {
                    this.reportsService.save(reports);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = ReportsController.createEditModelAndView(reports, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Reports reports) {
            ModelAndView result;
            try {
                this.reportsService.delete(reports);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    