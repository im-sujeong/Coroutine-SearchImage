package com.sue.coroutine_searchimage.domain.use_case

data class UseCases(
    val searchImageUseCase: SearchImageUseCase,
    val insertFavoriteImageUseCase: InsertFavoriteImageUseCase,
    val getFavoriteImageUseCase: GetFavoriteImageUseCase,
    val deleteFavoriteImageUseCase: DeleteFavoriteImageUseCase
)
