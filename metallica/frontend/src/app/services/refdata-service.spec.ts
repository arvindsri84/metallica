import { TestBed, inject } from '@angular/core/testing';

import { RefdataService } from './refdata-service';

describe('RefdataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RefdataService]
    });
  });

  it('should be created', inject([RefdataService], (service: RefdataService) => {
    expect(service).toBeTruthy();
  }));
});
