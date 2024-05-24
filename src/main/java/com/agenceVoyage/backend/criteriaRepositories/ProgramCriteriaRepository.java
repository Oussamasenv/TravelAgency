//package com.agenceVoyage.backend.criteriaRepositories;
//
//
//import com.agenceVoyage.backend.model.Program;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.*;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//@Repository
//public class ProgramCriteriaRepository {
//
//    @Autowired
//    private final EntityManager entityManager;
//    private final CriteriaBuilder criteriaBuilder;
//
//    public ProgramCriteriaRepository(EntityManager entityManager) {
//        this.entityManager = entityManager;
//        this.criteriaBuilder = entityManager.getCriteriaBuilder();
//    }
//
//    public Page<Program> FindAllWithFilter(
//            ProgramPage programPage,
//            ProgramSearchCriteria programSearchCriteria) {
//
//        CriteriaQuery<Program> criteriaQuery = criteriaBuilder.createQuery(Program.class);
//        Root<Program> programRoot = criteriaQuery.from(Program.class);
//        Predicate predicate = getPredicate(programSearchCriteria, programRoot);
//        criteriaQuery.where(predicate);
//        setOrder(programPage, criteriaQuery, programRoot);
//        TypedQuery<Program> typedQuery = entityManager.createQuery(criteriaQuery);
//        typedQuery.setFirstResult(programPage.getPageNumber() * programPage.getPageSize());
//        typedQuery.setMaxResults(programPage.getPageSize());
//
//        Pageable pageable = getPageable(programPage);
//
//        long programsCount = getProgramsCount(predicate);
//
//
//        return new PageImpl<>(typedQuery.getResultList(), pageable, programsCount);
//
//
//
//
//
//    }
//
//
//
//
//    private Predicate getPredicate(
//            ProgramSearchCriteria programSearchCriteria,
//            Root<Program> programRoot){
//
//        List<Predicate> predicates = new ArrayList<>();
//
//        if(Objects.nonNull(programSearchCriteria.getName())) {
//            predicates.add(criteriaBuilder.like(programRoot.get("name"),"%" + programSearchCriteria.getName() + "%"));
//        }
//
//        if(Objects.nonNull(programSearchCriteria.getName())) {
//            predicates.add(criteriaBuilder.like(programRoot.get("description"),"%" + programSearchCriteria.getDescription() + "%"));
//        }
//
//        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//
//
//    }
//
//    private void setOrder(ProgramPage programPage, CriteriaQuery<Program> criteriaQuery, Root<Program> programRoot) {
//
//        if(programPage.getSortDirection().equals(Sort.Direction.ASC)){
//            criteriaQuery.orderBy(criteriaBuilder.asc(programRoot.get(programPage.getSortBy())));
//        } else {
//            criteriaQuery.orderBy(criteriaBuilder.desc(programRoot.get(programPage.getSortBy())));
//        }
//    }
//
//    private Pageable getPageable(ProgramPage programPage) {
//        Sort sort = Sort.by(programPage.getSortDirection(), programPage.getSortBy());
//        return PageRequest.of(programPage.getPageNumber(), programPage.getPageSize(), sort);
//    }
//
//    private long getProgramsCount(Predicate predicate) {
//
//        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
//        Root<Program> countRoot = countQuery.from(Program.class);
//        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
//
//        return entityManager.createQuery(countQuery).getSingleResult();
//
//
//    }
//}
