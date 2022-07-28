package pl.sda.jobOffer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Table(name="job_offer")
@AllArgsConstructor
@NoArgsConstructor
public class JobOfferEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private String location;
    private Double minSalary;
    private Double maxSalary;    
    private String link;

    public JobOfferEntity(String title, String company, String location, Double minSalary, Double maxSalary, String link) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static JobOfferEntity from(JobOffer jobOffer){
        return new JobOfferEntity(jobOffer.getId(),
                jobOffer.getTitle(),
                jobOffer.getCompany(),
                jobOffer.getLocation(),
                jobOffer.getMinSalary(),
                jobOffer.getMaxSalary(),
                jobOffer.getLink());
    }


}
