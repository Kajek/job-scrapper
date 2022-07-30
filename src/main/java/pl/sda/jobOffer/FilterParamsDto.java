package pl.sda.jobOffer;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import lombok.*;

import java.util.Locale;

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
