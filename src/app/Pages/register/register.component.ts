import {Component} from '@angular/core';
import {UserAuthService} from "../../services/user-auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  constructor(private authUser: UserAuthService, private router: Router) {
  }

  ngOnInit(): void {
    if (this.authUser.isLoggedIn()) {
      this.router.navigate(['/']).then();
    }
  }
}
