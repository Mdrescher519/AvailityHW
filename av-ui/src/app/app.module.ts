import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { LispParseComponent } from './lisp-parse/lisp-parse.component';
import { UserRegComponent } from './user-reg/user-reg.component';
import { CsvParseComponent } from './csv-parse/csv-parse.component';

@NgModule({
  declarations: [
    AppComponent,
    LispParseComponent,
    UserRegComponent,
    CsvParseComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    TabsModule.forRoot(),
    CollapseModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
