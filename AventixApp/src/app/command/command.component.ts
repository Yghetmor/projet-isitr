import {Component, Input} from '@angular/core';
import {CommandModel} from '../../model/command';

@Component({
  selector: 'app-command',
  templateUrl: './command.component.html',
  styleUrls: ['./command.component.css']
})
export class CommandComponent {


  @Input()
  command: CommandModel | undefined;





  formatStatus(status?: string): string {
    const statuses = {
      EN_COURS: 'En cours',
      CONFIRMER: 'Confirmée',
      ANNULER: 'Annulée',
      LIVRER: 'Livrée',
    };
    return statuses[status as keyof typeof statuses] || status || 'Status Unknown';
  }

}
