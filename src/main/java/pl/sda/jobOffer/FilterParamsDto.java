package pl.sda.jobOffer;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FilterParamsDto {

    public String location;
    Double  minSalary;
    Double  maxSalary;

    public String locationInfo(){
        return "location=" + location + "minSalary=" + minSalary.toString() + "maxSalary="+ maxSalary.toString();
    }
}
