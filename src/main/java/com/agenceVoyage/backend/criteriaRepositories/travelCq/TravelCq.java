package com.agenceVoyage.backend.criteriaRepositories.travelCq;


import com.agenceVoyage.backend.criteriaRepositories.PageProperties;
import com.agenceVoyage.backend.model.Program;
import com.agenceVoyage.backend.model.Travel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Repository
public class TravelCq {

    @Autowired
    private EntityManager em;
    private CriteriaBuilder criteriaBuilder;

    public TravelCq(EntityManager em) {
        this.criteriaBuilder = em.getCriteriaBuilder();
    }



    public Page<Travel> findAllWithFilter(
            PageProperties pageProperties,
            TravelSearchCriteria travelSearchCriteria){

        CriteriaQuery<Travel> criteriaQuery = criteriaBuilder.createQuery(Travel.class);
        Root<Travel> travelRoot = criteriaQuery.from(Travel.class);
        Predicate predicate = getPredicate(travelSearchCriteria, travelRoot);
        criteriaQuery.where(predicate);
        setOrder(pageProperties, criteriaQuery, travelRoot);
        TypedQuery<Travel> typedQuery = em.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pageProperties.getPageNumber() * pageProperties.getPageSize());
        typedQuery.setMaxResults(pageProperties.getPageSize());

        Pageable pageable = getPageable(pageProperties);

        Long programCounts = getProgramsCount(travelSearchCriteria);

        return new PageImpl<>(typedQuery.getResultList(), pageable, programCounts);

    }



    public Predicate getPredicate(
            TravelSearchCriteria travelSearchCriteria,
            Root<Travel> travelRoot) {

        List<Predicate> predicates = new ArrayList<>();

        if(Objects.nonNull(travelSearchCriteria.getName())){
            predicates.add(
                    criteriaBuilder.like(travelRoot.get("name"), "%" + travelSearchCriteria.getName() + "%")
            );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


    private void setOrder(PageProperties pageProperties, CriteriaQuery<Travel> criteriaQuery, Root<Travel> travelRoot) {
        if(pageProperties.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(travelRoot.get(pageProperties.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(travelRoot.get(pageProperties.getSortBy())));
        }

    }


    private Pageable getPageable(PageProperties pageProperties) {
        Sort sort = Sort.by(pageProperties.getSortDirection(), pageProperties.getSortBy());
        return PageRequest.of(pageProperties.getPageNumber(), pageProperties.getPageSize(), sort);
    }

    private Long getProgramsCount(TravelSearchCriteria travelSearchCriteria) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Travel> countRoot = countQuery.from(Travel.class);
        Predicate countPredicate = getPredicate(travelSearchCriteria, countRoot);
        countQuery.select(criteriaBuilder.count(countRoot)).where(countPredicate);

        return em.createQuery(countQuery).getSingleResult();
    }




}
