package com.kyosaka.kintaisan.service;

import com.kyosaka.kintaisan.dto.AttendanceStatusDto;
import com.kyosaka.kintaisan.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class AttendanceStatusService {

    private final AttendanceRepository repository;

    public AttendanceStatusService(AttendanceRepository repository) {
        this.repository = repository;
    }

    public List<AttendanceStatusDto> getTodayAttendanceStatus() {
        return repository.findTodayStatusRaw(LocalDate.now())
            .stream()
            .map(r -> new AttendanceStatusDto(
                (String) r[0],
                (String) r[1],
                (String) r[2],
                (String) r[3],
                (String) r[4],
                r[5] == null ? null : ((Instant) r[5]).atZone(ZoneId.of("Asia/Tokyo")),
                r[6] == null ? null : ((Instant) r[6]).atZone(ZoneId.of("Asia/Tokyo"))
            )
        )
        .toList();
    }
}
