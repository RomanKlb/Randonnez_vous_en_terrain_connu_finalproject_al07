import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogoGestIpComponent } from './logo-gest-ip.component';

describe('LogoGestIpComponent', () => {
  let component: LogoGestIpComponent;
  let fixture: ComponentFixture<LogoGestIpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LogoGestIpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LogoGestIpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
