import SwiftUI
import ComposeApp

struct MyTabPagerView: View {
    @State private var selectedIndex: Int = 0

    var body: some View {
        VStack {
            Picker("Tabs", selection: $selectedIndex) {
                Text("First").tag(0)
                Text("Second").tag(1)
                Text("Third").tag(2)
            }
            .pickerStyle(.segmented)

            ComposeContentView(index: selectedIndex).id(selectedIndex)
        }
        .padding()
    }
    
    
}
