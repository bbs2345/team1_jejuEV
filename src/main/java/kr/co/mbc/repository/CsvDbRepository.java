package kr.co.mbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.mbc.entity.CsvDataEntity;

public interface CsvDbRepository extends JpaRepository<CsvDataEntity, Long> {

}
