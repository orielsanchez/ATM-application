package edu.sdccd.cisc191.f;

/**
 * Parses the request types
 *
 * @author Oriel Sanchez
 * @author Eric Kim
 */
public class RequestTypeParser {

    /**
     * Parses request types
     *
     * @param input the request as a string
     * @return a RequestType object
     */
    public static RequestType parseRequestType(String input) {
        String string = input.substring(16, 19);
        return RequestType.valueOf(string);
    }
}
