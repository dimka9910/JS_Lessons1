package labs.lab2.src.registration.service;

import labs.lab2.src.registration.model.ActionStatus;
import labs.lab2.src.registration.service.forStudents.ServiceForStudents;

public class ForTest {
    public static void  main(String[] args){
        ServiceForStudents studentService = new ServiceForStudents();

        ActionStatus actionStatus = studentService.subscribe(102,10123);
    }
}
