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
    
    var body: some View {
//        ComposeView()
//                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
        switch viewState {
        case .tabs:
            MyTabPagerView()
//        case .settings:
//            SettingsView()
//        case .login:
//            LoginView()
        }
    }
    
    enum ViewState {
        case tabs
//        case settings
//        case login
        
        init?(rawString: String) {
            switch rawString {
            case "tabs": self = .tabs
//            case "settings": self = .settings
//            case "login": self = .login
            default: return nil
            }
        }
    }

}





