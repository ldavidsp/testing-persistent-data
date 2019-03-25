package com.example.testapplication.app

import com.dbflow5.annotation.Database
import com.dbflow5.config.DBFlowDatabase

@Database(version = 2)
abstract class PrepackagedDB : DBFlowDatabase()

