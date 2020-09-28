package com.octawizard.domain.usecase

import com.octawizard.data.repository.DayRepository
import com.octawizard.domain.model.WeekDay

class GetDaysOfWeek(private val dayRepository: DayRepository) {

    operator fun invoke(): List<WeekDay> = dayRepository.getDaysOfWeek()
}