package pokedex.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kt.mobius.First
import kt.mobius.Init
import kt.mobius.LoggingInit
import kt.mobius.MobiusLoop
import kt.mobius.compose.ComposeMobiusLoopStateHolder
import kt.mobius.functions.Consumer
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModel
import org.koin.compose.getKoin
import org.koin.core.Koin
import kotlin.reflect.KClass

// Similar to the `mobiuskt-compose` helpers, this method
// relies on PreCompose to maintain the Loop's lifecycle.
// Additionally, it uses the `loopClass` (ex. `DetailsLoop`)
// to inject the MobiusLoop.Builder from Koin.
@Composable
fun <M, E, F> rememberMobiusLoop(
    startModel: M,
    init: Init<M, F>? = null,
    createLoopBuilder: Koin.() -> MobiusLoop.Builder<M, E, F>
): ComposeMobiusLoopStateHolder<M, E> {
    val koin = getKoin()
    @Suppress("UNCHECKED_CAST")
    val viewModel = viewModel((LoopHoldingViewModel::class as KClass<LoopHoldingViewModel<M, E, F>>)) {
        val loopBuilder = createLoopBuilder(koin)
        val actualInit = init ?: Init { First.first(startModel) }
        val first = LoggingInit.fromLoop(actualInit, loopBuilder).init(startModel)
        val mobiusLoop = loopBuilder.startFrom(first.model(), first.effects())
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

// A ViewModel that manages a loop's lifecycle, exposing the
// current Model instance and accepting Events into the loop.
private class LoopHoldingViewModel<M, E, F>(
    private val loop: MobiusLoop<M, E, F>
) : ViewModel(), Consumer<M> {

    private val _model = MutableStateFlow(loop.mostRecentModel)
    val model = _model.asStateFlow()

    init {
        loop.observe(this)
    }

    fun dispatchEvent(event: E) {
        loop.dispatchEvent(event)
    }

    override fun accept(value: M) {
        _model.update { value }
    }

    override fun onCleared() {
        super.onCleared()
        loop.dispose()
    }
}
