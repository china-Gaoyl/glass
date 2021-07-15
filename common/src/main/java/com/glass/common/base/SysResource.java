package com.glass.common.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysResource implements Serializable {

    private String type;
    private String authority;
    private String resourceName;
    private String sequence;
}
