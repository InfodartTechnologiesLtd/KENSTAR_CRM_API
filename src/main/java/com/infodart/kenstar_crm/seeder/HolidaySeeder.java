package com.infodart.kenstar_crm.seeder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.infodart.kenstar_crm.entity.Holiday;
import com.infodart.kenstar_crm.repository.HolidayRepository;

import jakarta.annotation.PostConstruct;

@Component
public class HolidaySeeder {

    @Autowired
    private HolidayRepository holidayRepository;

    @PostConstruct
    public void seedHolidays() {
        // Check if holidays already exist to avoid duplication
        if (holidayRepository.count() > 0) {
            return;
        }

        List<Holiday> holidays = Arrays.asList(
                new Holiday("New Year's Day", LocalDate.of(2025, 1, 1), "", true),
                new Holiday("Independence Day", LocalDate.of(2025, 8, 15), "", true),
                new Holiday("Republic Day", LocalDate.of(2025, 1, 26), "", true),
                new Holiday("Christmas", LocalDate.of(2025, 12, 25), "", true)
                // Add other holidays here
        );

        holidayRepository.saveAll(holidays);
    }
}
