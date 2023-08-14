package com.example.memberjoin.repository;

import com.example.memberjoin.entity.MemberEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface MemberRepository extends CrudRepository<MemberEntity,Long> {
    @Override
    ArrayList<MemberEntity> findAll();
}
