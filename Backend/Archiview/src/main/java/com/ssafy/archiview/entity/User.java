package com.ssafy.archiview.entity;

import com.ssafy.archiview.dto.user.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.domain.Persistable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements Persistable<String> {
    @Id
    @Column(name = "id", length = 16)
    private String id;

    @NotNull
    @Column(name = "pw", length = 64)
    private String pw;

    @NotNull
    @Column(name = "name", length = 32)
    private String name;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(name = "introduce", columnDefinition = "TEXT")
    private String introduce;

    @Column(name = "role")
    @ColumnDefault("'USER'")
    @Enumerated(EnumType.STRING)
    private Role role;  // 권한

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;  // 생성 날짜

    @Column(name = "refresh_token")
    private String refreshToken;
    @Builder
    public User(String id, String pw, String name, String email, String profileUrl, String introduce, Role role, LocalDateTime createdAt, String refreshToken) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.profileUrl = profileUrl;
        this.introduce = introduce;
        this.role = role;
        this.createdAt = createdAt;
        this.refreshToken = refreshToken;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


    @Override
    public boolean isNew() {
        return this.createdAt == null;
    }

    public UserDto.loginResponseDto toLoginResponseDto(){
        return UserDto.loginResponseDto.builder()
                .id(id)
                .email(email)
                .name(name)
                .introduce(introduce)
                .profileUrl(profileUrl)
                .role(role)
                .refreshToken(refreshToken)
                .build();
    }

    public UserDto.DetailResponseDto toDetailResponseDto() {
        return UserDto.DetailResponseDto.builder()
                .id(id)
                .email(email)
                .name(name)
                .introduce(introduce)
                .profileUrl(profileUrl)
                .role(role)
                .build();
    }
}
