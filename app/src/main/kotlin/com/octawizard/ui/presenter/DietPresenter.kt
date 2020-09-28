package com.octawizard.ui.presenter

import android.arch.lifecycle.Lifecycle.Event.ON_DESTROY
import android.arch.lifecycle.Lifecycle.Event.ON_RESUME
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.octawizard.common.async
import com.octawizard.common.weak
import com.octawizard.domain.model.WeekDay
import com.octawizard.domain.usecase.GetDaysOfWeek
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DietPresenter(
    view: View,
    private val getDaysOfWeek: GetDaysOfWeek
) : LifecycleObserver, CoroutineScope by MainScope() {

    private val view: View? by weak(view)

    @OnLifecycleEvent(ON_RESUME)
    fun update() {
        view?.showLoading()
        refreshSuperHeroes()
    }

    @OnLifecycleEvent(ON_DESTROY)
    fun destroy() {
        cancel()
    }

    private fun refreshSuperHeroes() = launch {
        val daysOfWeek = async { getDaysOfWeek() }
        view?.hideLoading()
        view?.showWeekDays(daysOfWeek)
        view?.showWeekDays(daysOfWeek)
    }

    fun onWeekDayClicked(weekDay: WeekDay) = view?.openDetail(weekDay)

    interface View {
        fun hideLoading()
        fun showWeekDays(days: List<WeekDay>)
        fun showLoading()
        fun showEmptyCase()
        fun openDetail(weekDay: WeekDay)
    }
}