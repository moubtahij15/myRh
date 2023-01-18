import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {UserAuthService} from "../../services/user-auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  role: string = "RECRUTEURE";

  constructor(private authUser: UserAuthService, private router: Router) {
  }

  ngOnInit(): void {
    if (this.authUser.isLoggedIn()) {
      this.router.navigate(['/']).then();
    }
  }
}
