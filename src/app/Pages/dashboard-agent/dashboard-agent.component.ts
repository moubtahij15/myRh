import {Component} from '@angular/core';
import {Offre} from "../../Components/model/Offre";
import {OffreService} from "../../services/offre.service";
import {UserAuthService} from "../../services/user-auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-dashboard-agent',
  templateUrl: './dashboard-agent.component.html',
  styleUrls: ['./dashboard-agent.component.css']
})
export class DashboardAgentComponent {
  offres: Array<Offre> = new Array<Offre>();
  offresPage: Array<Offre> = new Array<Offre>();
  currentPage: number = 0;
  totalPages: number = 0;
  pageSize: number = 4;
  role: string = this.authUser.getRoles()

  constructor(private offreService: OffreService, private authUser: UserAuthService, private router: Router) {
  }

  ngOnInit(): void {
    if (!(this.authUser.isLoggedIn() && this.authUser.isAgent())) {
      this.router.navigate(['/']).then();
    }
    this.getAllOffres();
  }

  t() {
    console.log("khadaaama")
  }

  getAllOffres() {
    this.offresPage = []
    this.offres = []
    console.log("azertyuio")
    this.offreService.getAllEnAttente().subscribe(
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
              salaire: data.offres[i].salaire,
              etat: data.offres[i].etat,
              etude: data.offres[i].niveau_etude,
              entreprise_name: data.offres[i].recruteur.entreprise_name,
            })
          }
          this.goToPage(this.currentPage)


        }
      }
    )
  }


  goToPage(i: number) {
    console.log("from goToPage" + i)
    this.currentPage = i;
    // this.offres = [];
    let index = this.currentPage * this.pageSize;
    this.totalPages = ~~(this.offres.length / this.pageSize);
    if (this.offres.length % this.pageSize != 0) this.totalPages++;
    this.offresPage = this.offres.slice(index, index + this.pageSize)
    // this.getAllOffres();
  }


}
