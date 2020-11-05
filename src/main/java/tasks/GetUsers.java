package tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetUsers implements Task {

    private final int page;

    public GetUsers(int page) {
        this.page = page;
    }

    public static Performable from(int page) {
        return instrumented(GetUsers.class, page);
    }

    @Override
    @Step("{0} Realiza la peticion Get")
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Get.resource("/api/users?page=" + page).
                        with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .header("fsf", "df"))
        );
    }
}
