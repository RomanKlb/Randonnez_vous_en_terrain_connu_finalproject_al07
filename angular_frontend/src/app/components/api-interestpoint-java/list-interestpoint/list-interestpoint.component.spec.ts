import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListInterestpointComponent } from './list-interestpoint.component';

describe('ListInterestpointComponent', () => {
  let component: ListInterestpointComponent;
  let fixture: ComponentFixture<ListInterestpointComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListInterestpointComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListInterestpointComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
