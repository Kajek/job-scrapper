package pl.sda.jobOffer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@NoArgsConstructor

@AllArgsConstructor

public class JobOffer {
    @Id
    private Long id;

    private String title;
    private String company;
    private String location;
    private double minSalary;
    private double maxSalary;
    private String link;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobOffer(String title, String company, String location,Double minSalary, Double maxSalary, String link) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.link = link;
    }

    public static JobOffer from(JobOfferEntity entity){
        return new JobOffer(entity.getId(),
                entity.getTitle(),
                entity.getCompany(),
                entity.getLocation(),
                entity.getMinSalary(),
                entity.getMaxSalary(),
                entity.getLink());
    }
}


