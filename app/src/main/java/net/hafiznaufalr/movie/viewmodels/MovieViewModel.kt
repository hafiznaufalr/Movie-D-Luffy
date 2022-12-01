package net.hafiznaufalr.movie.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.hafiznaufalr.movie.data.movie.model.MovieDataModel
import net.hafiznaufalr.movie.domain.base.ResultData
import net.hafiznaufalr.movie.domain.base.toResult
import net.hafiznaufalr.movie.domain.now_playing.NowPlayingUseCase
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val nowPlayingUseCase: NowPlayingUseCase
): ViewModel() {
    private val _nowPlaying = MutableLiveData<ResultData<MovieDataModel>>()
    val nowPlaying: LiveData<ResultData<MovieDataModel>>
        get() = _nowPlaying

    fun getNowPlaying() {
        _nowPlaying.value = ResultData.Loading
        viewModelScope.launch {
            nowPlayingUseCase.invoke().toResult().run(_nowPlaying::postValue)
        }
    }
}