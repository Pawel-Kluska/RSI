package com.pwr.skirental.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Authors {

    private String author1;
    private String author2;
    private String datetime;
    private String javaVersion;
    private String userName;
    private String osName;
    private String localAddress;
}
