import { Component } from '@angular/core';
import {AuthService} from "../../auth.service";

@Component({
  selector: 'app-employer-board',
  templateUrl: './employer-board.component.html',
  styleUrls: ['./employer-board.component.css']
})
export class EmployerBoardComponent {
  constructor(private authService: AuthService) {
  }

  onLogout(){
    this.authService.logout();
  }

}
