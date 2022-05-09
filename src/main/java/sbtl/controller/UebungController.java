package sbtl.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sbtl.model.Tag;
import sbtl.model.Uebung;
import sbtl.repository.FitRepository;
import sbtl.repository.UebungRepository;

@Controller
public class UebungController {

	@Autowired
	FitRepository fR;
	
	@Autowired
	UebungRepository uR;
	
//Erscheint wenn man eine neue Uebung einem Tag hinzufuegen will
@GetMapping("/showLabel/{id}")
public String showLabelForm(@PathVariable("id") Long id, Uebung uebung, Model model) {
	Tag tag = fR.findById(id)
    		.orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
	model.addAttribute("tag", tag);
    return "add-uebung";
}
//Editiert eine einzelne Uebung
@GetMapping("/edit/uebung/{id}")
    public String editUebung(@PathVariable("id") Long id, Model model) {
        Uebung uebung  = uR.findById(id)
        		.orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
        model.addAttribute("uebung", uebung);
        return "update-uebung";
    }
//Updated eine einzelne Uebung
@PostMapping("/update/uebung/{id}")
    public String updateUebung(@PathVariable("id") Long id, @Valid Uebung uebung, 
      BindingResult result, Model model) {
        if (result.hasErrors())
        {
            uebung.setId(id);
            return "update-uebung";
        }
        Tag tag = fR.findByEnthaelt(uebung);
        model.addAttribute("uebungen", uR.findAllByIstEnthalten(tag));
        model.addAttribute("tag", tag);
        uR.save(uebung);
        return "update-tag";
    }
//Loescht eine einzelne Uebung
@GetMapping("/delete/uebung/{id}")
public String deleteUebung(@PathVariable("id") Long id, Model model) {
    Uebung uebung = uR.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Invalid  Id:" + id));
    Tag tag = fR.findByEnthaelt(uebung);
    tag.deleteUebung(uebung);
    uR.delete(uebung);
    model.addAttribute("uebungen", uR.findAllByIstEnthalten(tag));
    model.addAttribute("tag", tag);
    return "update-tag"; 
}
}
