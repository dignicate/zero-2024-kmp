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

//             Spacer()
//
//             Group {
//                 switch selectedIndex {
//                 case 0: Text("This is page 1")
//                 case 1: Text("This is page 2")
//                 case 2: Text("This is page 3")
//                 default: EmptyView()
//                 }
//             }
//
//             Spacer()

            ComposeContentView(index: selectedIndex)
        }
        .padding()
    }
    
    
}
