package mobi.mele.beers.di

import dagger.Module
import dagger.Provides
import mobi.mele.data.repository.BeersRepository
import mobi.mele.data.source.RemoteDataSource

/**
 * Created by Antonio Fernández
 * date   : 26/12/21
 * e-mail : meleappdev@gmail.com
 */

@Module
class DataModule {

    @Provides
    fun beersRepositoryProvider(
        remoteDataSource: RemoteDataSource
    ) = BeersRepository(remoteDataSource)

}