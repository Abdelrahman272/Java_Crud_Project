package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusKind {

    ENABLED(false, "利用可能"),
    
    DISABLED(true, "利用不可");

    private boolean isDisabled;
    private final String displayValue;
}
