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

}
