import {Component} from '@angular/core';
import {UserAuthService} from "../../services/user-auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private userAuthService: UserAuthService,
              private router: Router) {
  }

  public isLoggedIn() {
    return this.userAuthService.isLoggedIn();
  }

  public isRecruteur(): boolean {
    return this.userAuthService.isRecruteur()
  }

  public logout() {
    this.userAuthService.clear();
    this.router.navigate(['/']).then();
  }
}
