package com.wanho.searchnearbyplaces.place;

import com.wanho.searchnearbyplaces.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findByUserOrderByIdDesc(User user);
    Place findByUserAndId(User user, Long id);
}
