package com.example.memberjoin.dto;

import com.example.memberjoin.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String email;
    private String password;

    public MemberEntity toEntity() {
        return new MemberEntity(null,email,password);
    }
    public MemberEntity upEntity() {
        return new MemberEntity(id,email,password);
    }
}
