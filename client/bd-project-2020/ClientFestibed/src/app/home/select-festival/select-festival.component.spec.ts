import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectFestivalComponent } from './select-festival.component';

describe('SelectFestivalComponent', () => {
  let component: SelectFestivalComponent;
  let fixture: ComponentFixture<SelectFestivalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelectFestivalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectFestivalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
