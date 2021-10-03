import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminListcontrolIpComponent } from './admin-listcontrol-ip.component';

describe('AdminListcontrolIpComponent', () => {
  let component: AdminListcontrolIpComponent;
  let fixture: ComponentFixture<AdminListcontrolIpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminListcontrolIpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminListcontrolIpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
