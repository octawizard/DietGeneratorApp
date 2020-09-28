package com.octawizard.ui.presenter

import android.arch.lifecycle.Lifecycle.Event.ON_DESTROY
import android.arch.lifecycle.Lifecycle.Event.ON_RESUME
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.octawizard.common.async
import com.octawizard.common.weak
import com.octawizard.domain.model.*
import com.octawizard.domain.usecase.GetDailyDiet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DietDetailPresenter(
    view: View,
    private val getDailyDiet: GetDailyDiet
) : LifecycleObserver, CoroutineScope by MainScope() {

    private val view: View? by weak(view)

    private lateinit var day: WeekDay

    fun preparePresenter(weekDay: WeekDay) {
        this.day = weekDay
    }

    @OnLifecycleEvent(ON_RESUME)
    fun update() {
        view?.showLoading()
        refreshDiet()
    }

    @OnLifecycleEvent(ON_DESTROY)
    fun destroy() {
        cancel()
    }

    private fun refreshDiet() = launch {
        val dailyMeals = async { getDailyDiet(day) ?: DailyMeals(Meal.Empty, Meal.Empty) }
        view?.hideLoading()
        view?.showDailyMeals(day)
        view?.showDiet(dailyMeals)
    }

    interface View {
        fun close()
        fun showLoading()
        fun hideLoading()
        fun showDailyMeals(weekDay: WeekDay)
        fun showDiet(dailyMeals: DailyMeals)
    }
}