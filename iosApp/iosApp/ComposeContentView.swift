import SwiftUI
import ComposeApp

struct ComposeContentView: UIViewControllerRepresentable {
    let index: Int

    func makeUIViewController(context: Context) -> UIViewController {
        ComposeApp.PagerScreenViewControllerKt.PagerScreenViewController(index: Int32(index))
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        // ここは空でOK
    }
}
