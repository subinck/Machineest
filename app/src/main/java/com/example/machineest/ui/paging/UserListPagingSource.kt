package com.example.machineest.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.machineest.data.models.User
import com.example.machineest.data.network.GetUserListApi
import retrofit2.HttpException

private const val STARTING_PAGE_INDEX = 0
const val PAGE_SIZE=10

class UserListPagingSource ( private val service: GetUserListApi
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getUserList(
                limit =PAGE_SIZE
            )
            val responseData= mutableListOf<User>()
            val users = response.body()?.users?: emptyList()
            responseData.addAll(users)

            LoadResult.Page(
                data =users?: emptyList(),
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = if (responseData.isEmpty()) null else pageIndex.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
      return state.anchorPosition?.let {anchorPopsition->
      state.closestPageToPosition(anchorPopsition)?.prevKey?.plus(1)
          ?:state.closestPageToPosition(anchorPopsition)?.nextKey?.minus(1)
      }
    }

}