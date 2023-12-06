import UIKit
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {
    init() {
        Main_iosKt.doInitDI()
    }

    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}
