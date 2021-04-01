package jp.co.ricoh.cotos.commonlib.repository.estimation;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationAttachedFile;

@Repository
public interface EstimationAttachedFileRepository extends CrudRepository<EstimationAttachedFile, Long> {
	public List<EstimationAttachedFile> findByEstimationId(Long estimationId);

	@Modifying
	@Query(value = "update estimation_attached_file set estimation_id = :estimationId, updated_at = sysdate where id = :id", nativeQuery = true)
	public Integer updateEstimationAttachedFile(@Param("id") Long id, @Param("estimationId") Long estimationId);
}
