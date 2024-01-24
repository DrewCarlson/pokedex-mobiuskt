import Foundation
import SwiftUI
import pokedex


extension View {
    public func bindLoop<M, E>(
            loopFactory: @escaping () -> MobiusLoopFactory,
            modelBinding: Binding<M>,
            consumerBinding: Binding<((E) -> Void)?>
    ) -> some View {
        var loop: MobiusLoop<AnyObject, AnyObject, AnyObject>? = nil
        return onAppear {
            loop = loopFactory().startFrom(startModel: modelBinding.wrappedValue)
            try! loop?.observe(observer: BindingConsumer<M>(model: modelBinding))
            consumerBinding.wrappedValue = { event in
                loop?.dispatchEvent(event: event as AnyObject?)
            }
        }
        .onDisappear {
            consumerBinding.wrappedValue = nil
            loop?.dispose()
        }
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
