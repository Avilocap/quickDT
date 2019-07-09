    
    package controllers;


    import domain.Message;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.MessageService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/message")
    public class MessageController extends AbstractController {

        @Autowired
        private MessageService    messageService;


        public MessageController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Message message) {
            ModelAndView result;

            result = MessageController.createEditModelAndView(message, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Message message, final String message) {
            ModelAndView result;

            result = new ModelAndView("message/edit");
            result.addObject("message", message);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Message> messages;
                messages = this.messageService.findAll();
                result = new ModelAndView("message/list");
                result.addObject("message",messages);
                result.addObject("requestURI", "message/list.do");
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
                final Message message = this.messageService.create();
                result = MessageController.createEditModelAndView(message);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int messageId) {
            ModelAndView result;
            Message message;
            try{
                message = this.messageService.findOne(messageId);
                Assert.notNull(message);
                result = MessageController.createEditModelAndView(message);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Message message, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = MessageController.createEditModelAndView(message);
            else
                try {
                    this.messageService.save(message);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = MessageController.createEditModelAndView(message, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Message message) {
            ModelAndView result;
            try {
                this.messageService.delete(message);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    