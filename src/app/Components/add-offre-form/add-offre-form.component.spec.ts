import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddOffreFormComponent } from './add-offre-form.component';

describe('AddOffreFormComponent', () => {
  let component: AddOffreFormComponent;
  let fixture: ComponentFixture<AddOffreFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddOffreFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddOffreFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
