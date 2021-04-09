import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CsvParseComponent } from './csv-parse.component';

describe('CsvParseComponent', () => {
  let component: CsvParseComponent;
  let fixture: ComponentFixture<CsvParseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CsvParseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CsvParseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
