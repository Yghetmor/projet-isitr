package com.aventixcommande.commande.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aventixcommande.commande.entity.Commande;
import com.aventixcommande.commande.entity.Facture;
import com.aventixcommande.commande.ripository.FactureRepository;

@Service
public class FactureService {
    
    @Autowired
    private FactureRepository factureRepository;

    public Facture creerFacture(Commande commande) {
        Facture facture = new Facture();
        facture.setDateEmission(new Date());
        facture.setMontant(new BigDecimal (100)); // Exemple, définir le montant réel ici
        facture.setStatut("En attente de paiement");
        facture.setCommande(commande);
        return factureRepository.save(facture);
    }

    public List<Facture> recupererToutesLesFactures() {
        return factureRepository.findAll();
    }

    public Optional<Facture> trouverFactureParId(Long id) {
        return factureRepository.findById(id);
    }

    public Facture mettreAJourStatutFacture(Long id, String nouveauStatut) {
        Facture facture = factureRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Facture non trouvée avec l'id : " + id));
        facture.setStatut(nouveauStatut);
        return factureRepository.save(facture);
    }

    public List<Facture> recupererFacturesParStatut(String statut) {
        return factureRepository.findAll().stream()
            .filter(facture -> facture.getStatut().equals(statut))
            .collect(Collectors.toList());
    }

}