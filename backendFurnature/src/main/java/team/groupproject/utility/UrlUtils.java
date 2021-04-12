package team.groupproject.utility;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class UrlUtils {

    public static String getSiteURL() {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return baseUrl;
    }
}
