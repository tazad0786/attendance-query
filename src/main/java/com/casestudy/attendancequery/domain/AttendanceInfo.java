package com.casestudy.attendancequery.domain;

import com.casestudy.attendencecommon.domain.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttendanceInfo {

    @Id
    private Long id;
    private Integer employeeId;
    private String employeeName;
    private LocalTime swipeIn;
    private LocalTime swipeOut;
    private LocalTime totalHours;
    private AttendanceStatus status;
    private LocalDate date;
    private String officeLocation;

}