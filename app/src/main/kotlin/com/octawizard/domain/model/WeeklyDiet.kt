package com.octawizard.domain.model

class WeeklyDietGenerator(
    private val proteins: Set<Protein>,
    private val vegetables: Set<Vegetable>,
    private val sideVegetables: Set<Vegetable>,
    private val carbs: Set<Carb>
) {

    fun generate(): WeeklyMeals {
        val pairs =
            WeekDays.zip(
                List(WeekDay.Sunday.dayNumber) {
                    DailyMeals(lunch = generateMeal(), dinner = generateMeal())
                }
            )
        return WeeklyMeals(pairs)
    }

    private fun generateMeal(): Meal =
        Meal(proteins.random(), vegetables.random(), sideVegetables.random(3), carbs.random())

}

fun <T: Any> Set<T>.random(n: Int): Set<T> =
    generateSequence { this.random() }
        .take(n)
        .toSet()
