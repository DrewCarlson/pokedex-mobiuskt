import Foundation
import SwiftUI
import pokedex

extension View {
    public func bindLoop<M, E>(
        initFunc: Init? = nil,
        modelBinding: Binding<M>,
        consumerBinding: Binding<((E) -> Void)?>,
        loopFactory: MobiusLoopFactory
    ) -> some View {
        
        var loop: MobiusLoop<AnyObject, AnyObject, AnyObject>? = nil
        
        return onAppear {
            let first = LoggingInit<AnyObject, AnyObject>.companion.fromLoop(
                init: initFunc ?? NoopInit(),
                loopBuilder: loopFactory as! MobiusLoopBuilder
            ).doInit(model: modelBinding.wrappedValue)
           
            loop = loopFactory.startFrom(
                startModel: first.model(),
                startEffects: first.effects()
            )
           
            try! loop?.observe(observer: BindingConsumer<M>(model: modelBinding))
            
            consumerBinding.wrappedValue = { event in
                loop?.dispatchEvent(event: event as AnyObject?)
            }
        }
        .onDisappear {
            consumerBinding.wrappedValue = nil
            loop?.dispose()
            loop = nil
        }
    }
}

private class NoopInit : Init {
    func doInit(model: Any?) -> First<AnyObject, AnyObject> {
        return First<AnyObject, AnyObject>.companion.first(
            model: model,
            effects: KotlinArray(size: 0, init: { _ in nil })
        )
    }
}

private class BindingConsumer<T> : Consumer {
    private var model: Binding<T>
    
    init(model: Binding<T>) {
        self.model = model
    }
    
    func accept(value: Any?) {
        model.wrappedValue = value as! T
    }
}

public func mobiusLogger(tag: String) -> SimpleLogger<AnyObject, AnyObject, AnyObject> {
    return SimpleLogger(tag: tag)
}
