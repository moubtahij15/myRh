import {OnInit} from "@angular/core";

export class appUser {
  grantType: string = "password";
  username: string = "";
  password: string = "";
  withRefreshToken: boolean = true;
  role: string = "RECRUTEURE";
  refreshToken: string = ""


  constructor() {
  }
}
