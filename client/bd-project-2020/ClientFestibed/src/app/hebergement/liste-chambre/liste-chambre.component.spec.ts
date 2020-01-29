import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeChambreComponent } from './liste-chambre.component';

describe('ListeChambreComponent', () => {
  let component: ListeChambreComponent;
  let fixture: ComponentFixture<ListeChambreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListeChambreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeChambreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
