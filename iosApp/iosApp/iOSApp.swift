import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
	var body: some Scene {
        WindowGroup {
            // Kotlin 側から初期画面の状態を取得
            let stateString = ViewStateProvider().startUpViewState()
            if let viewState = ContentView.ViewState(rawString: stateString) {
                ContentView(viewState: viewState)
            } else {
                Text("Invalid view state from Kotlin")
            }
        }
	}
}
