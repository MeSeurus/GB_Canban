package com.canban.web.core.mapper;

import com.canban.api.core.DayDto;
import com.canban.web.core.entities.Day;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DayMapper {

    private final EventMapper eventMapper;

    public Day dtoToEntity(DayDto dayDto) {
        return new Day(dayDto.getId(), dayDto.getDate(), dayDto.getWeekday());
    }

    public DayDto entityToDto(Day day) {
        return new DayDto(day.getId(), day.getDate(), day.getWeekday());
    }


}
