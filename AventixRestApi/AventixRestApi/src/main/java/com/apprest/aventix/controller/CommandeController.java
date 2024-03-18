package com.apprest.aventix.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apprest.aventix.model.Commande;
import com.apprest.aventix.service.CommandeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/commande")
public class CommandeController {
	
	//auto injection de la propriete pour permettre de faire la communication avec la souche service
	@Autowired
	CommandeService commandeService;
	
	//methode qui permet de trouver les commande d'un client
    @GetMapping("/client/{idClient}")
    public String getCommandesForClient(@PathVariable long idClient, Model model) {
    	System.out.println("Client ID: " + idClient);
    	//recupere une liste de commande
        List<Commande> commandes = commandeService.getCommandesForClient(idClient);
        //definition du nom de l'attribut dans la page d'affichage 
        model.addAttribute("commandesList", commandes);
        return "commandes-client";  
    }
	
  //capte requete pour creation de commande
    @PostMapping("")
    public String createCommande(@Valid @ModelAttribute Commande commande, BindingResult results) {
        // Vérifier s'il y a des erreurs de validation avant de créer la commande
        if (results.hasErrors()) {
            return "commande-create-form";
        }

        // Créer la commande uniquement si la validation réussit
        commandeService.createCommande(commande);

        // Renvoie vers la page comportant le même nom
        return "commande-created";
    }
	
	//controller qui sert la page d'acceuil des commande
	@GetMapping("/admin/home")
	public String displayHome(Model model) {
		
		//recupere liste de commande et defini l'attribut 
		model.addAttribute("commandes", commandeService.getCommandeList());
		return "invoice-home";
		
	}
	
	@GetMapping("/admin/{id}")
	public String confirmCommande(@PathVariable("id")int num, Model model) {
		model.addAttribute("commande", commandeService.confirmCommandeAdmin(num));
		
		return "commande-details";
	}
	
	@GetMapping("/{id}")
	public String displayCommande(@PathVariable("id")int num, Model model) {
		model.addAttribute("commande", commandeService.getCommandeByNumber(num));
		
		return "commande-details-a";
	}
	
	@GetMapping("/delete/{id}")
	public String annuleCommande(@PathVariable("id")int num, Model model) {
		System.out.println("Methode annule commande a été invqoué");
		Commande c = commandeService.annuleCommandeService(num);
		model.addAttribute("commande", c);
		if(c.getStatut().equals("Annuler"))
			return "commande-details-a";
		else
			return "commande-details-b";
	}
	
	@GetMapping("/create-form")
	public String displayCommandeForm(@ModelAttribute Commande commande) {
		return "commande-create-form";
	}

}
