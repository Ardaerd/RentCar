package com.example.RentCar.Service;

import com.example.RentCar.DTO.MemberDTO;
import com.example.RentCar.Mapper.MemberMapper;
import com.example.RentCar.Model.Member;
import com.example.RentCar.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberMapper memberMapper;

    public MemberDTO save(MemberDTO dto) {
        Member member = memberMapper.memberDTOToEntity(dto);
        memberRepository.save(member);

        return memberMapper.memberEntityToDTO(member);
    }

}
