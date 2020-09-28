package com.octawizard.ui.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.octawizard.R
import com.octawizard.domain.model.WeekDay
import com.octawizard.domain.usecase.GetDaysOfWeek
import com.octawizard.ui.presenter.DietPresenter
import com.octawizard.ui.view.adapter.DietAdapter
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity(), DietPresenter.View {

    override val presenter: DietPresenter by injector.instance()
    private lateinit var adapter: DietAdapter
    override val layoutId: Int = R.layout.main_activity
    override val toolbarView: Toolbar
        get() = toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeAdapter()
        initializeRecyclerView()
    }

    private fun initializeAdapter() {
        adapter = DietAdapter(presenter)
    }

    private fun initializeRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun showEmptyCase() {
        tv_empty_case.visibility = View.VISIBLE
    }

    override fun showWeekDays(days: List<WeekDay>) {
        adapter.clear()
        //adapter.addAll(superHeroes)
        adapter.addAll(days)
        adapter.notifyDataSetChanged()
    }

    override fun openDetail(weekDay: WeekDay) {
        DietDetailActivity.open(activity = this, weekDay = weekDay)
    }

    override val activityModules = Module(allowSilentOverride = true) {
        bind<DietPresenter>() with provider {
            DietPresenter(this@MainActivity, instance())
        }
        bind<GetDaysOfWeek>() with provider { GetDaysOfWeek(instance()) }
    }
}
