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
  }

  open(content: any) {
    this.currentModal = this.modalService.open(content, {
      ariaLabelledBy: 'modal-basic-title',
      backdrop: "static"})
  }

/*  deleteById() {
    this.commandService.delete(this.command? this.command.id : -1).subscribe(v => {
      this.currentModal?.close()
      this.router.navigate(['../'], {relativeTo: this.activatedRoute})
    })
  }*/

}
