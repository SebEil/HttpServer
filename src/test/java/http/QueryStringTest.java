package http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class QueryStringTest {

    @Test
    void ShouldRetrieveQueryParameters(){
        QueryString queryString = new QueryString("Status=200");
        Assertions.assertEquals("200", queryString.getParameter("Status"));
    }

    @Test
    void shouldReturnNullForMissingParameter() {
        QueryString queryString = new QueryString("status=404");
        assertNull(queryString.getParameter("body"));
    }

    @Test
    void shouldParseSeveralParameters() {
        QueryString queryString = new QueryString("status=200&body=Hello");
        Assertions.assertEquals("200", queryString.getParameter("status"));
        Assertions.assertEquals("Hello", queryString.getParameter("body"));
    }

    @Test
    void shouldSerializeQueryString() {
        QueryString queryString = new QueryString("status=200");
        Assertions.assertEquals("status=200", queryString.getQueryString());
        queryString.addParameter("body", "Hello");
        Assertions.assertEquals("status=200&body=Hello", queryString.getQueryString());
    }
}