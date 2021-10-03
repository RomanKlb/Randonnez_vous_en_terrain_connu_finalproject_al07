import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotConnectingComponent } from './not-connecting.component';

describe('NotConnectingComponent', () => {
  let component: NotConnectingComponent;
  let fixture: ComponentFixture<NotConnectingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NotConnectingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NotConnectingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
