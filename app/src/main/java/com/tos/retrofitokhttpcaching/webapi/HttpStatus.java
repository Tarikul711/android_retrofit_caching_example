package com.tos.retrofitokhttpcaching.webapi;


public class HttpStatus {

    public static int OK = 200;  // SUCCESSFUL REQUEST and RESPONSE
    public static int ERROR = -200; // There is error or bug in library end or Response fields missing
    public static int BAD_REQUEST = 400;
    public static int UNAUTHORIZED = 401;
    public static int FORBIDDEN = 403;
    public static int NOT_FOUND = 404;
    public static int INTERNAL_SERVER_ERROR = 500;
    public static int NOT_IMPLEMENTED = 501;
    public static int BAD_GATWAY = 502;

}
