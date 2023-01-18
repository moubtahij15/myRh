import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserAuthService} from "./user-auth.service";
import {appUser} from "../Components/model/user.model";

@Injectable({
  providedIn: 'root'
})
export class RecruteurService {
  PATH_OF_API = 'http://localhost:8080';

  requestHeader = new HttpHeaders({
    'No-Auth': 'True',
    'Content-Type': 'application/json',
    'enctype': "multipart/form-data",
    'Accept': '*/*'
  });


  constructor(private httpClient: HttpClient, private authService: UserAuthService) {
  }

  public login(loginData: appUser) {
    return this.httpClient.post(this.PATH_OF_API + '/token', loginData, {
      headers: this.requestHeader,
    });
  }

  register(userFormGroup: any, file: any) {

    let formData = new FormData();
    formData.append("recruteur", new Blob([JSON.stringify(userFormGroup)], {
      type: "application/json"
    }));
    formData.append("file", file, file.name);
    return this.httpClient.post(this.PATH_OF_API + '/inscription', formData
    );
  }

  senMail(idUser: number) {
    console.log(idUser)
    return this.httpClient.get(this.PATH_OF_API + '/resend/' + idUser);
  }
}
