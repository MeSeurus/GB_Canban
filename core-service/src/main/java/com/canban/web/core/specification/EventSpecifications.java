package com.canban.web.core.specification;

import com.canban.web.core.entities.Event;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import java.time.LocalDateTime;

public class EventSpecifications {

    public static Specification<Event> titleLike(String titlePart) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)));
    }

    public static Specification<Event> usernameEqual(String username) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("username"), username));
    }

    public static Specification<Event> endDateLessOrEqualsThen(LocalDateTime endDate) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate));
    }

    public static Specification<Event> endDateGreaterOrEqualsThen(LocalDateTime endDate) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate));
    }

    public static Specification<Event> beginDateGreaterOrEqualsThen(LocalDateTime beginDate) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("beginDate"), beginDate));
    }

    public static Specification<Event> beginDateLessOrEqualsThen(LocalDateTime beginDate) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("beginDate"), beginDate));
    }

    public static Specification<Event> userInUsersAdded(String username) {
        return ((root, query, criteriaBuilder) -> {
            root.fetch("users", JoinType.LEFT);
            return criteriaBuilder.isMember(username, root.get("users"));
        });
    }

}
