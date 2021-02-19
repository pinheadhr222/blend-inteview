package com.interview.repo

import com.interview.repo.api.SearchApi
import com.interview.repo.data.SearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface SearchRepo {

    suspend fun search(query: String): List<SearchResult>
}


class SearchRepoImpl(
    private val api: SearchApi
): SearchRepo {
    override suspend fun search(query: String): List<SearchResult> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.search(query).execute()
//                response.body()?.results ?: emptyList()
                emptyList()
            } catch (ie: Exception) {
                ie.printStackTrace()
                emptyList()
            }
        }
    }

}