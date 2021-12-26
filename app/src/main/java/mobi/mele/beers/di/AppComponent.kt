package mobi.mele.beers.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Antonio Fernández
 * date   : 26/12/21
 * e-mail : meleappdev@gmail.com
 */
@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface AppComponent {

    //Aquí añadimos los distintos modulos usados en los viewModel
    //fun plus(module: MainModule): MainComponent
    //fun plus(module: DetailModule): DetailComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }
}