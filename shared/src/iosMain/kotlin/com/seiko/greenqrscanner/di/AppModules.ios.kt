package com.seiko.greenqrscanner.di

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.seiko.greenqrscanner.util.AppDateFormatter

@Singleton
@Provides
fun provideAppDateFormatter(): AppDateFormatter {
    return AppDateFormatter()
}
