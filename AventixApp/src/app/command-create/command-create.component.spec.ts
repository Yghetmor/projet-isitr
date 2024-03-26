import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommandCreateComponent } from './command-create.component';

describe('CommandCreateComponent', () => {
  let component: CommandCreateComponent;
  let fixture: ComponentFixture<CommandCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommandCreateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CommandCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
