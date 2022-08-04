package com.canban.web.core.specification;

import com.canban.api.core.Priority;
import com.canban.api.core.State;
import com.canban.web.core.entities.Event;
import com.canban.web.core.entities.Task;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskSpecification {

    public static Specification<Task> boardIdEqual(Long id) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("kanbanBoardId"), id));
    }

    public static Specification<Task> titleLike(String titlePart) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)));
    }

    public static Specification<Task> usernameCreatorEqual(String username) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userCreator"), username));
    }

    public static Specification<Task> usernameExecutorEqual(String username) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userExecutor"), username));
    }

    public static Specification<Task> endDateLessOrEqualsThen(LocalDateTime endDate) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate));
    }

    public static Specification<Task> endDateGreaterOrEqualsThen(LocalDateTime endDate) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate));
    }

    public static Specification<Task> beginDateGreaterOrEqualsThen(LocalDateTime beginDate) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("beginDate"), beginDate));
    }

    public static Specification<Task> beginDateLessOrEqualsThen(LocalDateTime beginDate) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("beginDate"), beginDate));
    }

    public static Specification<Task> actualEndDateGreaterOrEqualsThen(LocalDateTime actualEndDate) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("actualEndDate"), actualEndDate));
    }

    public static Specification<Task> actualEndDateLessOrEqualsThen(LocalDateTime actualEndDate) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("actualEndDate"), actualEndDate));
    }

    public static Specification<Task> stateEquals(State title) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("state"), title));
    }

    public static Specification<Task> priorityEquals(Priority title) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("priority"), title));
    }



}
