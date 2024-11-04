package bussar.curiotes

import KoinInitializer
import android.app.Application

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
 * design poprawić +
 * dodać linki - aktualnie jest jeden, ale w sumie może czas sprawdzić więcej?:
 * link można skopiować od razu z panelu curioty
 * sortowanie po dacie dodania, zrobione/nie zrobione +
 *
 * Plan na poprawę i całkowitą zmianę designu:
 * - dodać bottom nav bar który będzie miał zakładki: Explore, Curiotes, Categories
 * - Sekcja explore: coś jak zdrowe zakupy: mamy Top Curiotę z dnia, następnie sekcja niesprawdzonych curiot, najnoszesze 3 curioty, Sprawdź teraz: wikipedia link to random article
 * - Sekcja curiotes: to co jest, po prostu lista z filtrami, możliwość wyszukiwania, edycji, kasowania, dodawania
 * - sekcja categories: nowość: każda curiota może być sklasyfikowana jako categoria, w formie grida z kafelkami, z tego też ekranu odnośnik do tworzenia curioty
 * - w przyszłości sekcja statystyk: curioty dodawane w zależności od kategorii




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