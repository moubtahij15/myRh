import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Offre} from "../model/Offre";

@Component({
  selector: 'app-modal-offre-details',
  templateUrl: './modal-offre-details.component.html',
  styleUrls: ['./modal-offre-details.component.css']
})
export class ModalOffreDetailsComponent {
  itemName: string="";

  constructor(
    public dialogRef: MatDialogRef<ModalOffreDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Offre
  ) {}

  onSubmit() {
    this.dialogRef.close({ itemName: this.itemName });
  }

  onCancel() {
    this.dialogRef.close();
  }
}
