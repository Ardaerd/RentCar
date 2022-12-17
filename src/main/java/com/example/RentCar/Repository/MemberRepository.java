package com.example.RentCar.Repository;

import com.example.RentCar.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    @Query(value = "SELECT * FROM MEMBER M WHERE M.ID  = :id",nativeQuery = true)
    Optional<Member> findById(@Param("id") Long id);
}
