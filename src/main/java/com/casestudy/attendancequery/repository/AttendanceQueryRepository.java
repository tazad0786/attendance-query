package com.casestudy.attendancequery.repository;

import com.casestudy.attendancequery.domain.AttendanceInfo;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface AttendanceQueryRepository extends R2dbcRepository<AttendanceInfo, Long> {

}
