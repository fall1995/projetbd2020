import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AjouterfestivalComponent } from './ajouterfestival.component';

describe('AjouterfestivalComponent', () => {
  let component: AjouterfestivalComponent;
  let fixture: ComponentFixture<AjouterfestivalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AjouterfestivalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AjouterfestivalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
