package io.pivotal.auth.testapp;

import io.pivotal.auth.samlwrapper.userannotation.CurrentUser;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class Application {

    private String genericPage(String title, String content) {
        return (
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<head><title>" + title + "</title></head>" +
                        "<body>" +
                        "<h1>" + title + "</h1>" +
                        content +
                        "</body>" +
                        "</html>"
        );
    }

    @RequestMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String homePage() {
        return genericPage("Public Page",
                "<p class='home-page'><a href=\"/auth-required\">Go to Authenticated page</a></p>"
        );
    }

    @RequestMapping(value = "/auth-required", produces = MediaType.TEXT_HTML_VALUE)
    public String authRequiredPage(@CurrentUser User user) {

        return genericPage("Authentication Required to view",
                "<p class='auth-required'></p>" +
                "<p class='username-test'>Logged in as " + user.getUsername() + ".</p>"
        );
    }

    @RequestMapping(value = "/unauth1", produces = MediaType.TEXT_HTML_VALUE)
    public String unauth1() {
        return genericPage("Public Page - Unauth1",
                "<p class='unauth1-page'></p>"
        );
    }

    @RequestMapping(value = "/unauth2", produces = MediaType.TEXT_HTML_VALUE)
    public String unauth2() {
        return genericPage("Public Page - Unauth2",
                "<p class='unauth2-page'></p>"
        );
    }

}
