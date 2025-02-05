package kr.co.mbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.mbc.entity.CateEntity;

public interface CateRepository extends JpaRepository<CateEntity, String> {

	CateEntity findByCname(String cname);

	CateEntity findByCid(String cid);


}
