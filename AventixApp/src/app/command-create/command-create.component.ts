import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CommandService} from "../command.service";
import {ActivatedRoute, Router} from "@angular/router";
import {environment} from "../../environment/environment";
import {CommandModel} from "../../model/command";

@Component({
  selector: 'app-command-create',
  templateUrl: './command-create.component.html',
  styleUrls: ['./command-create.component.css']
})
export class CommandCreateComponent implements OnInit {
  createCommandForm!: FormGroup;
  formSubmitted: boolean = false
  successMessage!: string
  errorMessage!: string

  private url = `${environment.urlApi}`

  constructor(
    private fb: FormBuilder,
    private commandService: CommandService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
  ) {
  };

  ngOnInit(): void {
    this.createCommandForm = this.fb.group({
      nbCarte: ['', Validators.required],
      tauxParticipation: ['', Validators.required],
    });
  }

  onSubmit() {
    this.formSubmitted = true;

    if (this.createCommandForm.invalid) {
      return;
    }

    const employerId = localStorage.getItem('employer_id');
    if (employerId === null) {
      console.error("L'ID de l'employeur est manquant.");
      return;
    }

    const commandData = {
      numeroCommande: 0, // par défaut mais est trivial
      nbCarte: this.createCommandForm.value.nbCarte,
      tauxParticipation: this.createCommandForm.value.tauxParticipation,
      dateCommande: new Date().toISOString(),
      statut: 'EN_COURS',
      utilisateur: {id: +employerId},
      reclamations: [],
    };

    this.commandService.add(commandData).subscribe({
      next: (response) => {
        this.errorMessage = '';
        this.successMessage = 'Commande ajoutée avec succès';
        setTimeout(() => {
          this.router.navigate([`employer/commands`]);
        }, 2000);
      },
      error: (error) => {
        this.errorMessage = error.error.message;
        console.error('Erreur lors de l\'ajout de la commande:', error);

      }
    });
  }
}
