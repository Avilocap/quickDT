    
    package controllers;


    import domain.CreditCard;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.CreditCardService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/creditcard")
    public class CreditCardController extends AbstractController {

        @Autowired
        private CreditCardService    creditcardService;


        public CreditCardController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final CreditCard creditcard) {
            ModelAndView result;

            result = CreditCardController.createEditModelAndView(creditcard, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final CreditCard creditcard, final String message) {
            ModelAndView result;

            result = new ModelAndView("creditcard/edit");
            result.addObject("creditcard", creditcard);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<CreditCard> creditcards;
                creditcards = this.creditcardService.findAll();
                result = new ModelAndView("creditcard/list");
                result.addObject("creditcard",creditcards);
                result.addObject("requestURI", "creditcard/list.do");
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
                final CreditCard creditcard = this.creditcardService.create();
                result = CreditCardController.createEditModelAndView(creditcard);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int creditcardId) {
            ModelAndView result;
            CreditCard creditcard;
            try{
                creditcard = this.creditcardService.findOne(creditcardId);
                Assert.notNull(creditcard);
                result = CreditCardController.createEditModelAndView(creditcard);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final CreditCard creditcard, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = CreditCardController.createEditModelAndView(creditcard);
            else
                try {
                    this.creditcardService.save(creditcard);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = CreditCardController.createEditModelAndView(creditcard, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final CreditCard creditcard) {
            ModelAndView result;
            try {
                this.creditcardService.delete(creditcard);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    