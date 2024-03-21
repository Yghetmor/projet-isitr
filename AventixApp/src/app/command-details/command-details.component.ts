import { Component } from '@angular/core';
import {CommandModel} from "../../model/command";


@Component({
  selector: 'app-command-details',
  templateUrl: './command-details.component.html',
  styleUrls: ['./command-details.component.css']
})
export class CommandDetailsComponent {

  command: CommandModel | undefined;

  currentModal: NgbModalRef | undefined

  constructor(private klassServ: KlassService,
              private activatedRoute: ActivatedRoute,
              private modalService: NgbModal,
              private router: Router) {
  }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get("kId") || "";
    if (id != '') {
      this.klassServ.findOne(+id).subscribe(v => this.klass = v)
    }
  }

  open(content: any) {
    this.currentModal = this.modalService.open(content, {
      ariaLabelledBy: 'modal-basic-title',
      backdrop: "static"})
  }

  deleteById() {
    this.klassServ.delete(this.klass ? this.klass.id : -1).subscribe(v => {
      this.currentModal?.close()
      this.router.navigate(['../'], {relativeTo: this.activatedRoute})
    })
  }

}
