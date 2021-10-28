package com.golovin.hospital.repository.specification;

import com.golovin.hospital.dao.entity.MedicalStaff;
import com.golovin.hospital.dao.entity.Role;
import com.golovin.hospital.dto.filter.MedicalFilterDto;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class MedicalSpecification {

    public static Specification<MedicalStaff> findMedicals(Collection<MedicalFilterDto> filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();

            Fetch<MedicalStaff, Role> rolesFetch = root.fetch("roles", JoinType.LEFT);
            Join<MedicalStaff, Role> rolesJoin = (Join<MedicalStaff, Role>) rolesFetch;

            for (var filter : filters) {
                switch (filter.getMedicalsFields()) {
                    case ID -> {
                        Set<Long> ids = filter.getValues().stream().map(Long::parseLong).collect(Collectors.toSet());
                        predicates.add(criteriaBuilder.in(root.get("id")).value(ids));
                    }

                    case NAME -> predicates.add(criteriaBuilder.in(root.get("name")).value(filter.getValues()));

                    case ROLES -> {
                        predicates.add(criteriaBuilder.in(rolesJoin.get("code")).value(filter.getValues()));
                    }

                    default -> throw new IllegalArgumentException();
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
