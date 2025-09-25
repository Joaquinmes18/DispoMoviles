// features/dollar/data/repository/DollarRepository.kt
package com.calyrsoft.ucbp1.features.dollar.data.repository

import com.calyrsoft.ucbp1.features.dollar.data.database.dao.IDollarDao
import com.calyrsoft.ucbp1.features.dollar.data.database.entity.DollarEntity
import com.calyrsoft.ucbp1.features.dollar.data.source.RealTimeRemoteDataSource
import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel
import com.calyrsoft.ucbp1.features.dollar.domain.repository.IDollarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DollarRepository(
    private val realTimeRemoteDataSource: RealTimeRemoteDataSource,
    private val dao: IDollarDao
) : IDollarRepository {

    override suspend fun getDollar(): Flow<DollarModel> = flow {
        // (opcional) emitir el último registro local:
        dao.getList().maxByOrNull { it.timestamp }?.let { last ->
            emit(
                DollarModel(
                    dollarOfficial = last.dollarOfficial.orEmpty(),
                    dollarParallel = last.dollarParallel.orEmpty(),
                    // si tu DollarModel no tiene estos campos, puedes ignorarlos al emitir
                    dollarUsdt = last.usdt,
                    dollarUsdc = last.usdc
                )
            )
        }

        // escuchar remoto, guardar histórico y emitir
        realTimeRemoteDataSource.getDollarUpdates().collect { remote ->
            val entity = DollarEntity(
                dollarOfficial = remote.dollarOfficial,
                dollarParallel = remote.dollarParallel,
                usdt = remote.dollarUsdt,      // ← GUARDAR USDT
                usdc = remote.dollarUsdc,      // ← GUARDAR USDC
                timestamp = System.currentTimeMillis()
            )
            dao.insert(entity)  // ← HISTÓRICO EN ROOM
            emit(remote)        // ← UI recibe el último valor
        }
    }
}
