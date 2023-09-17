package com.spharos.pointapp.userpoint.pointList.specification;

import com.spharos.pointapp.point.domain.PointType;
import com.spharos.pointapp.userpoint.pointList.domain.UserPointList;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class UserPointListSpecification {

    public static Specification<UserPointList> withPointType(PointType pointType) {
        if (pointType == null) {
            return (root, query, builder) -> builder.conjunction();
        }
        return (root, query, builder) -> builder.equal(root.get("pointType"), pointType);
    }

    public static Specification<UserPointList> withUuid(String uuid) {
        if (uuid == null) {
            return (root, query, builder) -> builder.conjunction();
        }
        return (root, query, builder) -> builder.equal(root.get("uuid"), uuid);
    }

    public static Specification<UserPointList> withCreateAt(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null && endDate == null) {
            return (root, query, builder) -> builder.conjunction();
        }
        if (startDate == null) {
            return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("createAt"), endDate);
        }
        if (endDate == null) {
            return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("createAt"), startDate);
        }
        return (root, query, builder) -> builder.between(root.get("createAt"), startDate, endDate);
    }
}
