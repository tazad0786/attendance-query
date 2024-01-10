package com.casestudy.attendancequery.service;

import com.casestudy.attendancequery.domain.AttendanceInfo;
import com.casestudy.attendancequery.repository.AttendanceQueryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AttendanceQueryService {

    private final AttendanceQueryRepository attendanceQueryRepository;

    public AttendanceQueryService(AttendanceQueryRepository attendanceQueryRepository) {
        this.attendanceQueryRepository = attendanceQueryRepository;
    }

    public Flux<AttendanceInfo> findAll() {
        return attendanceQueryRepository.findAll();
    }

    public Mono<AttendanceInfo> findById(Long id) {
        return attendanceQueryRepository.findById(id);
    }
}
