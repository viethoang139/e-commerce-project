import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Country } from '../commons/country';
import { HttpClient } from '@angular/common/http';
import { State } from '../commons/state';


@Injectable({
  providedIn: 'root'
})
export class FormService {


  private countriesUrl = "http://localhost:8080/api/countries";
  private statesUrl = "http://localhost:8080/api/states";

  constructor(private httpClient: HttpClient) { }


  getCreditCardMonths(startMonth: number): Observable<number[]>{
    let data: number[] = [];
    for(let theMonth = startMonth; theMonth <= 12; theMonth++){
      data.push(theMonth);
    }
    return of(data);
  }

  getCreditCardYears(): Observable<number[]>{
    let data: number[] = [];
    const startYear: number = new Date().getFullYear();
    const endYear: number = startYear + 10;

    for(let theYear = startYear; theYear <= endYear; theYear++){
      data.push(theYear);
    }
    return of(data);
  }

  getCountries(): Observable<Country[]>{
    return this.httpClient.get<Country[]>(this.countriesUrl);
  }

  getStates(theCountryCode: string): Observable<State[]>{
    return this.httpClient.get<State[]>(`${this.statesUrl}/findByCountryCode?code=${theCountryCode}`);
  }


}
