package com.entertainment.showflix.feature_album.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "albums")
open class Album {
    var albumId: Int = -1
    @PrimaryKey
    var id: Int = -1
    var title: String = ""
    var url: String = ""
    var thumbnailUrl: String = ""
}