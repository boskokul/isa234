import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Contract } from "../model/contract";
import { environment } from "../env/environment";



@Injectable({
    providedIn: 'root',
  })
  export class ContractService {
    constructor(private http: HttpClient) {}
  
    getAllContracts(): Observable<Contract>{
        return this.http.get<any>(environment.apiHost + 'contracts/getAll')
    }

    cancelContract(c: Contract): Observable<any> {
        return this.http.post<any>(environment.apiHost + 'contracts', c);
    }
  }