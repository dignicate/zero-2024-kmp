import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    @State private var viewState: ViewState
    
    init(viewState: ViewState) {
        self.viewState = viewState
    }
    
    var body: some View {
        switch viewState {
        case .tabs:
            MyTabPagerView()
        }
    }
    
    enum ViewState {
        case tabs
        
        init?(rawString: String) {
            switch rawString {
            case "tabs": self = .tabs
            default: return nil
            }
        }
    }

}
