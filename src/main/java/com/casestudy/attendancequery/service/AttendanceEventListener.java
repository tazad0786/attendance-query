package com.casestudy.attendancequery.service;

import com.casestudy.attendancequery.domain.AttendanceInfo;
import com.casestudy.attendancequery.repository.AttendanceQueryRepository;
import com.casestudy.attendencecommon.domain.AttendanceStatus;
import com.casestudy.attendencecommon.domain.DailyAttendanceEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalTime;

@Service
@Slf4j
public class AttendanceEventListener {

    private final ObjectMapper objectMapper;
    private final AttendanceQueryRepository attendanceQueryRepository;

    public AttendanceEventListener(
            AttendanceQueryRepository attendanceQueryRepository,
            ObjectMapper objectMapper) {
        this.attendanceQueryRepository = attendanceQueryRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${daily.attendance.topic}")
    public void consume(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {
        log.info("Message received: key --> {} | Value --> {}", consumerRecord.key(), consumerRecord.value());
        DailyAttendanceEvent attendance = objectMapper.readValue(consumerRecord.value(), DailyAttendanceEvent.class);
        AttendanceInfo info = getAttendanceInfo(attendance);
        Mono<AttendanceInfo> savedInfo = attendanceQueryRepository.save(info).log();
        savedInfo.subscribe(i -> {
            log.info("Attendance Info saved --> {}", i);
        });
    }

    private static AttendanceInfo getAttendanceInfo(DailyAttendanceEvent attendance) {
        AttendanceInfo info = new AttendanceInfo();
        info.setEmployeeId(attendance.getEmployeeId());
        info.setEmployeeName(attendance.getEmployeeName());
        info.setDate(attendance.getDate());
        info.setSwipeIn(LocalTime.from(attendance.getSwipeInTime()));
        info.setSwipeOut(LocalTime.from(attendance.getSwipeOutTime()));
        info.setTotalHours(LocalTime.from(attendance.getTotalHours()));
        info.setStatus(AttendanceStatus.valueOf(attendance.getAttendanceStatus().name()));
        info.setOfficeLocation(attendance.getOfficeLocation());
        return info;
    }
}
