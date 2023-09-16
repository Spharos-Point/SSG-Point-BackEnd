package com.spharos.pointapp.coupon.infrastructure;

import com.spharos.pointapp.coupon.domain.Coupon;
import com.spharos.pointapp.coupon.domain.CouponList;
import com.spharos.pointapp.event.domain.EventList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponListRepository extends JpaRepository<CouponList, Long>{

    List<CouponList> findAllByUserId(Long userId);
    List<CouponList> findAllByUserIdAndCouponStatFalse(Long userId);

//    @Query(value = "SELECT * FROM coupon_list c LEFT JOIN user u on c.user.id = u.id where c.coupon_stat = true", nativeQuery = true)
//    List<CouponList> findAllByUserAndCouponStat;
}

//    @Query(value = "select * from coupon_list c Left Join user u on c.user_id = u.id where c.coupon_stat = false", nativeQuery = true)

//    @Query(value = "select * from coupon_list c Left Join User u on c.user_id = u.id where u.uuid= :uuid AND c.coupon_stat = true", nativeQuery = true)
//    List<CouponList> findCouponListByUserAndCouponStat(
//            @Param("uuid")
//            String uuid, Long userId);

