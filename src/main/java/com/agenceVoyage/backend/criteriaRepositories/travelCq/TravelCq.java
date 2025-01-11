package com.agenceVoyage.backend.criteriaRepositories.travelCq;


import com.agenceVoyage.backend.criteriaRepositories.PageProperties;
import com.agenceVoyage.backend.dto.TravelDto;
import com.agenceVoyage.backend.model.Program;
import com.agenceVoyage.backend.model.Travel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Repository
public class TravelCq {

    @Autowired
    private EntityManager em;
    private CriteriaBuilder criteriaBuilder;
    @Autowired
    private ModelMapper modelMapper;

    public TravelCq(EntityManager em) {
        this.criteriaBuilder = em.getCriteriaBuilder();
    }



    public Page<TravelDto> findAllWithFilter(
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

        Long travelsCount = getTravelsCount(travelSearchCriteria);

        List<TravelDto> travelDtoList = typedQuery.getResultList().stream()
                .map(
                        travel -> modelMapper.map(travel, TravelDto.class)
                )
                .toList();

        return new PageImpl<>(travelDtoList, pageable, travelsCount);

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

        if(travelSearchCriteria.getDuration() != 0) {
            predicates.add(
                    criteriaBuilder.equal(travelRoot.get("duration"), travelSearchCriteria.getDuration())
            );
        }

        if(travelSearchCriteria.getTravelers() != 0) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(travelRoot.get("placesLeft"), travelSearchCriteria.getTravelers())
            );

        }

        if(Objects.nonNull(travelSearchCriteria.getDestination())){
            Join<Travel, Program> programJoin = travelRoot.join("programs");
            predicates.add(
                    criteriaBuilder.like(programJoin.get("destination"), "%" + travelSearchCriteria.getDestination() + "%")
            );
        }

        if(!travelSearchCriteria.getType().isEmpty()) {

            predicates.add(
                    criteriaBuilder.equal(travelRoot.get("type"), travelSearchCriteria.getType())
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

    private Long getTravelsCount(TravelSearchCriteria travelSearchCriteria) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Travel> countRoot = countQuery.from(Travel.class);
        Predicate countPredicate = getPredicate(travelSearchCriteria, countRoot);
        countQuery.select(criteriaBuilder.countDistinct(countRoot)).where(countPredicate);

        return em.createQuery(countQuery).getSingleResult();
    }




}
