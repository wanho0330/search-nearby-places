package com.wanho.searchnearbyplaces.place;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Place {

    @Id
    @GeneratedValue
    private Long id;

    // 장소 이름
    private String name;

    // 장소 주소
    private String address;

    // 장소 전화번호
    private String tel;

    /*
    // 사업자 고유 아이디
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;

     */

    // 장소 사업자 번호
    private String ownerPlaceNumber;

    // 위도, 경도
    private Double latitude;
    private Double longitude;

    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime updateAt;


}
