import {Component, OnInit} from '@angular/core';
import {CommandModel} from "../../model/command";
import {CommandService} from "../command.service";

@Component({
  selector: 'app-command-list',
  templateUrl: './command-list.component.html',
  styleUrls: ['./command-list.component.css']
})
export class CommandListComponent implements OnInit{


  commands : CommandModel[] = [];


  constructor(private commandeService : CommandService) {}

  ngOnInit(): void {
    this.fetchCommands();

    this.commandeService.findByEmployerId(1).subscribe({
      next: (data) => this.commands = data,
      error: (error) => console.error(error)
    });




    const employerIdString = localStorage.getItem('employer_id');
    let employerId : number;
    if(employerIdString != null){
      employerId = parseInt(employerIdString, 10);
      if(employerId != 0){
        this.commandeService.findByEmployerId(employerId).subscribe(v => this.commands = v);
        console.log(this.commands.length);
      }
    }

  }

  getEmployerId():number{
    let employerIdString = localStorage.getItem('employer_id') || "0";
    return parseInt(employerIdString, 10);

  }

  fetchCommands():void{
    let employerId = this.getEmployerId();
      if(employerId!=0){
        this.commandeService.findByEmployerId(employerId).subscribe({
          next: (commands) => this.commands = commands,
          error: (error) => console.error('There was an error fetching commands!', error)
        });
    }

  }






}
