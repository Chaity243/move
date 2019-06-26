package com.chaity.easymove.di.module

import com.chaity.easymove.TimberLogger
import com.chaity.easymove.di.scope.ApplicationScope
import com.chaity.easymove.domain.Logger
import dagger.Binds
import dagger.Module

@Module(includes = [ApplicationModule.LoggerModule::class])
class ApplicationModule() {
    @Module
    interface LoggerModule{
        @Binds
        @ApplicationScope
        fun bindLogger(loagger : TimberLogger) : Logger
    }
}