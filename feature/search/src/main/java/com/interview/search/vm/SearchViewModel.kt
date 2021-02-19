package com.interview.search.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.repo.SearchRepo
import com.interview.search.data.SearchDisplayInfo
import com.interview.ui.data.AlbumImageInfo
import com.interview.ui.data.AlbumInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo: SearchRepo
) : ViewModel() {
    private val _data = MutableLiveData<SearchDisplayInfo>()
    val data: LiveData<SearchDisplayInfo> = _data

    fun search(query: String) {
        viewModelScope.launch {
            val result = repo.search(query).map {
                val imageMap = it.images?.map { image ->
                    val id = image.id
                    val value = AlbumImageInfo (
                        id = image.id,
                        title = image.title ?: "",
                        description = image.description ?: "",
                        link = image.link,
                        type = image.type,
                        animated = image.animated,
                        favorite = image.favorite
                    )

                    id to value
                }?.toMap()

                AlbumInfo(
                    id = it.id,
                    title = it.title ?: "",
                    description = it.description ?: "",
                    coverImage = imageMap?.get(it.cover)?.link ?: "",
                    favorite = it.favorite,
                    images = imageMap?.values?.toList() ?: emptyList(),
                    clickable = !imageMap.isNullOrEmpty()
                )
            }

            _data.value = SearchDisplayInfo(
                showNoResult = result.isNullOrEmpty(),
                query = query,
                result = result)
        }
    }
}