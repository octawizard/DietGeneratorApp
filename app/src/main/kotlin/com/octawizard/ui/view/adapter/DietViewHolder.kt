package com.octawizard.ui.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.octawizard.R
import com.octawizard.domain.model.WeekDay
import com.octawizard.ui.presenter.DietPresenter
import com.octawizard.ui.utils.setImageBackground

class DietViewHolder(
    itemView: View,
    private val presenter: DietPresenter
) : RecyclerView.ViewHolder(itemView) {

    private val photoImageView: ImageView = itemView.findViewById(R.id.iv_day_photo)
    private val nameTextView: TextView = itemView.findViewById(R.id.tv_week_day_name)

    fun render(weekDay: WeekDay) {
        hookListeners(weekDay)
        //renderSuperHeroPhoto(superHero.photo)
        renderSuperHeroPhoto(weekDay.photo)
        //renderSuperHeroName(superHero.name)
        renderWeekDay(weekDay.name)
        //renderAvengersBadge(superHero.isAvenger)
    }

    private fun hookListeners(weekDay: WeekDay) {
        itemView.setOnClickListener { presenter.onWeekDayClicked(weekDay) }
    }

    private fun renderSuperHeroPhoto(photo: String?) {
        photoImageView.setImageBackground(photo)
    }

    private fun renderWeekDay(name: String) {
        nameTextView.text = name
    }
}