import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { AppService } from '../app.service';

@Component({
  selector: 'app-lisp-parse',
  templateUrl: './lisp-parse.component.html',
  styleUrls: ['./lisp-parse.component.scss']
})
export class LispParseComponent {

  constructor(private appService: AppService) { }

  lisp = new FormControl('', Validators.required);
  validation = {
    val: 0,
    message: ''
  };

  //add data to form - send/recieve server request for string parse
  onSubmit(){
    this.appService.parseLisp(this.lisp.value).subscribe(data =>{
      this.validation.val = data;
      this.validation.message = this.validationMessage(data);
    })
  }

  validationMessage(val: any): string {
    if(val == 0) return 'All parenthesis are properly closed';
    else if(val < 0) return 'Too many closing ")" parenthesis';
    else return 'Too many opening "(" parenthesis';
  }

}
