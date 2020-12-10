package dev.enhance.exercise.spacex

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.enhance.exercise.R
import dev.enhance.exercise.core.repository.models.Launch
import javax.inject.Inject

class SpaceXViewModel @Inject constructor(
    private val spaceXRepository: SpaceXRepository,
    private val loaderRx: MutableLiveData<Boolean>,
    private val errorMessageRx: MutableLiveData<Int>): ViewModel() {

    private val launchesLiveData: MutableLiveData<List<Launch>> = MutableLiveData()

    fun getLaunchesData(): LiveData<List<Launch>> = launchesLiveData

    fun loading(): LiveData<Boolean> = loaderRx

    fun error(): LiveData<Int> = errorMessageRx

    @SuppressLint("CheckResult")
    fun getLaunches() {
        spaceXRepository.getProjectsApi(loaderRx)
            .subscribe({
                launchesLiveData.postValue(it)
            }, {
                errorMessageRx.postValue(R.string.error_msg)
            })
    }

}