package bussar.curiotes

import android.app.Application
import KoinInitializer

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}

/**
 * Plan na apkę:
 * standard: {
 * curioty do
 *          wyświetlania +,
 *          dodawania +,
 *          edytowania +,
 *          usuwania -  (v1) - wchodzisz w edit i tam jest opcja usunięcia curioty +
 *          (v2)można zaznaczyć kilka i usuwać przyciskiem u góry -> dodatkowo alert przed usunięciem
 * na górze wyszukiwarka curiot, opcja dodawania, opcja filtrowania + > to się pojawia wtedy kiedy jest minimum 1 curiote'a // update: widoczne od początku
 * design poprawić
 * dodać linki
 * link można skopiować od razu z panelu curioty
 * sortowanie po dacie dodania, zrobione/nie zrobione +



 *
 *
 * }
 * premium: {
 *  - dodanie kategorii
 *  - alert o sprawdzeniu curiotów
 *  priorytety
 * dodanie wyszukiwania z chatem gtp - ale dopiero po zalogowaniu przez usera
 * możliwość dodawania zdjęć
 * możliwość dodawania notatek głosowych
 *
 *
 *  }
 *
 *
 *
 */