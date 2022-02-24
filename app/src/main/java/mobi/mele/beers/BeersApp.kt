package mobi.mele.beers

import android.app.Application
import mobi.mele.beers.di.AppComponent
import mobi.mele.beers.di.DaggerAppComponent

/**
 * Created by Antonio Fernández
 * date   : 26/12/21
 * e-mail : meleappdev@gmail.com
 */
class BeersApp : Application() {

    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
            .factory()
            .create(this)
    }
}