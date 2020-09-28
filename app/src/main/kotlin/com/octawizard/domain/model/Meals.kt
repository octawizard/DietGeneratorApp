package com.octawizard.domain.model

data class Meal(val protein: Protein, val vegetable: Vegetable, val sideVegetables: Set<Vegetable>, val carb: Carb) {

    companion object {
        val Empty = Meal(Protein(""), Vegetable(""), setOf(), Carb(""))
    }

    override fun toString(): String =
        "Proteins: $protein\tVegetable: $vegetable\tSide Vegetables: $sideVegetables\tCarbohydrates: $carb"
}

data class DailyMeals(val lunch: Meal, val dinner: Meal)

data class WeeklyMeals(val meals: List<Pair<WeekDay, DailyMeals>>) {

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        for ((weekDay, dailyMeals) in meals) {
            stringBuilder.append("***********\n")
            stringBuilder.append("* $weekDay\n")
            stringBuilder.append("*** Lunch:\t${dailyMeals.lunch}\n")
            stringBuilder.append("*** Dinner:\t${dailyMeals.dinner}\n")
            stringBuilder.append("***********\n\n")
        }
        return stringBuilder.toString()
    }
}
