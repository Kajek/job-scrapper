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
//    @Embedded
//    private SalaryRange salaryRange;
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
        this.link = "https://nofluffjobs.com" + link;
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


//    @Data
//    @Setter
//    @Embeddable
//    public static class SalaryRange {
//        private double minSalary;
//        private double maxSalary;
//
//        public SalaryRange(double minSalary, double maxSalary) {
//            this.minSalary = minSalary;
//            this.maxSalary = maxSalary;
//        }
//        public SalaryRange(double minSalary) {
//            this.minSalary = minSalary;
//        }
//        public SalaryRange() {
//        }
//
//        @Override
//        public String toString() {
//            return
//                    "minimalne wynagrodzenie:" + minSalary  + "\r\n" +
//                    "  maksymalne wynagrodzenie " + maxSalary ;
//        }
//    }

}


