import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefaultBoardComponent } from './default-board.component';

describe('DefaultBoardComponent', () => {
  let component: DefaultBoardComponent;
  let fixture: ComponentFixture<DefaultBoardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DefaultBoardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DefaultBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
