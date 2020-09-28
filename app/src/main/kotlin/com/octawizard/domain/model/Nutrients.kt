package com.octawizard.domain.model
/**
 * Created by Roberto Manca on 19/09/2020.
 */

sealed class Nutrient(private val name: String) {
    override fun toString(): String = name
}

data class Protein(private val name: String) : Nutrient(name) {
    override fun toString(): String = super.toString()
}

data class Vegetable(private val name: String) : Nutrient(name) {
    override fun toString(): String = super.toString()
}


data class Carb(private val name: String) : Nutrient(name) {
    override fun toString(): String = super.toString()
}


fun setOfProtein(vararg elements: String): Set<Protein> = setOfNutrient(elements) { Protein(it) }
fun setOfCarb(vararg elements: String): Set<Carb> = setOfNutrient(elements) { Carb(it) }
fun setOfVegetable(vararg elements: String): Set<Vegetable> = setOfNutrient(elements) { Vegetable(it) }

fun <N : Nutrient> setOfNutrient(elements: Array<out String>, mapper: (String) -> N): Set<N> =
  elements.map(mapper).toSet()

