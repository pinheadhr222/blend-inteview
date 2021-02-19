package com.interview.search.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.repo.SearchRepo
import com.interview.search.data.AlbumImageInfo
import com.interview.search.data.AlbumInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo: SearchRepo
) : ViewModel() {
    private val _data = MutableLiveData<List<AlbumInfo>>()
    val data = _data

    fun search(query: String) {
        viewModelScope.launch {
            _data.value = repo.search(query).map {
                val imageMap = it.images?.map { image ->
                    val id = image.id
                    val value = AlbumImageInfo (
                        id = image.id,
                        title = image.title,
                        description = image.description,
                        link = image.link,
                        type = image.type,
                        animated = image.animated,
                        favorite = image.favorite
                    )

                    id to value
                }?.toMap()

                AlbumInfo(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    coverImage = imageMap?.get(it.cover)?.link ?: "",
                    favorite = it.favorite,
                    images = imageMap?.values?.toList() ?: emptyList()
                )
            }

        }
    }
}