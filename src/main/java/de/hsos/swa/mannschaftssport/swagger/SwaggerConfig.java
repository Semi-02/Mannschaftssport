package de.hsos.swa.mannschaftssport.swagger;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
        tags = {
                @Tag(name = "Mannschaftssport", description = "Operations for sports teams-database")
        },
        info = @Info(
                title = "REST API with Quarkus for sports teams",
                version = "1.0",
                contact = @Contact(
                        name = "Daniel Bregen, Semir Kuc",
                        url = "https://fooWebSiteUrl.legit",
                        email = "foo@mail.legit"
                ),
                license = @License(
                        name = "FAKE LICENSE",
                        url = "https://fooLicensingWebsite.legit/licenses/FAKE LICENSE"
                )
        )
)
public class SwaggerConfig extends Application {
}
