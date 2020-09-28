package com.octawizard.data.repository

import com.octawizard.domain.model.*
import com.octawizard.domain.model.WeekDay.*

class DietRepository {
    private val proteins: Set<Protein> = setOfProtein(
            "chicken",
            "tuna",
            "shrimps",
            "eggs",
            "beans",
            "chickpeas",
            "lentil",
            "cottage cheese",
            "clams",
            "cod",
            "salmon"
    )
    private val vegetables = setOfVegetable(
            "broccoli",
            "spinach",
            "beetroot",
            "cauliflower",
            "eggplant",
            "zucchini",
            "red pepper",
            "green beans",
            "edamame",
            "cabbage",
            "pumpkin"
    )
    private val sideVegetables = setOfVegetable(
            "cucumber",
            "tomato",
            "carrot",
            "rucola",
            "lettuce",
            "canon",
            "dried tomato"
    )
    private val carbs =
            setOfCarb("pasta", "brown rice", "brown bread", "potato", "sweet potato", "quinoa")

    private val weeklyMeals:  WeeklyMeals
    init {
        weeklyMeals = getWeeklyMeals()
    }

    fun getDailyMeal(weekDay: WeekDay): DailyMeals? =
            weeklyMeals.meals.find { it.first == weekDay }?.second

    private fun generateMeal(): Meal =
            Meal(proteins.random(), vegetables.random(), sideVegetables.random(3), carbs.random())

    private fun getWeeklyMeals(): WeeklyMeals {
        val pairs = WeekDays.zip(
                List(Sunday.dayNumber) {
                    DailyMeals(lunch = generateMeal(), dinner = generateMeal())
                }
        )
        return WeeklyMeals(pairs)
    }

    fun getWeeklyDiet(): WeeklyMeals = weeklyMeals

}

fun <T : Any> Set<T>.random(n: Int): Set<T> =
        generateSequence { this.random() }
                .take(n)
                .toSet()

