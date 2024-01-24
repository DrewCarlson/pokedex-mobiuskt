package com.mocoding.pokedex.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.mocoding.pokedex.ui.main.LoopHoldingViewModel
import kt.mobius.First
import kt.mobius.Init
import kt.mobius.LoggingInit
import kt.mobius.MobiusLoop
import kt.mobius.compose.ComposeMobiusLoopStateHolder
import moe.tlaster.precompose.viewmodel.viewModel
import org.koin.compose.getKoin
import org.koin.core.Koin
import org.koin.core.context.KoinContext
import kotlin.reflect.KClass


@Composable
fun <M, E, F> rememberMobiusLoopPreCompose(
    startModel: M,
    init: Init<M, F>? = null,
    loopBuilder: Koin.() -> MobiusLoop.Builder<M, E, F>
): ComposeMobiusLoopStateHolder<M, E> {
    val koin = getKoin()
    @Suppress("UNCHECKED_CAST")
    val viewModel = viewModel((LoopHoldingViewModel::class as KClass<LoopHoldingViewModel<M, E, F>>)) {
        val loopFactory = loopBuilder(koin)
        val first = run {
            val actualInit = init ?: Init { First.first(startModel) }
            LoggingInit.fromLoop(actualInit, loopFactory)
                .init(startModel)
        }
        val mobiusLoop = loopFactory.startFrom(first.model(), first.effects())
        LoopHoldingViewModel(mobiusLoop)
    }
    val modelState = viewModel.model.collectAsState()
    return remember {
        ComposeMobiusLoopStateHolder(
            model = modelState,
            eventConsumer = viewModel::dispatchEvent,
        )
    }
}
