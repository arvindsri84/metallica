import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MatMenuModule } from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatTabsModule} from '@angular/material/tabs';
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSelectModule} from '@angular/material/select';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatTableModule} from '@angular/material/table';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatRadioModule} from '@angular/material/radio';
import { MatToolbarModule, MatSnackBarModule  } from '@angular/material';

import { TradeSearchComponent } from './trade-search.component';

describe('TradeSearchComponent', () => {
  let component: TradeSearchComponent;
  let fixture: ComponentFixture<TradeSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TradeSearchComponent ],
      imports: [
          MatToolbarModule,
          MatMenuModule,
          MatIconModule,
          MatButtonModule,
          MatTabsModule,
          MatInputModule,
          MatCardModule,
          MatDatepickerModule,
          MatSelectModule,
          MatCheckboxModule,
          MatTableModule,
          MatGridListModule,
          MatRadioModule,
          MatSnackBarModule
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TradeSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
