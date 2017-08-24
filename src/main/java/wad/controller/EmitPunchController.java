
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
import wad.service.EmitPunchService;
import wad.service.EmitService;

@Controller
@RequestMapping("/emits/{emitId}")
public class EmitPunchController {
    
    @Autowired
    private CompetitorService competitorService;
    
    @Autowired
    private EmitService emitService;
    
    @Autowired
    private EmitPunchService emitPunchService;
    
    @ModelAttribute("emitPunch")
    private EmitPunch getEmitPunch() {
        return new EmitPunch();
    }

    @RequestMapping(value = "/punches", method = RequestMethod.GET)
    public String viewEmitPunches(Model model) {
        model.addAttribute("emitPunches", emitPunchService.getEmitPunches());
        return "emits";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String addEmitPunch(@PathVariable Long emitId, @Valid @ModelAttribute EmitPunch emitPunch, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("emit",emitService.getEmit(emitId));
            return "emit";
        }
        emitPunchService.addEmitPunchToEmit(emitPunch, emitId);
        return "redirect:/emits/{emitId}";
    }
    
    @RequestMapping(value = "/punches/{punchId}", method = RequestMethod.DELETE)
    public String deleteEmitPunch(@PathVariable Long emitId, @PathVariable Long punchId) {
        emitPunchService.deleteEmitPunch(punchId);
        return "redirect:/emits/{emitId}";
    }
    
}
