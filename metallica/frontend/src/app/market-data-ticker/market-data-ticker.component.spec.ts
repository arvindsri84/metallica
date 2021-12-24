import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketDataTickerComponent } from './market-data-ticker.component';

describe('MarketDataTickerComponent', () => {
  let component: MarketDataTickerComponent;
  let fixture: ComponentFixture<MarketDataTickerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarketDataTickerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketDataTickerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
