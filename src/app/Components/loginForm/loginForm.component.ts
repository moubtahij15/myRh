import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {RecruteurService} from "../../services/recruteur.service";
import {appUser} from "../model/user.model";
import {UserAuthService} from "../../services/user-auth.service";
import {Router} from "@angular/router";
import {Offre} from "../model/Offre";

@Component({
  selector: 'app-loginForm',
  templateUrl: './loginForm.component.html',
  styleUrls: ['./loginForm.component.css']
})
export class LoginFormComponent implements OnInit {

  @Input() role!: string

  userFormGroup!: FormGroup;
  loginData!: appUser;
  error: string = "";
  idUser: number = 0;


  constructor(private fb: FormBuilder,
              private recruteurService: RecruteurService,
              private userAuthService: UserAuthService,
              private router: Router
  ) {
    this.loginData = new appUser()
  }

  ngOnInit(): void {
    this.userFormGroup = this.fb.group({
      email: this.fb.control(""),
      password: this.fb.control("")
    })

  }


  login() {
    this.loginData.role = this.role
    this.loginData.username = this.userFormGroup.value.email;
    this.loginData.password = this.userFormGroup.value.password;
    this.recruteurService.login(this.loginData).subscribe({
      next: (data: any) => {
        console.log(data)
        if (data.statut != null && data.statut == "error") {
          if (data.type == "validation") {
            this.idUser = data.id;
          }
          this.error = data.errorMessage
        }
        this.userAuthService.setToken(data.accessToken);
        this.userAuthService.setRoles(data.role)

        if (data.role == "RECRUTEUR") {
          this.userAuthService.setId(data.recruteur.id)
          this.router.navigate(['/']);

        } else if (data.role == "AGENT") {
          this.router.navigate(['/dashboard']);


        }
      }
    })

  }

  sendMail() {
    console.log(this.idUser)
    this.recruteurService.senMail(this.idUser).subscribe({
      next: (data: any) => {
        console.log(data)
        this.userFormGroup.reset()
        this.error = "";
        this.idUser = 0;


      }
    })

  }
}
