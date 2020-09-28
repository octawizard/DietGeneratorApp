package com.octawizard

import android.app.Application
import android.content.Context
import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.conf.ConfigurableKodein
import com.github.salomonbrys.kodein.singleton
import com.octawizard.data.repository.DayRepository
import com.octawizard.data.repository.DietRepository
import com.octawizard.data.repository.SuperHeroRepository

class DietApplication : Application(), KodeinAware {
    override val kodein = ConfigurableKodein(mutable = true)
    var overrideModule: Module? = null

    override fun onCreate() {
        super.onCreate()
        resetInjection()
    }

    fun addModule(activityModules: Module) {
        kodein.addImport(activityModules, true)
        if (overrideModule != null) {
            kodein.addImport(overrideModule!!, true)
        }
    }

    fun resetInjection() {
        kodein.clear()
        kodein.addImport(appDependencies(), true)
    }

    private fun appDependencies(): Module {
        return Module(allowSilentOverride = true) {
            bind<SuperHeroRepository>() with singleton {
                SuperHeroRepository()
            }
            bind<DietRepository>() with singleton { DietRepository() }
            bind<DayRepository>() with singleton { DayRepository() }
        }
    }
}

fun Context.asApp() = this.applicationContext as DietApplication