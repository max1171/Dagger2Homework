package ru.otus.daggerhomework.di

import android.content.Context
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import ru.otus.daggerhomework.ColorGenerator
import ru.otus.daggerhomework.ColorGeneratorImpl
import ru.otus.daggerhomework.ColorsDataStore
import ru.otus.daggerhomework.MainActivity

@FeatureScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [MainActivityModule::class]
)
interface MainActivityComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance activityContext: Context,
            applicationComponent: ApplicationComponent,
        ): MainActivityComponent
    }

    fun inject(activity: MainActivity)

    @ActivityContext
    fun provideActivityContext(): Context

    @ApplicationContext
    fun provideApplicationContext(): Context

    fun provideColorsGenerator(): ColorGenerator

    fun provideColorDataDStore(): ColorsDataStore
}

@Module
interface MainActivityModule {
    companion object {
        @Provides
        @FeatureScope
        @ActivityContext
        fun provideActivityContext(activityContext: Context): Context = activityContext
    }

    @Binds
    fun provideColorsGenerator(colorsGeneratorImpl: ColorGeneratorImpl): ColorGenerator
}