package com.octawizard.domain.usecase

import com.octawizard.data.repository.DietRepository
import com.octawizard.domain.model.DailyMeals
import com.octawizard.domain.model.WeekDay

class GetDailyDiet(private val dietRepository: DietRepository) {

    operator fun invoke(day: WeekDay): DailyMeals? = dietRepository.getDailyMeal(day)
}