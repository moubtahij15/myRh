import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {ModalOffreDetailsComponent} from "../modal-offre-details/modal-offre-details.component";
import {Offre} from "../model/Offre";
import {UserAuthService} from "../../services/user-auth.service";
import {OffreService} from "../../services/offre.service";

@Component({
  selector: 'app-offre-details',
  templateUrl: './offre-details.component.html',
  styleUrls: ['./offre-details.component.css']
})
export class OffreDetailsComponent {
  @Input() offre!: Offre
  @Output() clickeda = new EventEmitter<void>()

  constructor(public dialog: MatDialog, private autUser: UserAuthService, private offreService: OffreService) {
  }

  openCreateModal(offre: Offre) {
    const dialogRef = this.dialog.open(ModalOffreDetailsComponent, {
      width: '580px',
      data: offre
    });
    dialogRef.afterClosed().subscribe(result => {
    });
  }

  validerOffre(id: number) {
    this.offreService.validerOffer(id).subscribe({
      next: (data: any) => {
        this.clickeda.emit()
      }
    })
  }

  isAgent() {
    return this.autUser.isAgent();
  }
}
