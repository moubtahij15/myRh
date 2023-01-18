import {Component, OnInit} from '@angular/core';
import {OffreService} from "../../services/offre.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {UserAuthService} from "../../services/user-auth.service";

@Component({
  selector: 'app-add-offre-form',
  templateUrl: './add-offre-form.component.html',
  styleUrls: ['./add-offre-form.component.css']
})
export class AddOffreFormComponent implements OnInit {
  succes: boolean = false;
  offreFormGroup!: FormGroup;
  profiles: Array<{
    "profile": "",
    "id": ""
  }> = []

  constructor(private fb: FormBuilder, private offreService: OffreService, private userAuth: UserAuthService) {
  }

  ngOnInit(): void {
    this.offreFormGroup = this.fb.group({
      titre: this.fb.control(""),
      ville: this.fb.control(""),
      profile_id: this.fb.control(""),
      niveau_etude: this.fb.control(""),
      salaire: this.fb.control(""),
      description: this.fb.control(""),
      recruteur_id: this.userAuth.getId()

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
    this.offreService.saveOffer(this.offreFormGroup.value).subscribe(
      {
        next: (data: any) => {
          this.succes = true;

        }
      })

  }
}
