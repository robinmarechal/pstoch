package views

import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.View
import tornadofx.label
import tornadofx.paddingAll

class HomeView : View() {

    private fun buildTitleRow(): Node {
        val row = label {
            text = "Welcome!"
            font = Font.font(null, FontWeight.BOLD, null, 24.0)
            textAlignment = TextAlignment.CENTER
            alignment = Pos.CENTER
            maxWidth = Double.MAX_VALUE
            paddingAll = 20
        }

        return row
    }

    private fun buildButtonsRow(): Node {

        val buttonMMS = Button("MMS")
        val buttonMM1K = Button("MM1K")

        return HBox(buttonMMS, buttonMM1K)
    }

    private fun buildRoot(): VBox {
        val root = VBox(buildTitleRow(), buildButtonsRow())

        root.setPrefSize(800.0, 600.0)

        return root
    }

    override val root = buildRoot()
}