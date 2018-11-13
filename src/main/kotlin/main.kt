
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.text.FontWeight
import javafx.stage.Stage
import tornadofx.*
import views.HomeView
import kotlin.reflect.KClass

class MainApp : App() {
    override val primaryView: KClass<out View>
        get() = HomeView::class

    override fun start(stage: Stage) {
        stage.isResizable = false
        stage.sizeToScene()

        try {
            val view = find(primaryView)

            stage.apply {
                scene = Scene(view.root)
                scene.stylesheets.addAll(FX.stylesheets)
                titleProperty().bind(view.titleProperty)
                show()
            }
        } catch (ex: Exception) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), ex)
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(MainApp::class.java, *args)
}

class Styles : Stylesheet() {
    init {
        label {
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            backgroundColor += c("#cecece")
        }
    }
}