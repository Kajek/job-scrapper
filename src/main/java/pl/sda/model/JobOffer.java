package pl.sda.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor

public class JobOffer {

    private String title;
    private String company;
    private String location;
    private String salary;
    private String link;

    public JobOffer(String title, String company, String location,String salary, String link) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.salary = salary;
        this.link = "https://nofluffjobs.com" + link;
    }
}
