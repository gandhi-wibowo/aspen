package com.dizcoding.aspen.main.data

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single {
        Room
            .databaseBuilder(
                androidContext(),
                Database::class.java,
                "hotels.db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<Database>().catalogDao() }
}