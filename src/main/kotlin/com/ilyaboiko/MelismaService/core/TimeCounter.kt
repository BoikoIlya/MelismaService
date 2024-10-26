package com.ilyaboiko.MelismaService.core

import com.ilyaboiko.MelismaService.model.StatisticsRecordEntity
import java.util.concurrent.TimeUnit

object TimeCounter {

    fun countTimeInMinutes(records:List<StatisticsRecordEntity>?): Long{
//        var totalTime = 0L
//
//        var firstValue = 0L
//        records?.forEach {
//            if (it.isStarted) firstValue = it.recordTime.time
//            else {
//                totalTime += (it.recordTime.time - firstValue)
//                firstValue = 0L
//            }
//        }

        val groupped = records?.groupBy { it.track.id }

        var totalTime = 0L
        var firstValue = 0L

        groupped?.forEach { (id, record) ->
            record.forEach {
                if (it.isStarted) firstValue = it.recordTime.time
                else if(firstValue!=0L) {
                    val newRecordTime = it.recordTime.time - firstValue
                    if(newRecordTime<TimeUnit.MINUTES.toMillis(5)) totalTime+=newRecordTime
                    else TimeUnit.MINUTES.toMillis(5)
                    totalTime += (it.recordTime.time - firstValue)
                    firstValue = 0L
                }
            }
        }

        return TimeUnit.MILLISECONDS.toMinutes(totalTime)

    }


}