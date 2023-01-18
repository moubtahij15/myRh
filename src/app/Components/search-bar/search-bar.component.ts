import {Component, EventEmitter, Output} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {OffreService} from "../../services/offre.service";
import {UserAuthService} from "../../services/user-auth.service";

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent {
  offreFormGroup!: FormGroup;
  profiles: Array<{
    "profile": "",
    "id": ""
  }> = []
  @Output() clickeSearch = new EventEmitter<any>()



  constructor(private fb: FormBuilder, private offreService: OffreService, private userAuth: UserAuthService) {
  }

  ngOnInit(): void {
    this.offreFormGroup = this.fb.group({
      ville: this.fb.control(""),
      profile_id: this.fb.control(""),
      niveau_etude: this.fb.control("")

    })
    this.getProfiles()

  }


  getProfiles() {
    this.offreService.getAllProfiles().subscribe(
      {
        next: (data: any) => {
          for (let i = 0; i < data.profiles.length; i++) {
            this.profiles.push({
              profile: data.profiles[i].titre,
              id: data.profiles[i].id
            })
          }
        }
      })
  }

  submit() {
    this.clickeSearch.emit(this.offreFormGroup.value);
  }
}
