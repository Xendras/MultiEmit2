
package wad.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.Emit;
import wad.domain.EmitPunch;
import wad.service.CompetitorService;
import wad.service.EmitService;

@Controller
public class EmitController {
    
    @Autowired
    private CompetitorService competitorService;
    
    @Autowired
    private EmitService emitService;
    
    @ModelAttribute("inputEmit")
    private Emit getEmit() {
        return new Emit();
    }
    
    @ModelAttribute("emitPunch")
    private EmitPunch getEmitPunch() {
        return new EmitPunch();
    }

    @RequestMapping(value = "/emits", method = RequestMethod.GET)
    public String viewEmits(Model model) {
        model.addAttribute("emits", emitService.getEmits());
        return "emits";
    }

    @RequestMapping(value = "/emits", method = RequestMethod.POST)
    public String addEmit(@Valid @ModelAttribute("inputEmit") Emit inputEmit, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            viewEmits(model);
            return "emits";
        }
        String number = inputEmit.getNumber();
        for(Emit otherEmit : emitService.getEmits()){
            if(otherEmit.getNumber().equals(number)){
                return "redirect:/emits";
            }
        }
        emitService.saveEmit(inputEmit);
        return "redirect:/emits";
    }
    
    @RequestMapping(value = "/emits/{id}", method = RequestMethod.GET)
    public String viewEmit(@PathVariable Long id, Model model) {
        Emit emit = emitService.getEmit(id);
        model.addAttribute("emit", emit);
        return "emit";
    }
    
    @RequestMapping(value = "/emits/{id}", method = RequestMethod.DELETE)
    public String deleteEmit(@PathVariable Long id) {
        emitService.deleteEmit(id);
        return "redirect:/emits";
    }
}
