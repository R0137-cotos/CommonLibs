package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.MvTJmcj005Master;

@Repository
public interface MvTJmcj005MasterRepository extends JpaRepository<MvTJmcj005Master, String> {
	public MvTJmcj005Master findByHanshCdAndRingsTkiskCdAndRingsTodokesakiCd(String hanshCd, String ringsTkiskCd, String ringsTodokesakiCd);

	public List<MvTJmcj005Master> findByHanshCdAndRingsTkiskCd(String hanshCd, String ringsTkiskCd);

	public MvTJmcj005Master findByOeTodokesakiCdAndRingsTodokesakiCdIsNotNull(String oeTodokesakiCd);

	public List<MvTJmcj005Master> findByOeTodokesakiCd(String oeTodokesakiCd);

	public List<MvTJmcj005Master> findByOriginalSystemCode(String originalSystemCode);
}