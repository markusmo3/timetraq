import tornadofx.*

class TTApp: App(TTView::class)

class TTView: View() {
    override val root = vbox {
        button("Press me")
        label("Waiting")
    }
}
