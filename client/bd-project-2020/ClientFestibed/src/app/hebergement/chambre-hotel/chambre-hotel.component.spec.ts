import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChambreHotelComponent } from './chambre-hotel.component';

describe('ChambreHotelComponent', () => {
  let component: ChambreHotelComponent;
  let fixture: ComponentFixture<ChambreHotelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChambreHotelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChambreHotelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
