    
    package controllers;


    import domain.Actor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.ActorService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/actor")
    public class ActorController extends AbstractController {

        @Autowired
        private ActorService    actorService;


        public ActorController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Actor actor) {
            ModelAndView result;

            result = ActorController.createEditModelAndView(actor, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Actor actor, final String message) {
            ModelAndView result;

            result = new ModelAndView("actor/edit");
            result.addObject("actor", actor);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Actor> actors;
                actors = this.actorService.findAll();
                result = new ModelAndView("actor/list");
                result.addObject("actor",actors);
                result.addObject("requestURI", "actor/list.do");
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
                final Actor actor = this.actorService.create();
                result = ActorController.createEditModelAndView(actor);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int actorId) {
            ModelAndView result;
            Actor actor;
            try{
                actor = this.actorService.findOne(actorId);
                Assert.notNull(actor);
                result = ActorController.createEditModelAndView(actor);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Actor actor, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = ActorController.createEditModelAndView(actor);
            else
                try {
                    this.actorService.save(actor);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = ActorController.createEditModelAndView(actor, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Actor actor) {
            ModelAndView result;
            try {
                this.actorService.delete(actor);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    