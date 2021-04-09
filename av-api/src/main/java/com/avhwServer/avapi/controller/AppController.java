package com.avhwServer.avapi.controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.avhwServer.avapi.entity.Users;
import com.avhwServer.avapi.entity.Enrollees;
import com.avhwServer.avapi.repository.EnrolleesRepository;
import com.avhwServer.avapi.repository.UsersRepository;
import com.opencsv.CSVReader;

@RestController
@CrossOrigin(origins = "http:localhost:4200")
public class AppController {

    UsersRepository usersRepository;
    EnrolleesRepository enrolleesRepository;

    public AppController(UsersRepository usersRepository, EnrolleesRepository enrolleesRepository){
        this.usersRepository = usersRepository;
        this.enrolleesRepository = enrolleesRepository;
    }

    // Get all users from db
	@GetMapping("/api/users")
    public Iterable<Users> getUsers(){
        return usersRepository.findAll();
    }

    // Insert 1 user into db
    @PostMapping("/api/user")
	public HttpStatus addUser(@RequestBody Users user){
        usersRepository.save(user);
        return HttpStatus.CREATED;
    }

    // Server side processing - Parenthesis counting for LISP code
    @PostMapping("/api/lisp")
    public Integer parseLisp(@RequestBody String lisp){
        int count = 0;
        lisp = lisp.trim();

        for(int i=0; i<lisp.length(); i++){
            // add 1 to count for each (
            if(lisp.charAt(i) == '(') count++;

            // subtract 1 from count for each )
            else if(lisp.charAt(i) == ')') count--;

            // if count ever goes negative, opening paranthesis was skipped
            if(count < 0) break;
        }
        return count;
    }

    // Parse CSV file and upload into db
    @PostMapping("/api/csv/upload")
    public List<String> parseCSV(@RequestParam("file") MultipartFile file) throws IOException, SQLException {

        Reader reader = new InputStreamReader(file.getInputStream());
        CSVReader csvReader = new CSVReader(reader);
        
        String[] line;
        int count = 0;
        
        while ((line = csvReader.readNext()) != null) {
            // Check if file has a header
            if(count == 0){
                if(line[0].toLowerCase().equals("user id") || line[1].toLowerCase().equals("first name") || line[2].toLowerCase().equals("last name") || 
                    line[3].toLowerCase().equals("version") || line[4].toLowerCase().equals("insurance company")){
                    continue;
                }
            }
            
            Enrollees enr = new Enrollees(count, line[0].trim(), line[1].trim(), line[2].trim(), Integer.parseInt(line[3].trim()), line[4].trim());

            // If userID exists, replace with highest version - else insert
            if(enrolleesRepository.checkIfUserIdExists(enr.getUserId()) > 0){
                Enrollees enrExisting = enrolleesRepository.findByUserId(enr.getUserId());
                if(enr.getVersion() > enrExisting.getVersion()){
                    enrolleesRepository.update(enr);
                }
            }
            else{
                enrolleesRepository.insert(enr);
            }

            count++;
        }

        reader.close();
        csvReader.close();

        // Return distinct list of companies
        return enrolleesRepository.findAllCompany();
    }

    @GetMapping("api/csv/download/{company}")
    public ResponseEntity<Resource> getCSV(@PathVariable String company) throws Exception{

        // Select all users from user selcted company
        List<Enrollees> enrList = enrolleesRepository.findByCompany(company);

        CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);

        // convert to string for CSV
        for (Enrollees enr : enrList) {
            List<String> data = Arrays.asList(
                enr.getUserId(),
                enr.getFirstName(),
                enr.getLastName(),
                String.valueOf(enr.getVersion()),
                enr.getCompany()
            );
            csvPrinter.printRecord(data);
        }

        csvPrinter.flush();
        InputStreamResource file = new InputStreamResource(new ByteArrayInputStream(out.toByteArray()));

        csvPrinter.close();

        // return list in CSV format
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + company +".csv").contentType(MediaType.parseMediaType("application/csv")).body(file);
    }
}
