package jp.co.ricoh.cotos.commonlib.repository.communication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.communication.BounceMailDestination;

@Repository
public interface BounceMailDestinationRepository extends CrudRepository<BounceMailDestination, Long> {

}
