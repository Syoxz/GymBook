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
public class TrainingController {
	
	@Autowired
	FitRepository fR;

	@Autowired
	UebungRepository uR;
	
@GetMapping("/signup")
public String showSignUpForm(Tag tag, Uebung uebung) {
    return "add-tag";
}
@PostMapping("/addUebung/{tagId}")
public String addUebung(@PathVariable Long tagId, Uebung uebung, BindingResult result) {
    if (result.hasErrors()) {
        return "add-uebung";
    }
    uR.save(uebung);
    enrollTagToUebung(uebung.getId(),tagId);
    return "redirect:/edit/{tagId}";
}
@PostMapping("/addtag")
public String addUebungUndTag(Uebung uebung, Tag tag, BindingResult result) {
    if (result.hasErrors()) {
        return "add-tag";
    }
    fR.save(tag);
    return "add-uebung";
}
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
public String showUpdateForm(@PathVariable Long id, Model model) {
    Tag tag = fR.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
    model.addAttribute("uebungen", uR.findAllByIstEnthalten(tag));
    model.addAttribute("tag", tag);
    return "update-tag";
} 
//Loescht den Tag mit den dazu gehoerigen Uebungen
@GetMapping("/delete/{id}")
public String deleteTag(@PathVariable("id") Long id, Model model) {
    Tag tag = fR.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
    fR.delete(tag);
    return "redirect:/index";
}   
@PutMapping("/add{tagId}/{uebungId}")
public Tag enrollTagToUebung (@PathVariable Long uebungId, 
    						  @PathVariable Long tagId) {
	Uebung uebung = uR.findById(uebungId).get(); 
	Tag tag = fR.findById(tagId).get();
	tag.enrollUebung(uebung);
	return fR.save(tag);
    	}
}