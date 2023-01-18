import {Component, OnInit} from '@angular/core';
import {OffreService} from "../../services/offre.service";
import {Offre} from "../../Components/model/Offre";
import {UserAuthService} from "../../services/user-auth.service";
import {data} from "autoprefixer";

@Component({
  selector: 'app-offre',
  templateUrl: './offre.component.html',
  styleUrls: ['./offre.component.css']
})
export class OffreComponent implements OnInit {
  offres: Array<Offre> = new Array<Offre>();
  pageOffre: Array<Offre> = new Array<Offre>();
  currentPage: number = 0;
  totalPages: number = 0;
  pageSize: number = 5;

  constructor(private offreService: OffreService, private autUser: UserAuthService) {
  }

  ngOnInit(): void {
    this.getAllOffres();
  }

  getAllOffres() {
    this.offreService.getAll().subscribe(
      {
        next: (data: any) => {

          for (let i = 0; i < data.offres.length; i++) {
            this.offres.push({
              titre: data.offres[i].titre,
              id: data.offres[i].id,
              description: data.offres[i].description,
              profile: data.offres[i].profile.titre,
              email: data.offres[i].recruteur.email,
              img_url: "http://localhost:8080/download/" + data.offres[i].recruteur.img_url.id,
              tel: data.offres[i].recruteur.tel,
              ville: data.offres[i].ville,
              etat: data.offres[i].etat,
              salaire: data.offres[i].salaire,
              etude: data.offres[i].niveau_etude,
              entreprise_name: data.offres[i].recruteur.entreprise_name,
            })
          }

          this.goToPage(this.currentPage)
        }
      }
    )
  }

  // goToPage(i: number) {
  //   console.log("from goToPage" + i)
  //   this.currentPage = i;
  //   this.offres = [];
  //   this.getAllOffres();
  // }
  goToPage(i: number) {
    console.log("from goToPage" + i)
    this.currentPage = i;
    let index = this.currentPage * this.pageSize;
    this.totalPages = ~~(this.offres.length / this.pageSize);
    if (this.offres.length % this.pageSize != 0) this.totalPages++;
    this.pageOffre = this.offres.slice(index, index + this.pageSize)
  }

  filterOffre(searchValue: any) {
    this.offres = [];
    this.offreService.getOffreBySearch(searchValue).subscribe({
      next: (data: any) => {
        console.log(data)
        for (let i = 0; i < data.offres.length; i++) {
          this.offres.push({
            titre: data.offres[i].titre,
            id: data.offres[i].id,
            description: data.offres[i].description,
            profile: data.offres[i].profile.titre,
            email: data.offres[i].recruteur.email,
            img_url: "http://localhost:8080/download/" + data.offres[i].recruteur.img_url.id,
            tel: data.offres[i].recruteur.tel,
            ville: data.offres[i].ville,
            etat: data.offres[i].etat,
            salaire: data.offres[i].salaire,
            etude: data.offres[i].niveau_etude,
            entreprise_name: data.offres[i].recruteur.entreprise_name,
          })
        }

        this.goToPage(this.currentPage)
      }
    })
    console.log(searchValue);
  }
}
