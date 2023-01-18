import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {RecruteurService} from "../../services/recruteur.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  userFormGroup!: FormGroup;
  TutorialName: string = 'Angular JS2';
  selectedFile = null;
  succes: boolean = false;
  error: string = "";

  imageSrc = "https://assets.stickpng.com/images/585e4bf3cb11b227491c339a.png";

  constructor(private fb: FormBuilder,
              private recruteurService: RecruteurService) {
  }


  ngOnInit(): void {
    // this.imageSrc = "https://images.unsplash.com/photo-1531316282956-d38457be0993?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=700&q=80"

    this.userFormGroup = this.fb.group({
      nom: this.fb.control(""),
      prenom: this.fb.control(""),
      email: this.fb.control(""),
      password: this.fb.control(""),
      tel: this.fb.control(""),
      adresse: this.fb.control(""),
      entreprise_name: this.fb.control(""),
      fileSource: this.fb.control(""),
      file: this.fb.control(""),
      name: this.fb.control(""),
    })

  }



  onFileChange(event: any) {
    const reader = new FileReader();
    this.selectedFile = event.target.files[0];

    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);

      reader.onload = () => {

        this.imageSrc = reader.result as string;

        this.userFormGroup.patchValue({
          fileSource: reader.result,
          file: this.selectedFile
        });

      };

    }
  }

  submit() {
    this.recruteurService.register(this.userFormGroup.value, this.selectedFile).subscribe({
      next: (data: any) => {
        this.succes = true;

      }
    })

  }

}
