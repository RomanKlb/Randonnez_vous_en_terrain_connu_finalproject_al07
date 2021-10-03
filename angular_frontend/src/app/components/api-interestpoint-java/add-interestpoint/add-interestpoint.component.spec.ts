import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddInterestpointComponent } from './add-interestpoint.component';

describe('AddInterestpointComponent', () => {
  let component: AddInterestpointComponent;
  let fixture: ComponentFixture<AddInterestpointComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddInterestpointComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddInterestpointComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
