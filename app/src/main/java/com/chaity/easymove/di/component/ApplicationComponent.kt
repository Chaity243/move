package com.chaity.easymove.di.component

import android.app.Application
import com.chaity.easymove.di.module.ApplicationModule
import com.chaity.easymove.di.module.DbModule
import com.chaity.easymove.di.module.RetrofitModule
import com.chaity.easymove.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component



@Component(modules = [ApplicationModule::class, DbModule::class, RetrofitModule::class])
@ApplicationScope
interface ApplicationComponent{
    fun plusFragmentComponent() : FragmentComponent


    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
        @BindsInstance
        fun application(application: Application): Builder
    }

}