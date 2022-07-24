package pl.sda.jobOffer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
//@AllArgsConstructor

public class JobOffer {

    private String title;
    private String company;
    private String location;
    private SalaryRange salaryRange;
//    private String salary;
    private String link;

    public JobOffer(String title, String company, String location,SalaryRange salaryRange, String link) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.salaryRange = salaryRange;
        this.link = "https://nofluffjobs.com" + link;
    }


    @Data
    @Setter
    public static class SalaryRange {
        private double minSalary;
        private double maxSalary;

        public SalaryRange(double minSalary, double maxSalary) {
            this.minSalary = minSalary;
            this.maxSalary = maxSalary;
        }
        public SalaryRange(double minSalary) {
            this.minSalary = minSalary;
        }
        public SalaryRange() {
        }

        @Override
        public String toString() {
            return
                    "minimalne wynagrodzenie:" + minSalary  + "\r\n" +
                    "  maksymalne wynagrodzenie " + maxSalary ;
        }
    }

}
