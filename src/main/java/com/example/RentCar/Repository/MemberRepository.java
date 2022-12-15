package com.example.RentCar.Repository;

import com.example.RentCar.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Float> {
}
