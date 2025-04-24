package com.infodart.kenstar_crm.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infodart.kenstar_crm.entity.Holiday;
import com.infodart.kenstar_crm.entity.Role;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
	//boolean existsByDate(LocalDate date);
	
	//@Query("SELECT r FROM Role r WHERE r.name = :name")
	@Query("SELECT h FROM Holiday h WHERE h.date = :date")
	Optional<Holiday> findByDate(@Param("date") LocalDate date);

	List<Holiday> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

}
