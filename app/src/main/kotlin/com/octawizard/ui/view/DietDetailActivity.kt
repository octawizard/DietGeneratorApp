package com.octawizard.ui.view

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.Toolbar
import android.view.View
import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.octawizard.R
import com.octawizard.domain.model.DailyMeals
import com.octawizard.domain.model.WeekDay
import com.octawizard.domain.usecase.GetDailyDiet
import com.octawizard.ui.presenter.DietDetailPresenter
import com.octawizard.ui.utils.setImageBackground
import kotlinx.android.synthetic.main.super_hero_detail_activity.*

class DietDetailActivity : BaseActivity(), DietDetailPresenter.View {

    companion object {
        private const val WEEK_DAY_KEY = "com.karumi.domain.model.week_day_key"

        fun open(activity: Activity, weekDay: WeekDay) {
            val intent = Intent(activity, DietDetailActivity::class.java)
            intent.putExtra(WEEK_DAY_KEY, weekDay)
            activity.startActivity(intent)
        }
    }

    override val presenter: DietDetailPresenter by injector.instance()

    override val layoutId: Int = R.layout.super_hero_detail_activity
    override val toolbarView: Toolbar
        get() = toolbar

    override fun preparePresenter(intent: Intent?) {
        val weekDay = intent?.extras?.getSerializable(WEEK_DAY_KEY) as WeekDay
        title = weekDay.name
        presenter.preparePresenter(weekDay)
    }

    override fun close() = finish()

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun showDailyMeals(weekDay: WeekDay) {
        iv_day_photo.setImageBackground(weekDay.photo)
    }

    override fun showDiet(dailyMeals: DailyMeals) {
        tv_meal_lunch_protein_description.text = dailyMeals.lunch.protein.toString()
        tv_meal_lunch_vegetable_description.text = dailyMeals.lunch.vegetable.toString()
        tv_meal_lunch_side_vegetables_description.text = dailyMeals.lunch.sideVegetables.joinToString(", ")
        tv_meal_lunch_carbohydrate_description.text = dailyMeals.lunch.carb.toString()

        tv_meal_dinner_protein_description.text = dailyMeals.dinner.protein.toString()
        tv_meal_dinner_vegetable_description.text = dailyMeals.dinner.vegetable.toString()
        tv_meal_dinner_side_vegetables_description.text = dailyMeals.dinner.sideVegetables.joinToString(", ")
        tv_meal_dinner_carbohydrate_description.text = dailyMeals.dinner.carb.toString()
    }

    override val activityModules = Module(allowSilentOverride = true) {
        bind<DietDetailPresenter>() with provider {
            DietDetailPresenter(this@DietDetailActivity, instance())
        }
        bind<GetDailyDiet>() with provider { GetDailyDiet(instance()) }
    }
}