package com.cinema.cinema.screening.services;

import com.cinema.cinema.screening.dto.ScreeningOutputDto;
import com.cinema.cinema.screening.exceptions.ScreeningNotFoundException;
import com.cinema.cinema.screening.mappers.ScreeningMapper;
import com.cinema.cinema.screening.repository.ScreeningRepository;
import com.cinema.cinema.screening.models.Screening;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ScreeningService {
    private final ScreeningRepository screeningRepository;
    private final ScreeningMapper screeningMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public ScreeningService(ScreeningRepository screeningRepository, ScreeningMapper screeningMapper) {
        this.screeningRepository = screeningRepository;
        this.screeningMapper = screeningMapper;
    }

    public List<ScreeningOutputDto> getFilteredScreenings(
            LocalDate date,
            String movieTitle,
            String genre,
            Integer numberOfActors,
            Boolean hasTrailer,
            Pageable pageable
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Screening> query = cb.createQuery(Screening.class);
        Root<Screening> root = query.from(Screening.class);
        Predicate predicate = cb.conjunction();

        if (!Objects.isNull(date)) {
            predicate = cb.and(predicate, cb.equal(root.get("date"), date));
        }
        if (!Objects.isNull(movieTitle)) {
            predicate = cb.and(predicate, cb.like(root.get("movie").get("title"), "%" + movieTitle + "%"));
        }
        if (!Objects.isNull(genre)) {
            predicate = cb.and(predicate, cb.like(root.get("movie").get("genre"), genre));
        }
        if (!Objects.isNull(numberOfActors)) {
            predicate = cb.and(predicate, cb.equal(cb.size(root.get("movie").get("actors")), numberOfActors));
        }
        if (!Objects.isNull(hasTrailer)) {
            System.out.println(hasTrailer);
            if (hasTrailer) {
                predicate = cb.and(predicate, cb.isNotNull(root.get("movie").get("trailerUrl")));
            } else {
                predicate = cb.and(predicate, cb.isNull(root.get("movie").get("trailerUrl")));
            }
        }

        query.where(predicate);

        if (pageable.getSort().isSorted()) {
            List<Order> orders = new ArrayList<>();
            for (Sort.Order sortOrder : pageable.getSort()) {
                String property = sortOrder.getProperty();
                if (sortOrder.isAscending()) {
                    orders.add(cb.asc(root.get(property)));
                } else {
                    orders.add(cb.desc(root.get(property)));
                }
            }
            query.orderBy(orders);
        }

        TypedQuery<Screening> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        return screeningMapper.screeningsToScreeningOutputDtos(typedQuery.getResultList());
    }

    public ScreeningOutputDto getScreeningById(Integer id) {
        Screening screening = getScreeningEntityById(id);
        return screeningMapper.screeningToScreeningOutputDto(screening);
    }

    public Screening getScreeningEntityById(Integer id) {
        return screeningRepository.findById(id).orElseThrow(() -> new ScreeningNotFoundException(id));
    }
}
