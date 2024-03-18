import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployerBoardComponent } from './employer-board.component';

describe('EmployerBoardComponent', () => {
  let component: EmployerBoardComponent;
  let fixture: ComponentFixture<EmployerBoardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployerBoardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployerBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
