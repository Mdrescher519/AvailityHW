import { FormBuilder, FormGroup } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import * as fileSaver from 'file-saver';

@Component({
  selector: 'app-csv-parse',
  templateUrl: './csv-parse.component.html',
  styleUrls: ['./csv-parse.component.scss']
})
export class CsvParseComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private appService: AppService)  { }

  uploadForm: FormGroup;
  isFileChosen: boolean = false;
  fileName: string = '';
  file: File;
  companies: String[] = [];

  ngOnInit() {
    this.uploadForm = this.formBuilder.group({
      file: ['']
    });
  }

  // check if file exists, upload to formgroup - set flags
  selectFile(event: Event) {
    let fileTarget = (event.target as HTMLInputElement);
    if (fileTarget.files.length > 0){
      this.isFileChosen = true;
      this.file = fileTarget.files[0];
      this.fileName = this.file.name;
      this.uploadForm.get('file').setValue(this.file);
    }
  }

  //upload formgroup to formdata on sumbit
  onUpload(){
    const formData = new FormData();
    formData.append('file', this.uploadForm.get('file').value);
    //send server request with form data
    this.appService.parseCsv(formData).subscribe(data => {
      this.companies = data;
    });
  }

  //Save csv file download from server
  onDownload(company: String){
    this.appService.getCsv(company).subscribe(response => {
			fileSaver.saveAs(response, company+'.csv');
		});
  }
}
