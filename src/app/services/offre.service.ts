import {Injectable} from '@angular/core';
import {Offre} from "../Components/model/Offre";
import {async, Observable, of} from "rxjs";
import {PageProduct, Product} from "../Components/model/Product";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserAuthService} from "./user-auth.service";
import {appUser} from "../Components/model/user.model";

@Injectable({
  providedIn: 'root'
})
export class OffreService {
  PATH_OF_API = 'http://localhost:8080';

  offers: Array<Offre> = []

  constructor(private httpClient: HttpClient, private auth: UserAuthService) {
  }


  getAll() {
    return this.httpClient.get(this.PATH_OF_API + '/offer/valider');
  }

  getAllEnAttente() {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.auth.getToken()}`
    });
    return this.httpClient.get(this.PATH_OF_API + '/offer/enattente', {headers});
  }

  getAllProfiles() {
    console.log(this.auth.getToken())
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.auth.getToken()}`
    });
    console.log(headers)
    return this.httpClient.get(this.PATH_OF_API + '/profiles');
  }

  getOffreBySearch(searchValue: any) {
    console.log(this.auth.getToken())
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.auth.getToken()}`
    });
    console.log(headers)
    return this.httpClient.post(this.PATH_OF_API + '/searchOffre',
      searchValue
    );
  }

  validerOffer(id: number) {
    console.log(this.auth.getToken())
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.auth.getToken()}`
    });
    console.log(headers)
    return this.httpClient.get(this.PATH_OF_API + '/offer/valider/' + id, {headers});
  }

  public saveOffer(offrData: any) {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.auth.getToken()}`
    });
    return this.httpClient.post(this.PATH_OF_API + '/offer', offrData, {
      headers
    });
  }

}
