package pl.sda.jobOffer;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobOfferRepository extends JpaRepository<JobOfferEntity, Long>, QuerydslPredicateExecutor<JobOfferEntity>  {


}
