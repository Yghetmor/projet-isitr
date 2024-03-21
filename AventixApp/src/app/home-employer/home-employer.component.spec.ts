import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeEmployerComponent } from './home-employer.component';

describe('HomeEmployerComponent', () => {
  let component: HomeEmployerComponent;
  let fixture: ComponentFixture<HomeEmployerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeEmployerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeEmployerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
