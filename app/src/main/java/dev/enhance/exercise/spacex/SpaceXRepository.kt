package dev.enhance.exercise.spacex

import io.reactivex.Observable
import androidx.lifecycle.MutableLiveData
import dev.enhance.exercise.core.repository.Api
import dev.enhance.exercise.core.repository.models.Launch
import dev.enhance.exercise.core.repository.models.SpaceXResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SpaceXRepository @Inject constructor(val api: Api) {

    fun getProjectsApi(loaderRx: MutableLiveData<Boolean>): Observable<List<Launch>> = api
        .getLaunches()
        .map { it.filter { launch -> launch.rocket == SpaceXResponse.FALCON9_ID } }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { loaderRx.postValue(true) }
        .doOnTerminate { loaderRx.postValue(false) }

}