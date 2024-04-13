package org.example.pattern;

import java.time.LocalDate;
import java.util.Date;

class Employee {
    String name;
    String department;
    LocalDate hireDate;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", hireDate='" + hireDate + '\'' +
                '}';
    }
}
class EmployeeBuilder {
    private Employee employee;
    EmployeeBuilder() {
        employee =new Employee();
    }
    EmployeeBuilder(Employee employee) {
        this.employee = employee;
    }
    EmployeeBuilder withName(String name) {
        this.employee.name = name;
        return this;
    }
    EmployeeBuilder withDepartment(String department) {
        this.employee.department = department;
        return this;
    }
    EmployeeBuilder withHireDate(LocalDate hireDate) {
        this.employee.hireDate = hireDate;
        return this;
    }
    Employee build() {
        return employee;
    }
}
public class BuilderClient {
    public static void main(String[] args) {
        EmployeeBuilder builder = new EmployeeBuilder();
        System.out.println(builder.withName("Ahmed").withDepartment("IT").withHireDate(LocalDate.parse("2023-10-10")).build());
    }
}
