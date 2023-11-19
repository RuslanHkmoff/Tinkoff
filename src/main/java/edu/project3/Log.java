package edu.project3;

public record Log(String addr, String user, String time, String method, String resource,
                  String protocol, int status, long size, String refer, String userAgent) {
}
