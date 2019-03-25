package com.example.testapplication.model

import com.dbflow5.annotation.Column
import com.dbflow5.annotation.PrimaryKey
import com.example.testapplication.app.PrepackagedDB
import com.dbflow5.annotation.Table
import com.dbflow5.annotation.Unique

@Table(database = PrepackagedDB::class, name = "Users")
class Users(
    @PrimaryKey(autoincrement = true)
    @Unique(unique = true)
    var id: Int = 0,

    @Column var name: String? = null,
    @Column var age: String? = null,
    @Column var lang: String? = null
)

