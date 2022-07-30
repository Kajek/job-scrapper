package pl.sda.jobOffer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface JobOfferRepository extends JpaRepository<JobOfferEntity, Long>, QuerydslPredicateExecutor<JobOfferEntity>  {

    List<JobOfferEntity> findByMinSalaryGreaterThanEqualAndMaxSalaryLessThanEqual(Double minSalary, Double maxSalary);
}
