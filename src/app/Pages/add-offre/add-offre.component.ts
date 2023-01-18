import {Component, OnInit} from '@angular/core';
import {UserAuthService} from "../../services/user-auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-offre',
  templateUrl: './add-offre.component.html',
  styleUrls: ['./add-offre.component.css']
})
export class AddOffreComponent implements OnInit {
  constructor(private authUser: UserAuthService, private router: Router) {
  }

  ngOnInit(): void {
    if (!(this.authUser.isLoggedIn() && this.authUser.isRecruteur())) {
      this.router.navigate(['/']).then();
    }
  }
}
