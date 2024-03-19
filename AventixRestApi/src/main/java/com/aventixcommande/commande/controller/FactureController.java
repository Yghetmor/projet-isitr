package com.aventixcommande.commande.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aventixcommande.commande.entity.Commande;
import com.aventixcommande.commande.entity.Facture;
import com.aventixcommande.commande.service.FactureService;

@RestController
@RequestMapping("/api/factures")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @PostMapping
    public ResponseEntity<Facture> creerFacture(@RequestBody Commande commande) {
        Facture nouvelleFacture = factureService.creerFacture(commande);
        return new ResponseEntity<>(nouvelleFacture, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Facture>> recupererToutesLesFactures() {
        List<Facture> factures = factureService.recupererToutesLesFactures();
        return new ResponseEntity<>(factures, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facture> recupererFactureParId(@PathVariable Long id) {
        Facture facture = factureService.trouverFactureParId(id)
            .orElseThrow(() -> new RuntimeException("Facture non trouvée avec l'id: " + id));
        return new ResponseEntity<>(facture, HttpStatus.OK);
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Facture>> recupererFacturesParStatut(@PathVariable String statut) {
        List<Facture> factures = factureService.recupererFacturesParStatut(statut);
        return new ResponseEntity<>(factures, HttpStatus.OK);
    }

}