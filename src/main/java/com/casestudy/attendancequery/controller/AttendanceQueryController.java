package com.casestudy.attendancequery.controller;

import com.casestudy.attendancequery.domain.AttendanceInfo;
import com.casestudy.attendancequery.service.AttendanceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class AttendanceQueryController {

    @Autowired
    AttendanceQueryService attendanceQueryService;

    @QueryMapping("allAttendances")
    public Flux<AttendanceInfo> findAll() {
        return attendanceQueryService.findAll();
    }

    @QueryMapping("getAttendance")
    public Mono<AttendanceInfo> findById(@Argument long attendanceInfoId) {
        return attendanceQueryService.findById(attendanceInfoId);
    }
}
