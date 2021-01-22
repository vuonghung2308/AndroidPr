package com.example.dagger.dagger

import com.example.dagger.MainActivity
import com.example.dagger.car.Car
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@PerActivity
@Component(
    modules = [WheelsModule::class, PetrolEngineModule::class],
    dependencies = [AppComponent::class]
)
interface ActivityComponent {
    fun getCar(): Car
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun horsePower(@Named("horse power") horsePower: Int): Builder

        @BindsInstance
        fun engineCapacity(@Named("engine capacity") engineCapacity: Int): Builder
        fun appComponent(appComponent: AppComponent): Builder
        fun build(): ActivityComponent
    }
}