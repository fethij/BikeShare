package dev.johnoreilly.common.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dev.johnoreilly.common.database.AppDatabase
import dev.johnoreilly.common.database.dbFileName
import dev.johnoreilly.common.ui.BikeShareApp
import io.ktor.client.engine.android.Android
import kotlinx.coroutines.Dispatchers
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.MergeComponent


@MergeComponent(AppScope::class)
abstract class AndroidApplicationComponent(val application: Application): SharedApplicationComponent {
    abstract val bikeShareApp: BikeShareApp

    override fun getHttpClientEngine() = Android.create()

    override fun getRoomDatabase() = createRoomDatabase(application)

    companion object
}

fun createRoomDatabase(ctx: Context): AppDatabase {
    val dbFile = ctx.getDatabasePath(dbFileName)
    return Room.databaseBuilder<AppDatabase>(ctx, dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}