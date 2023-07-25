package app.manganyan.domain.interactor

import javax.inject.Inject

data class MangaInteractor @Inject constructor(

    val getMangaPageUC: getMangaPageUC,

)
