package jp.co.ricoh.cotos.commonlib.repository.arrangement;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWorkAttachedFile;

@Repository
public interface ArrangementWorkAttachedFileRepository extends CrudRepository<ArrangementWorkAttachedFile, Long> {
	public List<ArrangementWorkAttachedFile> findByArrangementWorkId(Long arrangementWorkId);

	@Modifying
	@Query(value = "update arrangement_work_attached_file set arrangement_work_id = -:arrangementWorkId, updated_user_id = :updatedUserId, updated_at = sysdate where id = :id", nativeQuery = true)
	public Integer deleteLogicalArrangementWorkAttachedFile(@Param("id") Long id, @Param("arrangementWorkId") Long arrangementWorkId, @Param("updatedUserId") String updatedUserId);
}
