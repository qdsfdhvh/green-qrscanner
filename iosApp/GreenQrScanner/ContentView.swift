import UIKit
import SwiftUI
import combine

struct ComposeView: UIViewControllerRepresentable {
    init() {
        MainKt.doInitDI()
    }

    func makeUIViewController(context: Context) -> UIViewController {
        MainKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}



