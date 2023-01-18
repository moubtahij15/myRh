import {Component} from '@angular/core';
import {UserAuthService} from "../../services/user-auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-agent',
  templateUrl: './login-agent.component.html',
  styleUrls: ['./login-agent.component.css']
})
export class LoginAgentComponent {
  role: string = "AGENT";

  constructor(private authUser: UserAuthService, private router: Router) {
  }

  ngOnInit(): void {
    if (this.authUser.isLoggedIn()) {
      this.router.navigate(['dashboard']).then();
    }
  }
}
