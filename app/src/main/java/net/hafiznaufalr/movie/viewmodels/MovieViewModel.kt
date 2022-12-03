package net.hafiznaufalr.movie.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.hafiznaufalr.movie.data.movie.model.GenreModel
import net.hafiznaufalr.movie.data.movie.model.MovieDataModel
import net.hafiznaufalr.movie.data.movie.model.MovieReviewModel
import net.hafiznaufalr.movie.domain.base.ResultData
import net.hafiznaufalr.movie.domain.base.toResult
import net.hafiznaufalr.movie.domain.movie.*
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val nowPlayingUseCase: NowPlayingUseCase,
    private val popularUseCase: PopularUseCase,
    private val genreUseCase: GenreUseCase,
    private val reviewUseCase: ReviewUseCase,
    private val trailerUseCase: TrailerUseCase
) : ViewModel() {
    private val _nowPlaying = MutableLiveData<ResultData<MovieDataModel>>()
    val nowPlaying: LiveData<ResultData<MovieDataModel>>
        get() = _nowPlaying

    private val _popular = MutableLiveData<ResultData<MovieDataModel>>()
    val popular: LiveData<ResultData<MovieDataModel>>
        get() = _popular

    private val _movieGenres = MutableLiveData<ResultData<List<GenreModel>>>()
    val movieGenres: LiveData<ResultData<List<GenreModel>>>
        get() = _movieGenres

    private val _reviews = MutableLiveData<ResultData<List<MovieReviewModel>>>()
    val reviews: LiveData<ResultData<List<MovieReviewModel>>>
        get() = _reviews

    private val _trailerKey = MutableLiveData<ResultData<String>>()
    val trailerKey: LiveData<ResultData<String>>
        get() = _trailerKey

    fun getNowPlaying() {
        _nowPlaying.value = ResultData.Loading
        viewModelScope.launch {
            nowPlayingUseCase.invoke().toResult().run(_nowPlaying::postValue)
        }
    }

    fun getPopular(page: Int) {
        _popular.value = ResultData.Loading
        viewModelScope.launch {
            popularUseCase.addParam(page).invoke().toResult().run(_popular::postValue)
        }
    }

    fun getMovieGenres() {
        _movieGenres.value = ResultData.Loading
        viewModelScope.launch {
            genreUseCase.invoke().toResult().run(_movieGenres::postValue)
        }
    }

    fun getMovieReviews(movieId: Int) {
        _reviews.value = ResultData.Loading
        viewModelScope.launch {
            reviewUseCase.addParam(movieId).invoke().toResult().run(_reviews::postValue)
        }
    }

    fun getMovieTrailerKey(movieId: Int) {
        _trailerKey.value = ResultData.Loading
        viewModelScope.launch {
            trailerUseCase.addParam(movieId).invoke().toResult().run(_trailerKey::postValue)
        }
    }
}