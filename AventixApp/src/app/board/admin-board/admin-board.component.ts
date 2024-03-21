import { Component } from '@angular/core';
import {AuthService} from "../../auth.service";

@Component({
  selector: 'app-admin-board',
  templateUrl: './admin-board.component.html',
  styleUrls: ['./admin-board.component.css']
})
export class AdminBoardComponent {

  constructor(private authService: AuthService) {
  }

  onLogout(){
    this.authService.logout();
  }

}
