package com.agenceVoyage.backend.criteriaRepositories.programCq;


import com.agenceVoyage.backend.criteriaRepositories.PageProperties;
import com.agenceVoyage.backend.model.Program;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ProgramRepositoryCq {

    @PersistenceContext
    private EntityManager em;
    private final CriteriaBuilder criteriaBuilder;

    public ProgramRepositoryCq(EntityManager em) {
        this.em = em;
        this.criteriaBuilder = em.getCriteriaBuilder();
    }


    public Page<Program> FindAllWithFilter(
            PageProperties pageProperties,
            ProgramSearchCriteria programSearchCriteria) {



        CriteriaQuery<Program> criteriaQuery = criteriaBuilder.createQuery(Program.class);
        Root<Program> programRoot = criteriaQuery.from(Program.class);
        Predicate predicate = getPredicate(programSearchCriteria, programRoot);
        criteriaQuery.where(predicate);
        setOrder(pageProperties, criteriaQuery, programRoot);
        TypedQuery<Program> typedQuery = em.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pageProperties.getPageNumber() * pageProperties.getPageSize());
        typedQuery.setMaxResults(pageProperties.getPageSize());

        Pageable pageable = getPageable(pageProperties);

        long programsCount = getProgramsCount(programSearchCriteria);


        return new PageImpl<>(typedQuery.getResultList(), pageable, programsCount);





    }




    private Predicate getPredicate(
            ProgramSearchCriteria programSearchCriteria,
            Root<Program> programRoot){

        List<Predicate> predicates = new ArrayList<>();

        if(Objects.nonNull(programSearchCriteria.getName())) {
            predicates.add(criteriaBuilder.like(programRoot.get("name"),"%" + programSearchCriteria.getName() + "%"));
        }

        if(Objects.nonNull(programSearchCriteria.getDescription())) {
            predicates.add(criteriaBuilder.like(programRoot.get("description"),"%" + programSearchCriteria.getDescription() + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


    }

    private void setOrder(PageProperties pageProperties, CriteriaQuery<Program> criteriaQuery, Root<Program> programRoot) {

        if(pageProperties.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(programRoot.get(pageProperties.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(programRoot.get(pageProperties.getSortBy())));
        }
    }

    private Pageable getPageable(PageProperties pageProperties) {
        Sort sort = Sort.by(pageProperties.getSortDirection(), pageProperties.getSortBy());
        return PageRequest.of(pageProperties.getPageNumber(), pageProperties.getPageSize(), sort);
    }

    private long getProgramsCount(
            ProgramSearchCriteria programSearchCriteria) {

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Program> countRoot = countQuery.from(Program.class);
        Predicate countPredicate = getPredicate(programSearchCriteria, countRoot);
        countQuery.select(criteriaBuilder.count(countRoot)).where(countPredicate);

        return em.createQuery(countQuery).getSingleResult();


    }
}
