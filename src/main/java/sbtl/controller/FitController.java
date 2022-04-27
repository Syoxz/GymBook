package sbtl.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import sbtl.model.Tag;
import sbtl.model.Uebung;
import sbtl.repository.FitRepository;
import sbtl.repository.UebungRepository;

@Controller
public class FitController {

	@Autowired
	FitRepository fR;

	@Autowired
	UebungRepository uR;
	
    @GetMapping("/signup")
    public String showSignUpForm(Tag tag, Uebung uebung) {
        return "add-tag";
    }
    
  
    //Erscheint wenn man eine neue Uebung einem Tag hinzufuegen will
    @GetMapping("/showLabel/{id}")
    public String showLabelForm(@PathVariable("id") Long id, Uebung uebung, Model model) {
    	Tag tag = fR.findById(id)
        		.orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
    	model.addAttribute(tag);
        return "add-uebung";
    }
    
    @PostMapping("/addUebung/{id}")
    public String addUebung(Tag tag,Uebung uebung, BindingResult result) {
        if (result.hasErrors()) {
            return "add-uebung";
        }
        uR.save(uebung);  
        enrollTagToUebung(uebung.getId(), tag.getId());

        return "redirect:/edit/{id}";
    }
    
    @PostMapping("/addtag")
    public String addUebungUndTag(@Valid Uebung uebung, Tag tag, BindingResult result) {
        if (result.hasErrors()) {
            return "add-tag";
        }
       
        uR.save(uebung);
        fR.save(tag);
        enrollTagToUebung(uebung.getId(), tag.getId());
        return "redirect:/index";
    }
   
    //Muss man noch anpassen, bzw. / direkt auf index weiterleiten
    @GetMapping("/")
    public String showUebungenList1(Model model) {
        model.addAttribute("tage", fR.findAll());
        return "index";
    }
    @GetMapping("/index")
    public String showUebungenList(Model model) {
        model.addAttribute("tage", fR.findAll());
        return "index";
    }
    
    //Editiert einen ganzen Tag
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Tag tag = fR.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
        model.addAttribute("uebungen", uR.findAllByIstEnthalten(tag));
        model.addAttribute("tag", tag);
        return "update-tag";
    }
    
    //Editiert eine einzelne Uebung
    @GetMapping("/edit/uebung/{id}")
    public String editUebung(@PathVariable("id") Long id, Model model) {
        Uebung uebung  = uR.findById(id)
        		.orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
        model.addAttribute("uebung", uebung);
        return "update-uebung";
    }
    
    //Wird aktuell nicht benutzt
    @PostMapping("/update/{id}")
    public String updateTag(@PathVariable("id") Long id, @Valid Tag tag, Uebung uebung,
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            tag.setId(id);
            return "update-tag";
        }
            
        fR.save(tag);
        return "redirect:/index";
    }
    
    //Updated eine einzelne Uebung
    @PostMapping("/update/uebung/{id}")
    public String updateUebung(@PathVariable("id") Long id, @Valid Uebung uebung,
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            uebung.setId(id);
            return "update-uebung";
        }
            
        uR.save(uebung);
        return "redirect:/edit/{id}";
    }
    
    //Loescht den Tag mit den dazu gehoerigen Uebungen
    @GetMapping("/delete/{id}")
    public String deleteTag(@PathVariable("id") Long id, Model model) {
        Tag tag = fR.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
        fR.delete(tag);
        return "redirect:/index";
    }
    
    //Loescht eine einzelne Uebung
    @GetMapping("/delete/uebung/{id}")
    public String deleteUebung(@PathVariable("id") Long id, Model model) {
        Uebung uebung = uR.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
        uR.delete(uebung);
        return "redirect:/update-tag";
    }
    
    @PutMapping("/add{tagId}/{uebungId}")
    public Tag enrollTagToUebung (@PathVariable Long uebungId, 
    						  @PathVariable Long tagId)
    {
    	Uebung uebung = uR.findById(uebungId).get(); 
    	Tag tag = fR.findById(tagId).get();
    	tag.enrollUebung(uebung);
    	return fR.save(tag);
    	}

}
