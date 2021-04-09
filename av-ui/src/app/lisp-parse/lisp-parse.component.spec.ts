import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LispParseComponent } from './lisp-parse.component';

describe('LispParseComponent', () => {
  let component: LispParseComponent;
  let fixture: ComponentFixture<LispParseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LispParseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LispParseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
