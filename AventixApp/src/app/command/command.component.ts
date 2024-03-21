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

}
