package com.dizcoding.aspen

import android.app.Application
import com.dizcoding.aspen.main.data.dataModule
import com.dizcoding.aspen.main.domain.domainModule
import com.dizcoding.aspen.main.view.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(
                listOf(
                    dataModule,
                    domainModule,
                    viewModule
                )
            )
        }
    }
}