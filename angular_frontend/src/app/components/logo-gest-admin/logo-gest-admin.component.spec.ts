import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogoGestAdminComponent } from './logo-gest-admin.component';

describe('LogoGestAdminComponent', () => {
  let component: LogoGestAdminComponent;
  let fixture: ComponentFixture<LogoGestAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LogoGestAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LogoGestAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
