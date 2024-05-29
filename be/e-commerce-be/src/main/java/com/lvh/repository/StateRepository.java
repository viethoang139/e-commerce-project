package com.lvh.repository;

import com.lvh.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Integer> {

    List<State> findStateByCountryId(Integer countryId);

    List<State> findStateByCountryCode(String code);
}
