package com.octawizard.ui.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.octawizard.R
import com.octawizard.domain.model.WeekDay
import com.octawizard.ui.presenter.DietPresenter

internal class DietAdapter(
    private val presenter: DietPresenter
) : RecyclerView.Adapter<DietViewHolder>() {
    //private val superHeroes: MutableList<SuperHero> = ArrayList()
    private val weekDays: MutableList<WeekDay> = ArrayList()

    /*fun addAll(collection: Collection<SuperHero>) {
        superHeroes.addAll(collection)
    }*/

    fun addAll(days: List<WeekDay>) {
        weekDays.addAll(days)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.super_hero_row, parent,
            false
        )
        return DietViewHolder(view, presenter)
    }

    override fun onBindViewHolder(holder: DietViewHolder, position: Int) {
        //val superHero = superHeroes[position]
        val day = weekDays[position]
        holder.render(day)
    }

    override fun getItemCount(): Int {
        //return superHeroes.size
        return weekDays.size
    }

    fun clear() {
        //superHeroes.clear()
        weekDays.clear()
    }
}