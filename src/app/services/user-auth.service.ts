import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() {
  }

  public setRoles(roles: string) {
    localStorage.setItem('roles', roles);
  }

  public getRoles(): string {
    // @ts-ignore
    return localStorage.getItem('roles');
  }

  public setToken(jwtToken: string) {
    localStorage.setItem('jwtToken', jwtToken);
  }

  public getToken(): string {
    // @ts-ignore
    return localStorage.getItem('jwtToken');
  }

  public isAgent(): boolean {
    return this.getRoles() == "AGENT"
  }

  public isRecruteur(): boolean {
    return this.getRoles() == "RECRUTEUR"
  }

  public setId(id: string) {
    localStorage.setItem('id', id);
  }

  public getId(): string {
    // @ts-ignore
    return localStorage.getItem('id');
  }

  public clear() {
    localStorage.clear();
  }

  public isLoggedIn(): boolean {
    if (this.getRoles() && this.getToken()) {
      return true;
    }
    return false
  }
}
