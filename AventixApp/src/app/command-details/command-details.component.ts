import { Component } from '@angular/core';
import {CommandModel} from "../../model/command";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {CommandService} from "../command.service";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
  selector: 'app-command-details',
  templateUrl: './command-details.component.html',
  styleUrls: ['./command-details.component.css']
})
export class CommandDetailsComponent {

  command: CommandModel | undefined;

  currentModal: NgbModalRef | undefined

  constructor(private commandService: CommandService,
              private activatedRoute: ActivatedRoute,
              private modalService: NgbModal,
              private router: Router) {
  }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get("cId") || "";
    if (id != '') {
      this.commandService.findOne(+id).subscribe(v => this.command = v)
    }
    console.log(this.command?.nbCarte);
  }

  calcMontant(nbCarte: number, tauxParticipation: number): number {
    return nbCarte * 25 * (1 - (tauxParticipation / 100));
  }

  formatStatus(status?: string): string {
    const statuses = {
      EN_COURS: 'En cours',
      CONFIRMER: 'Confirmée',
      ANNULER: 'Annulée',
      LIVRER: 'Livrée',
    };
    return statuses[status as keyof typeof statuses] || status || 'Status Unknown';
  }

  open(content: any) {
    this.currentModal = this.modalService.open(content, {
      ariaLabelledBy: 'modal-basic-title',
      backdrop: "static"})
  }

  cancelCommande() {
    this.commandService.cancel(this.command ? this.command.numeroCommande : -1).subscribe(v => {
      this.currentModal?.close()
      this.router.navigate(['../'], {relativeTo: this.activatedRoute})
    })
  }


}
