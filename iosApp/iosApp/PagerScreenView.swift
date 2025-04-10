import SwiftUI
import ComposeApp

struct PagerScreenView: UIViewControllerRepresentable {
    let index: Int

    func makeUIViewController(context: Context) -> UIViewController {
        ComposeApp.PagerScreenViewControllerKt.PagerScreenViewController(index: Int32(index))
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        // No-op
    }
}
