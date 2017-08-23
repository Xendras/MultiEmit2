
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
import wad.domain.Competitor;
import wad.domain.Emit;
import wad.service.CompetitorService;
import wad.service.EmitService;

@Controller
public class EmitController {
    
    @Autowired
    private CompetitorService competitorService;
    
    @Autowired
    private EmitService emitService;
    
    @ModelAttribute
    private Emit getEmit() {
        return new Emit();
    }

    @RequestMapping(value = "/emits", method = RequestMethod.GET)
    public String viewEmits(Model model) {
        model.addAttribute("emits", emitService.getEmits());
        return "emits";
    }
    
    @Transactional
    @RequestMapping(value = "/emits", method = RequestMethod.POST)
    public String addEmit(@Valid @ModelAttribute("emit") Emit emit, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            viewEmits(model);
            return "emits";
        }
        emitService.saveEmit(emit);
        return "redirect:/emits";
    }
    
    @RequestMapping(value = "/emits/{id}", method = RequestMethod.GET)
    public String viewEmit(@PathVariable Long id, Model model) {
        Emit emit = emitService.getEmit(id);
        model.addAttribute("emit", emit);
        model.addAttribute("emitPunches", emit.getEmitPunches());
        return "competitor";
    }
    
    @RequestMapping(value = "/emits/{id}", method = RequestMethod.DELETE)
    public String deleteEmit(@PathVariable Long id) {
        emitService.deleteEmit(id);
        return "redirect:/emits";
    }
}
