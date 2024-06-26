import { Component, OnInit } from '@angular/core';
import { HttpClientModule, HttpClient } from "@angular/common/http";
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  imports: [
    RouterModule,
    CommonModule,
    HttpClientModule
  ],
  standalone: true,
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'employees';
  dayInfo: any;
  showTable = true;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get("http://localhost:8080/employee/api/getAllDays")
      .subscribe(response => {
        console.log(response)
        this.dayInfo = response;
      });
  }
  toggleShowTable(): void{
    /*const employee = { name: "Mariethe" }; // Assuming the server expects a JSON payload

    this.http.put("http://localhost:8080/employee/api/putEmployee", employee)
      .subscribe(response => {
        console.log(response);
        this.showTable = !this.showTable;
      });*/
    this.showTable = !this.showTable;

  }
}
