package edu.sdccd.cisc191.f;

public class RequestTypeParser {

    public static RequestType parseRequestType(String input) {
        String string = input.substring(16, 19);
        return RequestType.valueOf(string);
    }
}
