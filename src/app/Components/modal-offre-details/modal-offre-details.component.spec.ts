import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalOffreDetailsComponent } from './modal-offre-details.component';

describe('ModalOffreDetailsComponent', () => {
  let component: ModalOffreDetailsComponent;
  let fixture: ComponentFixture<ModalOffreDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalOffreDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalOffreDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
